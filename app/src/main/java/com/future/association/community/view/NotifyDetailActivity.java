package com.future.association.community.view;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.future.association.R;
import com.future.association.community.adapter.NotifyDetailAdapter;
import com.future.association.community.base.BaseActivity;
import com.future.association.community.base.EndlessRecyclerOnScrollListener;
import com.future.association.community.contract.NotifyDetailContract;
import com.future.association.community.model.MsgDetailInfo;
import com.future.association.community.model.ReplyInfo;
import com.future.association.community.presenter.NotifyDetailPresenter;
import com.future.association.databinding.ActivityNotifyDetailBinding;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/7/4.
 */

public class NotifyDetailActivity extends BaseActivity<ActivityNotifyDetailBinding> implements NotifyDetailContract.IView{

    private NotifyDetailAdapter adapter;
    private ArrayList<ReplyInfo> replyInfos;
    private NotifyDetailContract.IPresenter presenter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public int setContentView() {
        return R.layout.activity_notify_detail;
    }

    @Override
    public void initView() {
        linearLayoutManager = new LinearLayoutManager(context);
        viewBinding.rclReply.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void initData() {
        replyInfos = new ArrayList<>();
        viewBinding.layoutTitle.setTitle("通知详情");
        adapter = new NotifyDetailAdapter(context, replyInfos);
        viewBinding.rclReply.setAdapter(adapter);
        presenter = new NotifyDetailPresenter(this);
        presenter.getData(1);
        presenter.getNotifyDetail();
    }

    @Override
    public void initListener() {
        viewBinding.rclReply.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                presenter.getData(currentPage);
            }
        });
        viewBinding.layoutTitle.setViewClickListener(this);
        viewBinding.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_send://发表评论
                presenter.sendTalk();
        }
    }

    @Override
    public void setData(ArrayList<ReplyInfo> replyInfos) {
        this.replyInfos.addAll(replyInfos) ;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setNotifyDetail(MsgDetailInfo detailInfo) {
        viewBinding.setDetailInfo(detailInfo);
    }

    @Override
    public String getTalkContent() {
        return viewBinding.getTalkContent();
    }

    @Override
    public void talkReult(boolean isSuccess, ReplyInfo replyInfo) {
        if(isSuccess){//评论成功
            viewBinding.setTalkContent("");
            this.replyInfos.add(replyInfo) ;
            adapter.notifyDataSetChanged();
            viewBinding.rclReply.scrollToPosition(adapter.getItemCount()-1);//列表滑到最后一行
            showShortToast("评论成功");
        }else{
            showShortToast("评论失败，请稍候再试");
        }
    }
}