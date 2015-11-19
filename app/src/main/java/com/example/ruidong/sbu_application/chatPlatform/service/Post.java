package com.example.ruidong.sbu_application.chatPlatform.service;

import android.content.Context;
import android.net.wifi.WifiManager;

import java.util.Date;

/**
 * Created by Albert on 11/18/15.
 */
public class Post {

    private int postID;
    private String userID;
    private Date dateCreated;
    private String content;
    private int likes;

    public Post(){
        dateCreated = new Date();
        content = "";
        likes = 0;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public int getLikes() {
        return likes;
    }

    public int getPostID() {
        return postID;
    }

    public String getContent() {
        return content;
    }

    public String getUserID() {
        return userID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getMacAddress(Context context) {
        WifiManager wimanager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String macAddress = wimanager.getConnectionInfo().getMacAddress();
        if (macAddress == null) {
            macAddress = "Device don't have mac address or wi-fi is disabled";
        }
        return macAddress;
    }

}
