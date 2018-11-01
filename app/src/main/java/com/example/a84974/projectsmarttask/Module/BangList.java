package com.example.a84974.projectsmarttask.Module;

public class BangList {
    private String tieude,icon,idthe;

    public BangList(String idthe,String tieude, String icon) {
        this.tieude = tieude;
        this.idthe = idthe;
        this.icon = icon;
    }

    public BangList(String idthe,String tieude) {
        this.tieude = tieude;
        this.idthe = idthe;
    }

    public String getIdthe() {
        return idthe;
    }

    public void setIdthe(String idthe) {
        this.idthe = idthe;
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
