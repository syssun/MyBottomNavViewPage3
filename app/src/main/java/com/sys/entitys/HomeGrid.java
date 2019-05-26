package com.sys.entitys;

public class HomeGrid {
    private Integer id;
    private Integer iamge;
    private String title;
    public HomeGrid(){

    }
    public HomeGrid(Integer image,String title){
            this.iamge = image;
            this.title = title;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIamge() {
        return iamge;
    }

    public void setIamge(Integer iamge) {
        this.iamge = iamge;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
