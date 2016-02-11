package com.example.ruidong.sbu_application.chatPlatform.service;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruidong.sbu_application.R;

import java.util.ArrayList;

/**
 * /**This class, is a custom list adapter for the Comments. It extends
 * ArrayAdapter of type posts because it will be stored in the
 * ArrayAdapter, and we want to inherit its methods. It will store
 * specifcally COMMENTSs into the Array Adapter
 *
 *
 * Created by Albert Ibragimov and Sikho Wong on 12/11/2015.
 */
public class CommentsCustomListAdapter extends ArrayAdapter<Comment> {

    private Activity activity;
    //private final String[] web;
    private ArrayList<Comment> comments;
    private static LayoutInflater inflater = null;

    /**
     * Default construtor used to set the comment adapters activity and posts
     * @param activity
     * @param textViewResourceId
     * @param comments
     */
    public CommentsCustomListAdapter(Activity activity, int textViewResourceId, ArrayList<Comment> comments) {
        super(activity, R.layout.recent_post_listview, comments);
        try {
            this.activity = activity;
            this.comments = comments;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }

    }

    /**
     * Static class used within the class, containts text views for the
     * display name, display number, and display likes. Will assist
     * with the getView method
     */
    public static class ViewHolder {
        public TextView displayName;
        public TextView displayNumber;
        public TextView displayLikes;
    }

    /**
     * Overriden methoud used to get the exact view in the COMMENTS adapter.
     * It also sets the position, likes, number and position of the post inside the
     * list.
     *
     * There is also a method inside this method that represents an onClick listener.
     * When a post is clicked, or the heart is clicked, it will increase the number of likes
     * by 1.
     * @param position
     * @param view
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        final ViewHolder holder;

        View rowView = inflater.inflate(R.layout.recent_post_listview, null);
        holder = new ViewHolder();
        holder.displayName = (TextView) rowView.findViewById(R.id.textView1);
        holder.displayNumber = (TextView) rowView.findViewById(R.id.textView2);
        holder.displayLikes = (TextView) rowView.findViewById(R.id.textView7);
        ImageView iv = (ImageView) rowView.findViewById(R.id.imageView2);
        final int p = position;
        iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Toast.makeText(activity, "You Clicked at " + p, Toast.LENGTH_SHORT).show();

                comments.get(p).setLikes(comments.get(p).getLikes()+1);
                reload();
            }
        });

//        LayoutInflater inflater = context.getLayoutInflater();
//        View rowView = inflater.inflate(R.layout.recent_post_listview, null, true);
//        TextView txtTitle = (TextView) rowView.findViewById(R.id.textView1);
//        TextView txtTitle2 = (TextView) rowView.findViewById(R.id.textView2);
//        ImageView iv = (ImageView) rowView.findViewById(R.id.imageView2);
//        final int p = position;
//        iv.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Toast.makeText(context, "You Clicked at " + p, Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
        holder.displayName.setText(comments.get(position).getContent());
        holder.displayNumber.setText(comments.get(position).getDateCreated().toString());
        holder.displayLikes.setText("Likes: " + comments.get(position).getLikes());
        return rowView;
    }
    public void reload(){
        this.notifyDataSetChanged();
    }
    /*
    public Post getItem(int position){

        return posts.get(position);
    }*/
}
