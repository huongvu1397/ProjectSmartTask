package com.example.a84974.projectsmarttask.Module;

public class BangList {
    private String tieude,icon;

    public BangList(String tieude, String icon) {
        this.tieude = tieude;
        this.icon = icon;
    }

    public BangList(String tieude) {
        this.tieude = tieude;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
