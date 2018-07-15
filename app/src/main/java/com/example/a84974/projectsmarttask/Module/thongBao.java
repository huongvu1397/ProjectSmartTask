package com.example.a84974.projectsmarttask.Module;

public class thongBao {
    private String ava,name,comment;

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

    public thongBao(String name, String comment, String ava) {
        this.ava = ava;
        this.name = name;
        this.comment = comment;
    }

}
