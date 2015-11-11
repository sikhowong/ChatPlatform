package com.example.ruidong.sbu_application.chatPlatform.service;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TabHost;


import com.example.ruidong.sbu_application.R;
import com.example.ruidong.sbu_application.framework.NavigationActivity;
/**
 * Created by MasterAl on 11/7/2015.
 */
public class ChatMainFragment extends Fragment {
    // Fragment TabHost as mTabHost
    private FragmentTabHost mTabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.chat_main_fragment, containter, false);


        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator("Recent Posts"),
                RecentPostsTab.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator("Popular Posts"),
                PopularPostsTab.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentd").setIndicator("My Stuff"),
                MyStuffTab.class, null);


        return rootView;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        // inflater = getActivity().getMenuInflater();
       // inflater.inflate(R.menu.chat_compose_action_menu, menu);
        menu.clear();

        ((NavigationActivity)getActivity()).getMenuInflater().inflate(R.menu.chat_compose_action_menu, menu);
        //super.onCreateOptionsMenu(menu, inflater);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.miCompose:
               // composeMessage();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
