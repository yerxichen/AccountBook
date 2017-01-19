package com.softwise.adapter;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.accountbook.HomeActivity;
import com.example.accountbook.ImageShowActivity;
import com.example.accountbook.R;
import com.softwise.Util.BitmapConvent;
import com.softwise.dto.Account;

import java.security.acl.Group;
import java.util.ArrayList;

/**
 * Created by softwise on 2016/12/27.
 */

public class AccListViewAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private ArrayList<Account> list;
    private Callback mCallback;

    @Override
    public void onClick(View v) {
        mCallback.click(v);
    }

    public interface Callback {
        public void click(View v);
    }

    public AccListViewAdapter(Context mContext, ArrayList<Account> list, Callback mCallback) {
        this.mContext = mContext;
        this.list = list;
        this.mCallback = mCallback;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_acc_list_group, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.accid = (TextView) convertView.findViewById(R.id.tv_acc_lv_accid);
            viewHolder.accaction = (TextView) convertView.findViewById(R.id.tv_acc_lv_accaction);
            viewHolder.accmoney = (TextView) convertView.findViewById(R.id.tv_acc_lv_accmoney);
            viewHolder.acclist = (TextView) convertView.findViewById(R.id.tv_acc_lv_acclist);
            viewHolder.accsay = (TextView) convertView.findViewById(R.id.tv_acc_lv_accsay);
            viewHolder.acctime = (TextView) convertView.findViewById(R.id.tv_acc_lv_acctime);
            viewHolder.accpic = (ImageView) convertView.findViewById(R.id.iv_photo_head);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.accid.setText(list.get(position).getAccid().toString());
        viewHolder.accaction.setText(list.get(position).getAccaction());
        viewHolder.accmoney.setText(list.get(position).getAccmoney().toString());
        viewHolder.acclist.setText(list.get(position).getAcclist());
        viewHolder.accsay.setText(list.get(position).getAccsay());
        viewHolder.acctime.setText(list.get(position).getAcctime());
        viewHolder.accpic.setImageBitmap(BitmapConvent.convertStringToIcon(list.get(position).getAccpic()));
        viewHolder.accpic.setOnClickListener(this);
        viewHolder.accpic.setTag(position);
        return convertView;
    }

    static class ViewHolder {
        TextView accid;
        TextView accaction;
        TextView accmoney;
        TextView acclist;
        TextView accsay;
        TextView acctime;
        ImageView accpic;
    }
}
