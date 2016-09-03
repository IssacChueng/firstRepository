package com.issac.administrator.classestest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/16.
 */
public class MyBaseAdapter extends BaseAdapter implements AbsListView.OnScrollListener{
    private List<ClassBean> classBeanList;
    private LayoutInflater inflater;
    private BitMapLoader mapLoader;
    private int mStart,mEnd;
    public static String[] URLs;
    private boolean mFirstIn;
    public MyBaseAdapter(Context context, List<ClassBean> classBeanList, ListView listView) {
        this.classBeanList = classBeanList;
        this.inflater = LayoutInflater.from(context);
        mapLoader = new BitMapLoader(listView);

        URLs = new String[classBeanList.size()];
        for (int i = 0; i <URLs.length ; i++) {
            URLs[i] = classBeanList.get(i).getClassUriIcon();
        }
        mFirstIn = true;

        listView.setOnScrollListener(this);
    }

    @Override
    public int getCount() {
        return classBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return classBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item,null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        ClassBean bean = classBeanList.get(position);
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        String url = bean.getClassUriIcon();
        viewHolder.imageView.setTag(url);
        //mapLoader.showImageByThread(viewHolder.imageView,url);
        mapLoader.showImageByAsyncTask(viewHolder.imageView,url);
        //这里需要将imageView设置个tag，只有当tag内容一致时，才显示bitmap；否则在handler中取得的缓冲converntView的image还是旧的，导致刷新几次

        viewHolder.tvTitle.setText(bean.getClassTitle());
        viewHolder.tvContent.setText(bean.getClassContent());
        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE){
            //加载
            mapLoader.loadImages(mStart,mEnd);
        }else{
            //停止加载
            mapLoader.cancelAllTask();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mStart = firstVisibleItem;
        mEnd = mStart+visibleItemCount;
        //第一次显示时候调用
        if (mFirstIn && visibleItemCount > 0){
            mapLoader.loadImages(mStart,mEnd);
            mFirstIn = false;
        }
    }

    class ViewHolder{
        public ImageView imageView;
        public TextView tvTitle;
        public TextView tvContent;
    }
}
