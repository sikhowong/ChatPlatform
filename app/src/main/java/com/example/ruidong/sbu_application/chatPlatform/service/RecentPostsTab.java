package com.example.ruidong.sbu_application.chatPlatform.service;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ruidong.sbu_application.R;

public class RecentPostsTab extends Fragment  {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_recent_posts, container, false);
        String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};

        Post[] posts = {
                new Post(),
                new Post(),
                new Post(),
                new Post(),
                new Post(),
                new Post(),
        };

        for (int i = 0; i < posts.length; i++) {
            posts[i].setContent("" + i);
        }

        RecentPostCustomListAdapter adapter = new RecentPostCustomListAdapter(getActivity(), mobileArray);



        ListView listView = (ListView) V.findViewById(R.id.listView);
        //ArrayAdapter<Post> adapter = new ArrayAdapter<Post>(getActivity(), R.layout.recent_post_listview, posts);


        //View V2 = inflater.inflate(R.layout.recent_post_listview, container, false);

        listView.setAdapter(adapter);
        return V;
    }
}