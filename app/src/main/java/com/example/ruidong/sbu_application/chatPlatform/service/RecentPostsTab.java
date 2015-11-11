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

        RecentPostCustomListAdapter adapter = new RecentPostCustomListAdapter(getActivity(), mobileArray);

        //ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.recent_post_listview, mobileArray);

        ListView listView = (ListView) V.findViewById(R.id.listView);

        //View V2 = inflater.inflate(R.layout.recent_post_listview, container, false);

        listView.setAdapter(adapter);
        return V;
    }
}