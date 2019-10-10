package com.dgsw.remember.InfoClass;

public class infoData {
    private String infoName;
    private String information;
    private int image_;
    private String when;

    public infoData() {
    }

    public infoData(String infoName, String information, int image_, String when) {
        this.infoName = infoName;
        this.information = information;
        this.image_ = image_;
        this.when = when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    public void setImage_(int image_) {
        this.image_ = image_;
    }

    public String getInformation() {
        return information;
    }

    public String getWhen() {
        return when;
    }

    public String getInfoName() {
        return infoName;
    }

    public int getImage_() {
        return image_;
    }
}

