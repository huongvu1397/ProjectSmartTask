package com.example.a84974.projectsmarttask.Module;

public class thongBao {
    private String ava,name,comment,idThe,milis;

    public String getIdThe() {
        return idThe;
    }

    public String getMilis() {
        return milis;
    }

    public void setMilis(String milis) {
        this.milis = milis;
    }

    public void setIdThe(String idThe) {
        this.idThe = idThe;
    }

    public String getAva() {
        return ava;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public thongBao(String idThe ,String name, String comment, String ava,String milis) {
        this.ava = ava;
        this.name = name;
        this.comment = comment;
        this.idThe = idThe;
        this.milis = milis;
    }

}
