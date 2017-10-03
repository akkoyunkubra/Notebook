package com.kbakkoyungmail.notebook;

/**
 * Created by asus-pc on 9.7.2017.
 */

public class Note {
    private String title;
    private String note;
    private String date;
    private String user;

    public Note() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Note(String title, String note, String date, String user) {
        this.title = title;
        this.note = note;
        this.date = date;
        this.user = user;
    }
}
