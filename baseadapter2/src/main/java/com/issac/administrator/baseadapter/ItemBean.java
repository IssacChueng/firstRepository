package com.issac.administrator.baseadapter;

/**
 * Created by Administrator on 2016/8/15.
 */
public class ItemBean {
    private int itemResource;
    private String itemTitle;
    private String itemContent;

    public ItemBean(int itemResource, String itemTitle, String itemContent) {
        this.itemResource = itemResource;
        this.itemTitle = itemTitle;
        this.itemContent = itemContent;
    }

    public int getItemResource() {
        return itemResource;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemResource(int itemResource) {
        this.itemResource = itemResource;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }
}
