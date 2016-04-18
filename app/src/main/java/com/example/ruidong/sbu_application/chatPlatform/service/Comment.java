package com.example.ruidong.sbu_application.chatPlatform.service;

import android.content.Context;
import android.net.wifi.WifiManager;

import java.util.Date;

/**
 * This class represents the object Comment. A user can create a comment
 * and add itto the listview on the page associated with a post.
 * This classes data fields will then be stored onto the database as a table.
 *
 * Created by Albert Ibragimov and Sikho Wong on 12/11/2015.
 */
public class Comment {

    //The data fields associated with a Comment
    private String postID;
    private String userID;
    private Date dateCreated;
    private String content;
    private int likes;
    private String id ;
    private String date;
    /**
     * Constructor for making a comment. When a user clicks on a post, they
     * can decide to comment. When doing that, this constructor will be called
     * to make a new comment. It will consist of content and likes.
     * C will be set to the content and l will be set to likes.
     * @param c
     * @param l
     */
    public Comment(String id , String c , int like, String user, String d, String postID){
        //dateCreated = new Date();
        this.postID = postID;
        this.id = id;
        this.content = c;
        this.likes = like;
        this.userID = user;
        this.date = d;
    }
    public String getID(){
        return id;
    }
    public String getDate(){
        return date;
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
    public String getPostID() {
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
    public void setPostID(String postID) {
        this.postID = postID;
    }

    /**
     * Method to set the ID of the user to userID
     * @param userID
     */
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
