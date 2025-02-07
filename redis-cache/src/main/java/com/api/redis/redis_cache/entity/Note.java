package com.api.redis.redis_cache.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "stream_note")
public class Note implements Serializable {
    @Id
    private String id;
    private String title;
    private String content;
    private Date addDate;
    private boolean live = false;

    Note(){}

    public Note(String id, String title, String content, Date addDate, boolean live) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.addDate = addDate;
        this.live = live;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
