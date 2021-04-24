package com.dreamyphobic.luckydraw.models;

/*
  Created by Harsh Gupta on 24/04/2021.
 */

public class WinnerModel {

    public WinnerModel(){

    }

    private String _id;
    private String name;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
