package com.issac.administrator.myhttpclient;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class TestJson {
	public static void main(String[] args){
		Result result = new Result();
		result.setResult(1);
		List<Person> list = new ArrayList<Person>();
		result.setPersonData(list);
		Person person1 = new Person();
		person1.setName("nate");
		person1.setAge(12);
		person1.setUrl("http://imgsrc.baidu.com/forum/w%3D580%3B/sign=fadd2089ba119313c743ffb855030dd7/e61190ef76c6a7ef6785466df5faaf51f2de66de.jpg");
		SchoolInfo schoolInfo1 = new SchoolInfo();
		schoolInfo1.setName("qinghua");
		SchoolInfo schoolInfo2 = new SchoolInfo();
		schoolInfo2.setName("beida");
		List<SchoolInfo> schoolInfos = new ArrayList<SchoolInfo>();
		schoolInfos.add(schoolInfo1);
		schoolInfos.add(schoolInfo2);
		person1.setSchoolInfo(schoolInfos);
		list.add(person1);
		
		Person person2 = new Person();
		person2.setName("fuck");
		person2.setAge(21);
		person2.setUrl("http://v1.qzone.cc/avatar/201305/15/13/18/51931a8ad0949020.jpg%21200x200.jpg");
		SchoolInfo schoolInfo3 = new SchoolInfo();
		schoolInfo3.setName("shandong");
		SchoolInfo schoolInfo4 = new SchoolInfo();
		schoolInfo4.setName("jinhua");
		List<SchoolInfo> schoolInfos2 = new ArrayList<SchoolInfo>();
		schoolInfos2.add(schoolInfo3);
		schoolInfos2.add(schoolInfo4);
		person2.setSchoolInfo(schoolInfos2);
		list.add(person2);
		
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(result));
	}
}
