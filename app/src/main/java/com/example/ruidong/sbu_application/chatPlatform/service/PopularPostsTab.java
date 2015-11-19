package com.example.ruidong.sbu_application.chatPlatform.service;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ruidong.sbu_application.R;

import java.util.ArrayList;

import static com.example.ruidong.sbu_application.R.layout.recent_post_listview;

public class PopularPostsTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_popular_posts, container, false);
        String[] mobileArray = {"HEllow , school is mad work school is mad work school is mad work", "What's good people, school is mad work", "Who trynna get lunch?", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X"};

        ArrayList<Post> posts = new ArrayList<Post>();
        posts.add(new Post());
        posts.add(new Post());
        posts.add(new Post());
        posts.add(new Post());
        posts.add(new Post());


        for( int i = 0; i < posts.size(); i++) {
            posts.get(i).setContent("This is post: " + i);
        }


        PopularPostCustomListAdapter adapter = new PopularPostCustomListAdapter(getActivity(), recent_post_listview, posts);
        ListView listView = (ListView) V.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        return V;
    }
}