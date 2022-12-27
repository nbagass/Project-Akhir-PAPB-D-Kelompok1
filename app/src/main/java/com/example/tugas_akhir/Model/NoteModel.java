package com.example.tugas_akhir.Model;

import android.net.Uri;

public class NoteModel {
    private String title, desc, key, loc;

    public NoteModel(String title, String desc, String key) {
        this.title = title;
        this.desc = desc;
        this.key = key;
    }

    public NoteModel(String title, String desc, String key, String loc) {
        this.title = title;
        this.desc = desc;
        this.key = key;
        this.loc = loc;
    }

    public String getloc() {
        return loc;
    }

    public void setloc(String loc) {
        this.loc = loc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public NoteModel() {
    }

/*    public NoteModel(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
