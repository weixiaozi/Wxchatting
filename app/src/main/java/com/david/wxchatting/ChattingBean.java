package com.david.wxchatting;

/**
 * Created by dell on 2018/3/22.
 */

public class ChattingBean {
    public static final int LOADING = 0;
    public static final int OWN_TXT = 1;
    public static final int OTHER_TXT = 2;
    private int type;//0:other;1:self;2;loading
    private String content;
    private String time;
    private String name;

    public ChattingBean(int type, String content, String time, String name) {
        this.type = type;
        this.content = content;
        this.time = time;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
