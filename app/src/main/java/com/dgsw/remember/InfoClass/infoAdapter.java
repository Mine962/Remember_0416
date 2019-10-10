package com.dgsw.remember.InfoClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dgsw.remember.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class infoAdapter extends BaseAdapter {
    private Context mContext;
    private List<infoData> listItem = null;
    LayoutInflater mLayoutInflater;
    ViewHolder holder;

    public infoAdapter(Context context, ArrayList<infoData> listItem) {
        mContext = context;
        this.listItem = listItem;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return listItem.size();
    }

    public infoData getItem(int i) {
        return listItem.get(i);
    }

    public long getItemId(int i) {
        return i;
    }

    class ViewHolder {
        public TextView nick;
        public TextView message;
        public CircleImageView ci;
        public TextView ctime;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_1, null);

            holder.nick = (TextView) convertView.findViewById(R.id.nick);
            holder.message = (TextView) convertView.findViewById(R.id.message);
            holder.ci = (CircleImageView) convertView.findViewById(R.id.messengerImageView);
            holder.ctime = (TextView) convertView.findViewById(R.id.ctime);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        infoData mData = listItem.get(position);

        holder.nick.setText(mData.getInfoName());
        holder.message.setText(mData.getInformation());
        holder.ctime.setText(mData.getWhen());
        Glide.with(mContext).load(mData.getImage_()).into(holder.ci);

        return convertView;
    }
}