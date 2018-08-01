package com.example.a84974.projectsmarttask.Module;

public class CongViec {
    private int idCV;
    private String tenCV;

    public CongViec(int idCV, String tenCV) {
        this.idCV = idCV;
        this.tenCV = tenCV;
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
}
