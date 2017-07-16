package com.future.association.supervice.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.future.association.R;
import com.future.association.supervice.model.SupericeList;

import java.util.List;

/**
 * Created by rain on 2017/7/7.
 */

public class SuperviceAdapter extends BaseQuickAdapter<SupericeList.SupericeListInfo,BaseViewHolder> {
    public SuperviceAdapter(@LayoutRes int layoutResId, @Nullable List<SupericeList.SupericeListInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SupericeList.SupericeListInfo item) {
        ImageView imageView = baseViewHolder.getView(R.id.iv);
        Glide.with(mContext).load(item.getImage()).error(R.drawable.ic_launcher).into(imageView);
        baseViewHolder.setText(R.id.title,item.getTitle())
                .setText(R.id.time,item.getTime());
    }
}
