package com.example.ruidong.sbu_application.chatPlatform.service;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruidong.sbu_application.R;

import java.util.ArrayList;

/**
 * Created by shw on 11/11/2015.
 */
public class RecentPostCustomListAdapter extends ArrayAdapter<Post> {
    private Activity activity;
    private ArrayList<Post> posts;
   // private final String[] web;
    private static LayoutInflater inflater = null;
    public RecentPostCustomListAdapter(Activity activity, ArrayList<Post> posts) {
        super(activity, R.layout.recent_post_listview, posts);
        try {
            this.activity = activity;
            this.posts = posts;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }


    }

    public static class ViewHolder {
        public TextView displayName;
        public TextView displayNumber;
        public TextView displayLikes;
    }


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
                posts.get(p).setLikes(posts.get(p).getLikes()+1);
                reload();

            }
        });

        holder.displayName.setText(posts.get(position).getContent());
        holder.displayNumber.setText(posts.get(position).getDateCreated().toString());
        holder.displayLikes.setText("Likes: " + posts.get(position).getLikes());
        return rowView;
    }

    public void reload(){
        this.notifyDataSetChanged();
    }

    public Post getItem(int position){
        return posts.get(position);
    }
}
