package com.example.ruidong.sbu_application.chatPlatform.service;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ruidong.sbu_application.R;
import com.example.ruidong.sbu_application.framework.NavigationActivity;
import com.example.ruidong.sbu_application.framework.common.tool.FragmentIdPair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Albert and Sikho Wong on 11/11/2015.
 * This the class that represents all of the data that will
 * be going into the Recent Posts Tab. This class extends
 * Fragment as we will be using a Fragment to generate
 * the Interface.
 */

public class RecentPostsTab extends Fragment  {
    Fragment MenuFragment = NavigationActivity.MenuFragment;

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     * This is the overriden method for the Recent post Fragment class used to
     * create and returns the view hierarchy associated with the fragment.
     * Creats the text views as well as paste exactly what it will contain.
     * The purpose of this method in future will consist of the posts
     * information, the number of likes it contains, and how many replys
     * it has as well. The reply number will be added in future tests.
     *
     * Json formatting will also be done towards the bottom of the method. This will be
     * used to send the data to the database, and if we want to parse it for reloading,
     * we will have the JSON file formatted data to do so
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_recent_posts, container, false);
        String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
        ArrayList<Post> posts = new ArrayList<Post>();

        String jsonString = "";
        try {
            JSONArray jsonArray = new JSONArray();
            for(int i =0  ; i < 12; i++){
                JSONObject jsonObj = new JSONObject();  // these 4 files add jsonObject to jsonArray
                jsonObj.put("content", "New post ! Lorem Ipsum !" + i);
                jsonObj.put("dateCreated", "2015-09-12 09:12:"+i);
                jsonObj.put("likes", i+3);
                jsonArray.put(jsonObj);
            }
            int count = jsonArray.length(); // get totalCount of all jsonObjects
            for(int i=0 ; i< count; i++){   // iterate through jsonArray
                JSONObject jsonObject = jsonArray.getJSONObject(i);  // get jsonObject @ i position
                System.out.println("jsonObject " + i + ": " + jsonObject.getString("content") + "  " + jsonObject.getString("dateCreated"));
                Log.w("myapp", "jsonObject " + i + ": " + jsonObject.getString("content") + "  " + jsonObject.getString("dateCreated"));
                posts.add(new Post(jsonObject.getString("content"), jsonObject.getInt("likes")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
/*
        Post[] posts = {
                new Post("a",1),
                new Post("a",1),
                new Post("a",1),
                new Post("a",1),
                new Post("a",1),
                new Post("a",1),
        };

        for (int i = 0; i < posts.length; i++) {
            posts[i].setContent("" + i);
        }
*/
       // final RecentPostCustomListAdapter adapter = new RecentPostCustomListAdapter(getActivity(), posts);
        final RecentPostCustomListAdapter adapter = new RecentPostCustomListAdapter(getActivity(), ChatMainFragment.postList);
        ListView listView = (ListView) V.findViewById(R.id.listView);
        //ArrayAdapter<Post> adapter = new ArrayAdapter<Post>(getActivity(), R.layout.recent_post_listview, posts);
        //View V2 = inflater.inflate(R.layout.recent_post_listview, container, false);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * This overriden method is a event that triggers an item click. Use
             * this method to determine the action delt when a user clicks
             * on a specific item
             * @param parent
             * @param v
             * @param position
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Post item = adapter.getItem(position);
                Toast.makeText(getActivity(), "You Clicked at " + position + " " + item.getContent(), Toast.LENGTH_SHORT).show();

                //new fragment to the view

                if (MenuFragment != null) {
                    FragmentTransaction tran7 = getActivity().getSupportFragmentManager().beginTransaction().remove(MenuFragment);
                    tran7.commit();
                }
                if (!NavigationActivity.backButtonStack.isEmpty()) {
                    FragmentTransaction tran = getActivity().getSupportFragmentManager().beginTransaction().remove(NavigationActivity.backButtonStack.peek().getFragment());
                    tran.commit();
                }
                ViewPostFragment viewPostFragment = new ViewPostFragment();
                viewPostFragment.setPost(item);

                MenuFragment = viewPostFragment;
                FragmentTransaction view_post_tran = getActivity().getSupportFragmentManager().beginTransaction().add(R.id.CourseHistory_container, MenuFragment);
                FragmentIdPair newPostPair = new FragmentIdPair(MenuFragment, R.id.CourseHistory_container, 1);
                NavigationActivity.backButtonStack.push(newPostPair);

                view_post_tran.commit();


            }


        });
        return V;
    }
}