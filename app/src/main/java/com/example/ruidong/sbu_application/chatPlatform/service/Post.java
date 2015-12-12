package com.example.ruidong.sbu_application.chatPlatform.service;

import android.content.Context;
import android.net.wifi.WifiManager;

import java.util.Date;

/**
 * Created by Albert and Sikho Wong on 11/18/15.
 * This class represents the object Post. A user can create a post
 * and add itto the listview on the main page. This classes data fields
 * will then be stored onto the database as a table.
 */
public class Post {

    private int postID;
    private String userID;
    private Date dateCreated;
    private String content;
    private int likes;

    /**
     * Defauct construction used in making a new post. sets the dateCreated to the
     * current date, the content C will be the content of the post, and the likes will
     * be an integer noted by l
     * @param c
     * @param l
     */
    public Post(String c , int l){
        dateCreated = new Date();
        content = c;
        likes = l;
    }

    /**
     * Method to return the date
     * @return
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Method to return the number of likes
     * @return
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Method to return the postID
     * @return
     */
    public int getPostID() {
        return postID;
    }

    /**
     * Method to return the content of the post
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * Method to return the ID of the user
     * @return
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Method to set the content of the post to content
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Method to set the date created of the post to dateCreated.
     * @param dateCreated
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Method to set the likes of the post to likes
     * @param likes
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    /**
     * Method to set the ID of the post to postID
     * @param postID
     */
    public void setPostID(int postID) {
        this.postID = postID;
    }

    /**
     * Method to set the ID of the user to userID
     * @param userID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Method to retrieve the MacAddress of the phone. This will be useful
     * in determining the ID of the phone, basically the user.
     * @param context
     * @return
     */
    public String getMacAddress(Context context) {
        WifiManager wimanager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String macAddress = wimanager.getConnectionInfo().getMacAddress();
        if (macAddress == null) {
            macAddress = "Device don't have mac address or wi-fi is disabled";
        }
        return macAddress;
    }

}
