package com.yn.user.adapter;

/**
 * Created by USER on 02/11/2017.
 */

public class Flower {

    private  String Name , ImageSrc;
    public Flower(String _name, String _imagePath)
    {
        Name = _name;
        ImageSrc = _imagePath;
    }
    public String getImageSrc() {
        return ImageSrc;
    }

    public void setImageSrc(String imageSrc) {
        ImageSrc = imageSrc;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
