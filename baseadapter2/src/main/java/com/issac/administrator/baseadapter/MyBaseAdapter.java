package com.issac.administrator.baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/15.
 */
public class MyBaseAdapter extends BaseAdapter{

    private List<ItemBean> itemBeanList;
    private LayoutInflater mInflater;

    public MyBaseAdapter(Context context,List<ItemBean> itemBeanList) {
        this.itemBeanList = itemBeanList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = mInflater.inflate(R.layout.item,null);
//        ImageView imageView = (ImageView) view.findViewById(R.id.img);
//        TextView title = (TextView) view.findViewById(R.id.tvTitle);
//        TextView content = (TextView) view.findViewById(R.id.tvContent);
//        ItemBean itemBean = itemBeanList.get(position);
//        imageView.setImageResource(itemBean.getItemResource());
//        title.setText(itemBean.getItemTitle());
//        content.setText(itemBean.getItemContent());
        //没有用到缓存机制，不推荐


        /**
         * 普通
         */
        /*if (convertView == null){
            convertView = mInflater.inflate(R.layout.item,null);
        }//避免创建大量View
        ImageView imageView = (ImageView) convertView.findViewById(R.id.img);
        TextView title = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView content = (TextView) convertView.findViewById(R.id.tvContent);
        ItemBean itemBean = itemBeanList.get(position);
        imageView.setImageResource(itemBean.getItemResource());
        title.setText(itemBean.getItemTitle());
        content.setText(itemBean.getItemContent());*/
        ViewHolder viewHolder;
        //高级
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item,null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.img);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.content = (TextView) convertView.findViewById(R.id.tvContent);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ItemBean bean = itemBeanList.get(position);
        viewHolder.imageView.setImageResource(bean.getItemResource());
        viewHolder.title.setText(bean.getItemTitle());
        viewHolder.content.setText(bean.getItemContent());



        return convertView;
    }
    class ViewHolder{
        public ImageView imageView;
        public TextView title;
        public TextView content;
    }
}
