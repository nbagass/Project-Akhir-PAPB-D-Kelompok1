package com.example.tugas_akhir.Model;

public class NoteModel {
    private String title, desc, key;

    public NoteModel(String title, String desc, String key) {
        this.title = title;
        this.desc = desc;
        this.key = key;
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
