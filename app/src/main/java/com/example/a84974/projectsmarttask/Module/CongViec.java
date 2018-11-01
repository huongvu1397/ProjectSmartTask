package com.example.a84974.projectsmarttask.Module;

public class CongViec {
    private int idCV;
    private String tenCV;
    private boolean isSelected;

    public CongViec(int idCV, String tenCV,boolean isSelected) {
        this.idCV = idCV;
        this.tenCV = tenCV;
        this.isSelected = isSelected;
    }

    public CongViec() {
    }

    public int getIdCV() {
        return idCV;
    }

    public void setIdCV(int idCV) {
        this.idCV = idCV;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
