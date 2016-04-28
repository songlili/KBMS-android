package com.cwp.kbms.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cwp.kbms.Fragment.HomeFragment;
import com.cwp.kbms.R;

import java.util.ArrayList;

/**
 * Created by 曹伟鹏 on 2016/4/19.
 */
//适配器类
public class FileListAdapter extends BaseAdapter {

    private int picID = 0;
    private ArrayList<String> list;
    private Context context;

    static class ViewHolder {
        public ImageView img;
        public TextView floderName;
    }

    public FileListAdapter(ArrayList<String> list, Context context, int Rid) {
        this.list = list;
        this.context = context;
        this.picID = Rid;
    }

    public FileListAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 获取视图
     *
     * @param position    位置
     * @param convertView 转变的视图
     * @param parent      父视图
     * @return 视图
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater layoutInflater = LayoutInflater.from(context);


        if (convertView == null) {
            //// TODO: 2016/4/20
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.filefloder_list_item_layout, null);
            holder.img = (ImageView) convertView.findViewById(R.id.fileImageView);
            holder.floderName = (TextView) convertView.findViewById(R.id.fileTextView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (picID != 0) {
            holder.img.setImageResource(picID);
        }
        holder.floderName.setText(list.get(position));
        return convertView;
    }

}
