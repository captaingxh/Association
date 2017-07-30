package com.future.association.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.future.association.R;
import com.future.association.personal.entity.MyJianDu;

import java.util.List;

/**
 * Created by javakam on 2017/7/10 0010.
 */
public class JianDuAdapter extends BaseListAdapter {
    public JianDuAdapter(Context mContext, List mList, LayoutInflater mInflater) {
        super(mContext, mList, mInflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_my_jiandu, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((MyJianDu.MyJianDus) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(MyJianDu.MyJianDus entity, ViewHolder holder) {
//        Glide.with(mContext).load(entity.getImgUrl()).crossFade().into(holder.imgMyJD);
        holder.imgMyJD.setImageResource(R.drawable.ic_birds);
        holder.tvMyJDTitle.setText(entity.title);
        holder.tvMyJDCreatetime.setText(entity.create_time);
        holder.tvMyJDType.setText(entity.type);
        holder.tvMyJDAddress.setText(entity.address);
        holder.tvMyJDContent.setText(entity.reason);
    }

    protected class ViewHolder {
        private ImageView imgMyJD;
        private TextView tvMyJDTitle;
        private TextView tvMyJDCreatetime;
        private TextView tvMyJDType;
        private TextView tvMyJDAddress;
        private TextView tvMyJDContent;

        public ViewHolder(View view) {
            imgMyJD = (ImageView) view.findViewById(R.id.img_myJD);
            tvMyJDTitle = (TextView) view.findViewById(R.id.tv_myJD_title);
            tvMyJDCreatetime = (TextView) view.findViewById(R.id.tv_myJD_createtime);
            tvMyJDType = (TextView) view.findViewById(R.id.tv_myJD_type);
            tvMyJDAddress = (TextView) view.findViewById(R.id.tv_myJD_address);
            tvMyJDContent = (TextView) view.findViewById(R.id.tv_myJD_content);
        }
    }
}