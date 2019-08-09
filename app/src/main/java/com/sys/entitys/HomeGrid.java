package com.sys.entitys;

public class HomeGrid {
    private Integer id;
    private Integer iamge;
    private String title;
    private String ctl;
    private String baseUrl = "http://192.168.6.131:8080/ctl/";

    public String getCtl() {
        return ctl;
    }

    public void setCtl(String ctl) {
        this.ctl = ctl;
    }

    public String getBaseUrl() {
        return baseUrl="http://192.168.6.131:8080/ctl/";
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public HomeGrid(){

    }
    public HomeGrid(Integer image,String title,String ctl){
            this.iamge = image;
            this.title = title;
            this.ctl = ctl;
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
