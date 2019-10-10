package com.dgsw.remember.Write_0416;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dgsw.remember.R;

import java.util.ArrayList;
import java.util.List;

public class ReadAdapter extends BaseAdapter {
    private Context mContext;
    private List<Data_write> listItem = null;
    LayoutInflater mLayoutInflater;
    ViewHolder holder;

    public ReadAdapter(Context context, ArrayList<Data_write> listItem) {
        mContext = context;
        this.listItem = listItem;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return listItem.size();
    }

    public Data_write getItem(int i) {
        return listItem.get(i);
    }

    public long getItemId(int i) {
        return i;
    }

    class ViewHolder {
        public TextView Messege;
        public TextView times;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.memo_item, null);

            holder.Messege = (TextView) convertView.findViewById(R.id.content);
            holder.times = (TextView) convertView.findViewById(R.id.times);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Data_write mData = listItem.get(position);

        holder.Messege.setText(mData.getMessage());
        holder.times.setText(mData.gettime());


        return convertView;
    }
}