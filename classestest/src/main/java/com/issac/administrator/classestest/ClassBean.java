package com.issac.administrator.classestest;

/**
 * Created by Administrator on 2016/8/16.
 */
public class ClassBean {
    private String classUriIcon;
    private String classTitle;
    private String classContent;

    public ClassBean(String classUriIcon, String classTitle, String classContent) {
        this.classUriIcon = classUriIcon;
        this.classTitle = classTitle;
        this.classContent = classContent;
    }

    public ClassBean() {
    }

    public String getClassUriIcon() {
        return classUriIcon;
    }

    public void setClassUriIcon(String classUriIcon) {
        this.classUriIcon = classUriIcon;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public String getClassContent() {
        return classContent;
    }

    public void setClassContent(String classContent) {
        this.classContent = classContent;
    }
}
