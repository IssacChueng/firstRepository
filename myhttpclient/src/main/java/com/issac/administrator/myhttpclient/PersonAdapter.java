package com.issac.administrator.myhttpclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public class PersonAdapter extends BaseAdapter {
    private List<Person> persons;
    private Context context;
    private LayoutInflater inflater;

    public PersonAdapter(List<Person> persons, Context context) {
        this.persons = persons;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHandler vHanlHandler;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item,null);
            ImageView ivIcon = (ImageView) convertView.findViewById(R.id.personIcon);
            TextView tvName = (TextView) convertView.findViewById(R.id.name);
            TextView tvAge = (TextView) convertView.findViewById(R.id.age);
            TextView tvSchool1 = (TextView) convertView.findViewById(R.id.school1);
            TextView tvSchool2 = (TextView) convertView.findViewById(R.id.school2);
            vHanlHandler = new ViewHandler(ivIcon,tvName,tvAge,tvSchool1,tvSchool2);
            convertView.setTag(vHanlHandler);
        }else{
            vHanlHandler = (ViewHandler) convertView.getTag();
        }

        Person person = persons.get(position);
        vHanlHandler.tvName.setText(person.getName());
        vHanlHandler.tvAge.setText(person.getAge()+"");
        List<SchoolInfo> schoolInfos = person.getSchoolInfo();
        vHanlHandler.tvSchool1.setText(schoolInfos.get(0).getName());
        vHanlHandler.tvSchool2.setText(schoolInfos.get(1).getName());
        return convertView;
    }

    class ViewHandler{
        private ImageView ivIcon;
        private TextView tvName;
        private TextView tvAge;
        private TextView tvSchool1;
        private TextView tvSchool2;

        public ViewHandler(ImageView ivIcon, TextView tvName, TextView tvAge, TextView tvSchool1, TextView tvSchool2) {
            this.ivIcon = ivIcon;
            this.tvName = tvName;
            this.tvAge = tvAge;
            this.tvSchool1 = tvSchool1;
            this.tvSchool2 = tvSchool2;
        }
    }
}
