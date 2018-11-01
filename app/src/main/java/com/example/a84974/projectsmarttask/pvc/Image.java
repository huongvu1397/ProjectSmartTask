package com.example.a84974.projectsmarttask.pvc;

public class Image {
    private int Id;
    private String Title;
    private String Description;
    private byte[] image;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Image(int id, String title, String description, byte[] image) {

        Id = id;
        Title = title;
        Description = description;
        this.image = image;
    }

}
