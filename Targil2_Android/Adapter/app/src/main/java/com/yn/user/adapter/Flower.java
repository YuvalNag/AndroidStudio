package com.yn.user.adapter;

import java.io.Serializable;

/**
 * Created by USER on 02/11/2017.
 */

public class Flower implements Serializable {

    private  String Name ;
    private int ImageId;
    private  String Description;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }

    private double Rating;

    public Flower(String _name, int _imageId,double _rating,String _description)
    {
        Name = _name;
        ImageId = _imageId;
        Rating=_rating;
        Description =_description;

    }
    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
