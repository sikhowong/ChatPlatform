package com.example.ruidong.sbu_application.chatPlatform.service;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ruidong.sbu_application.R;

public class MyStuffTab extends Fragment  {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_my_stuff, container, false);
        String[] mobileArray = {"These are my post let's check it out !!!!!!!!!!!! ! !!!  !!!!! haha","More of my post , these are great, look at my likes ","Who trynna get lunch?","Blackberry","These are my post let's check it out !!!!!!!!!!!! ! !!!  !!!!! haha","More of my post , these are great, look at my likes ","Who trynna get lunch?","Blackberry","These are my post let's check it out !!!!!!!!!!!! ! !!!  !!!!! haha","More of my post , these are great, look at my likes ","Who trynna get lunch?","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
        RecentPostCustomListAdapter adapter = new RecentPostCustomListAdapter(getActivity(), mobileArray);
        ListView listView = (ListView) V.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        return V;
    }
}