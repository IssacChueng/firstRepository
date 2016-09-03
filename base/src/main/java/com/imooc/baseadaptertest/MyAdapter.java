package com.imooc.baseadaptertest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    // 映射数据
    private List<ItemBean> mDataList;
    private long mSumTime;

    public MyAdapter(Context context, List<ItemBean> list) {
        mLayoutInflater = LayoutInflater.from(context);
        mDataList = list;
    }

    // 获取数据量
    @Override
    public int getCount() {
        return mDataList.size();
    }

    // 获取对应ID项对应的Item
    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    // 获取对应项ID
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 逗比式 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        // 获取纳秒时间 更加精确
        long start = System.nanoTime();
        // 由于我们只需要将XML转化为View，并不涉及到具体的布局，所以第二个参数通常设置为null
        View view = mLayoutInflater.inflate(R.layout.item, null);
        // 实例化控件
        ImageView itemImage = (ImageView) view.findViewById(R.id.iv_image);
        TextView itemTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView itemContent = (TextView) view.findViewById(R.id.tv_content);
        // 取出bean对象
        ItemBean bean = mDataList.get(position);
        // 设置控件的数据
        itemImage.setImageResource(bean.itemImageResid);
        itemTitle.setText(bean.itemTitle);
        itemContent.setText(bean.itemContent);
        long end = System.nanoTime();
        long dValue = end - start;
        mSumTime += dValue;
        // 输出每次getView消耗的时间和
        Log.d("xys", String.valueOf(mSumTime));
        return view;
        // 逗比式 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>24409529

        // 普通式 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        // 获取纳秒时间 更加精确
//        long start = System.nanoTime();
//        if (convertView == null) {
        // 由于我们只需要将XML转化为View，并不涉及到具体的布局，所以第二个参数通常设置为null
//            convertView = mLayoutInflater.inflate(R.layout.item, null);
//        }
//        ImageView itemImage = (ImageView) convertView.findViewById(R.id.iv_image);
//        TextView itemTitle = (TextView) convertView.findViewById(R.id.tv_title);
//        TextView itemContent = (TextView) convertView.findViewById(R.id.tv_content);
//        // 取出bean对象
//        ItemBean bean = mDataList.get(position);
        // 设置控件的数据
//        itemImage.setImageResource(bean.itemImageResid);
//        itemTitle.setText(bean.itemTitle);
//        itemContent.setText(bean.itemContent);
//        long end = System.nanoTime();
//        long dValue = end - start;
//        mSumTime += dValue;
        // 输出每次getView消耗的时间和
//        Log.d("xys", String.valueOf(mSumTime));
//        return convertView;
        // 普通式 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>19271161

        // 文艺式 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        // 获取纳秒时间 更加精确
//        long start = System.nanoTime();
//        ViewHolder holder = null;
//        if (convertView == null) {
//            holder = new ViewHolder();
        // 由于我们只需要将XML转化为View，并不涉及到具体的布局，所以第二个参数通常设置为null
//            convertView = mLayoutInflater.inflate(R.layout.item, null);
//            holder.img = (ImageView) convertView.findViewById(R.id.iv_image);
//            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
//            holder.content = (TextView) convertView.findViewById(R.id.tv_content);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
        // 取出bean对象
//        ItemBean bean = mDataList.get(position);
        // 设置控件的数据
//        holder.img.setImageResource(bean.itemImageResid);
//        holder.title.setText(bean.itemTitle);
//        holder.content.setText(bean.itemContent);
//        long end = System.nanoTime();
//        long dValue = end - start;
//        mSumTime += dValue;
        // 输出每次getView消耗的时间和
//        Log.d("xys", String.valueOf(mSumTime));
//        return convertView;
        // 文艺式 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>16325820
    }

    // ViewHolder用于缓存控件
    class ViewHolder{
        public ImageView img;
        public TextView title;
        public TextView content;
    }
}
