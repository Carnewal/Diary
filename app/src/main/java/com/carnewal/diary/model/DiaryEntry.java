package com.carnewal.diary.model;

import java.io.Serializable;

/**
 * Created by Brecht on 10/01/2016.
 */
public class DiaryEntry implements Serializable{

    private Long id;
    private String title;
    private String content;
    private String dateCreated;

    public DiaryEntry(String title, String content, String dateCreated) {
        this.title = title;
        this.content = content;
        this.dateCreated = dateCreated;
    }

    public DiaryEntry(Long id, String title, String content, String date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateCreated = date;
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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }
}