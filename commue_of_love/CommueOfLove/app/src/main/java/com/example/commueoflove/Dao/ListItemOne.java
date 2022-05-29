package com.example.commueoflove.Dao;

public class ListItemOne {
    private int img;
    private String title,level,kind,available,region,name,detail,time;

    public ListItemOne(int img, String title, String level, String kind, String available, String region, String name, String detail) {
        this.img = img;
        this.title = title;
        this.level = level;
        this.kind = kind;
        this.available = available;
        this.region = region;
        this.name = name;
        this.detail = detail;
    }

    public ListItemOne(String title, String level, String kind, String available, String region, String name, String detail, String time) {
        this.title = title;
        this.level = level;
        this.kind = kind;
        this.available = available;
        this.region = region;
        this.name = name;
        this.detail = detail;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
