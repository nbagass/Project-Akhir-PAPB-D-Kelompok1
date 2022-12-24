package com.example.tugas_akhir.Model;

public class ModulModel {
    private String title, desc;

    public ModulModel() {
    }

    public ModulModel(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

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
