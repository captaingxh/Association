package com.future.association.personal.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.future.association.R;
import com.future.association.common.MyApp;
import com.future.association.community.model.MsgDetailInfo;
import com.future.association.community.request.CommunityRequest;
import com.future.association.community.request.DataResponse;
import com.future.association.personal.PersonConstant;
import com.future.association.personal.adapter.TongzhiAdapter;
import com.future.association.personal.entity.MyNotification;
import com.future.baselib.activity.BaseActivity;
import com.future.baselib.utils.HttpRequest;
import com.future.baselib.utils.StatusUtils;

import java.util.ArrayList;
import java.util.List;

public class MyTongZhiActivity extends BaseActivity {
    private ListView lvMyTongzhi;
    private List<MyNotification.MyNotifications> tongzhiList = new ArrayList<>();
    private TongzhiAdapter tongzhiAdapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        StatusUtils.setStatusbarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_my_tongzhi);
    }

    @Override
    protected void initView() {
        setTitle("我的通知");
        setTitleLeft(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvMyTongzhi = (ListView) findViewById(R.id.lvMyTongzhi);


        new HttpRequest<MyNotification>()
                .with(this)
                .addParam("apiCode", PersonConstant.MY_NOTIFICATION)
                .addParam("userToken", MyApp.getUserToken())
                .addParam("page", "1")
                .addParam("size", PersonConstant.PAGE_SIZE_DEFAULT)
//                .addParam("id", id)
                .setListener(new HttpRequest.OnNetworkListener<MyNotification>() {
                    @Override
                    public void onSuccess(MyNotification response) {
                        if (response == null) return;
                        tongzhiList.clear();
                        tongzhiList = response.getList();
                        if (tongzhiAdapter == null) {
                            tongzhiAdapter = new TongzhiAdapter(MyTongZhiActivity.this, tongzhiList, getLayoutInflater());
                            lvMyTongzhi.setAdapter(tongzhiAdapter);
                        } else {
                            tongzhiAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFail(String message) {
                        toast("错误信息：" + message);
                    }
                }).start(new MyNotification());
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
