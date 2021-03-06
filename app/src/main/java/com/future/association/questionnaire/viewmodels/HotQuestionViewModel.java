package com.future.association.questionnaire.viewmodels;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.future.association.R;
import com.future.association.common.Contants;
import com.future.association.common.MyApp;
import com.future.association.databinding.FragmentHotQuestionnaireBinding;
import com.future.association.questionnaire.QuestionnaireApi;
import com.future.association.questionnaire.adapters.QuestionnaireAdapter;
import com.future.association.questionnaire.models.QuestionDetail;
import com.future.association.questionnaire.views.QuestionnaireWebActivity;
import com.future.baselib.utils.CommonUtils;
import com.future.baselib.utils.HttpRequest;

/**
 * Created by chenyu on 2017/7/18.
 */

public class HotQuestionViewModel {
    private final Activity activity;
    private final FragmentHotQuestionnaireBinding mBinding;
    public ObservableField<QuestionnaireAdapter> adapterObservable = new ObservableField<>();
    public ObservableArrayList<QuestionDetail> items = new ObservableArrayList<>();
    private int PAGE = 1;

    public HotQuestionViewModel(Activity activity, FragmentHotQuestionnaireBinding binding) {
        this.activity = activity;
        this.mBinding = binding;
        initView();
        initData();
    }

    private void initView() {
        mBinding.rv.setLayoutManager(new LinearLayoutManager(activity));
        mBinding.rv.addItemDecoration(new DividerItemDecoration(activity, LinearLayoutManager.VERTICAL));
        QuestionnaireAdapter adapter = new QuestionnaireAdapter(R.layout.questionnaire_item, null, Contants.HOTQUESTIONNAI_REFRAGMENT);
        adapterObservable.set(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                ++PAGE;
                initData();
            }
        }, mBinding.rv);
        mBinding.rv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (!CommonUtils.isFastDoubleClick()) {
                    Intent intent = new Intent(activity, QuestionnaireWebActivity.class);
                    intent.putExtra("data", ((QuestionDetail) baseQuickAdapter.getItem(i)));
                    activity.startActivity(intent);
                }
            }
        });
    }

    private void initData() {
        QuestionnaireApi.getInstance().getHotWenjuan(activity, MyApp.getUserToken(), String.valueOf(PAGE))
                .setListener(new HttpRequest.OnNetworkListener<QuestionDetail>() {
                    @Override
                    public void onSuccess(QuestionDetail response) {
                        if (response == null || response.getList() == null) {
                            adapterObservable.get().loadMoreEnd();
                        } else {
                            if (response.getList().size() < 20) {
                                adapterObservable.get().loadMoreEnd();
                            }
                            items.addAll(response.getList());
                        }
                    }

                    @Override
                    public void onFail(String message) {

                    }
                }).start(new QuestionDetail());
    }

    //刷新数据
    public void refresh() {
        PAGE=1;
        initData();
    }
}
