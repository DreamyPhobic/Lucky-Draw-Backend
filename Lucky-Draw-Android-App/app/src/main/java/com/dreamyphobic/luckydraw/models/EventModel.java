package com.dreamyphobic.luckydraw.models;

/*
  Created by Harsh Gupta on 24/04/2021.
*/

public class EventModel {

    public EventModel(){

    }

    private String _id;
    private String Reward;
    private String Time;
    private long Status;
    private WinnerModel Winner;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getReward() {
        return Reward;
    }

    public void setReward(String reward) {
        Reward = reward;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public long getStatus() {
        return Status;
    }

    public void setStatus(long status) {
        Status = status;
    }


    public WinnerModel getWinner() {
        return Winner;
    }

    public void setWinner(WinnerModel winner) {
        Winner = winner;
    }
}
