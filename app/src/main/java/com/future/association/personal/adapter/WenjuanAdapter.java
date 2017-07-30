package com.future.association.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.future.association.R;
import com.future.association.personal.entity.MyWenJuan;

import java.util.List;

/**
 * Created by javakam on 2017/7/11 0011.
 */
public class WenjuanAdapter extends BaseListAdapter {
    public WenjuanAdapter(Context mContext, List mList, LayoutInflater mInflater) {
        super(mContext, mList, mInflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_my_wenjuan, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((MyWenJuan.MyWenJuans) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(MyWenJuan.MyWenJuans beanWenJuan, ViewHolder holder) {
        holder.tvMyTitle.setText(beanWenJuan.title);
        holder.tvMyTitleGreen.setText(beanWenJuan.jifen);
        holder.tvMyTimeAgo.setText(beanWenJuan.time);
        //type": "1",//问卷状态 1 进行中 2 已完成 3已过期

        if (beanWenJuan.type.equals("3")) {
            holder.tvMyState.setText("已过期");
            holder.tvMyState.setTextColor(mContext.getResources().getColor(R.color.color_999999));
        } else if (beanWenJuan.type.equals("2")) {
            holder.tvMyState.setText("已完成");
            holder.tvMyState.setTextColor(mContext.getResources().getColor(R.color.color_999999));
        } else if (beanWenJuan.type.equals("1")) {
            holder.tvMyState.setText("进行中");
            holder.tvMyState.setTextColor(mContext.getResources().getColor(R.color.yellow));
        }
    }

    protected class ViewHolder {
        private TextView tvMyTitle;
        private TextView tvMyTitleGreen;
        private TextView tvMyTimeAgo;
        private TextView tvMyState;

        public ViewHolder(View view) {
            tvMyTitle = (TextView) view.findViewById(R.id.tvMyTitle);
            tvMyTitleGreen = (TextView) view.findViewById(R.id.tvMyTitleGreen);
            tvMyTimeAgo = (TextView) view.findViewById(R.id.tvMyTimeAgo);
            tvMyState = (TextView) view.findViewById(R.id.tvMyState);
        }
    }
}