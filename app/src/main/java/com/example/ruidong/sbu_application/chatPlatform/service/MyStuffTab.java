package com.example.ruidong.sbu_application.chatPlatform.service;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ruidong.sbu_application.R;
import com.example.ruidong.sbu_application.framework.NavigationActivity;
import com.example.ruidong.sbu_application.framework.common.tool.FragmentIdPair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyStuffTab extends Fragment  {
    Fragment MenuFragment = NavigationActivity.MenuFragment;

    /**
     * This is the overriden method for the My Stuff Tab Fragment class used to create
     * the text views as well as paste exactly what it will contain. The purpose of this
     * method in future will consist of the posts information, the number
     * of likes it contains, and how many replys it has as well. The reply number
     * will be added in future tests.
     *
     * Json formatting will also be done towards the bottom of the method. This will be
     * used to send the data to the database, and if we want to parse it for reloading,
     * we will have the JSON file formatted data to do so
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_my_stuff, container, false);
        String[] mobileArray = {"These are my post let's check it out !!!!!!!!!!!! ! !!!  !!!!! haha","More of my post , these are great, look at my likes ","Who trynna get lunch?","Blackberry","These are my post let's check it out !!!!!!!!!!!! ! !!!  !!!!! haha","More of my post , these are great, look at my likes ","Who trynna get lunch?","Blackberry","These are my post let's check it out !!!!!!!!!!!! ! !!!  !!!!! haha","More of my post , these are great, look at my likes ","Who trynna get lunch?","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
        ArrayList<Post> posts = new ArrayList<Post>();

        String jsonString = "";
        try {
            JSONArray jsonArray = new JSONArray();
            for(int i =0  ; i < 12; i++){
                JSONObject jsonObj = new JSONObject();  // these 4 files add jsonObject to jsonArray
                jsonObj.put("content", "All my post ! Lorem Ipsum !" + i);
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
        //ORIGINAL
        //final MyStuffCustomListAdapter adapter = new MyStuffCustomListAdapter(getActivity(), mobileArray);
        //TEMP

        //final RecentPostCustomListAdapter adapter = new RecentPostCustomListAdapter(getActivity(), posts);
        //remove
        final RecentPostCustomListAdapter adapter = new RecentPostCustomListAdapter(getActivity(), ChatMainFragment.postList);
        ListView listView = (ListView) V.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * Also contains an onItemClick listener used to add an action
             * to when the post is clicked. Currently it shows the specific post
             * that we click, however, what is meant to happen, is open up all
             * the comments and replies that are associated with that post.
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