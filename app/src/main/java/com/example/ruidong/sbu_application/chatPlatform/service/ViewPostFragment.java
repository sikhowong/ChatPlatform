package com.example.ruidong.sbu_application.chatPlatform.service;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruidong.sbu_application.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.ruidong.sbu_application.R.layout.recent_post_listview;

/**
 * Created by Albert and Sikho Wong on 11/11/2015.
 */


public class ViewPostFragment extends Fragment {
    Post post;
    public ViewPostFragment(){

    }

    /**
     * This method is called to do initial creation of the fragment.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    /**
     *
     * @param inflater
     * @param containter
     * @param savedInstanceState
     * @return
     * This is the overriden method for the View post Fragment class used to create the text
     * views as well as paste exactly what it will contain. The purpose of this
     * method in future will consist of the posts information, the number
     * of likes it contains, and how many replys it has as well. The reply number
     * will be added in future tests.
     *
     * Json formatting will also be done towards the bottom of the method. This will be
     * used to send the data to the database, and if we want to parse it for reloading,
     * we will have the JSON file formatted data to do so
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_view_post, containter, false);
        TextView tv1 = (TextView)rootView.findViewById(R.id.textView7);
        TextView tv2 = (TextView)rootView.findViewById(R.id.textView8);
        TextView tv3 = (TextView)rootView.findViewById(R.id.textViewNum);
        tv1.setText(post.getContent());
        tv2.setText(post.getDateCreated().toString() + " " + post.getMacAddress(getActivity()));
        tv3.setText(post.getLikes()+"");


        //Button button = (Button)rootView.findViewById(R.id.button2);
        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Submitting Post", Toast.LENGTH_SHORT).show();
            }

        });
        */
        ArrayList<Comment> comments = new ArrayList<>();


        try {
            JSONArray jsonArray = new JSONArray();
            for(int i =0  ; i < 12; i++){
                JSONObject jsonObj = new JSONObject();  // these 4 files add jsonObject to jsonArray
                jsonObj.put("content", "Comment Posting !Comment Posting ! Comment Posting.." + i);
                jsonObj.put("dateCreated", "2015-09-12 09:12:"+i*2);
                jsonObj.put("likes", i+3);
                jsonArray.put(jsonObj);
            }
            int count = jsonArray.length(); // get totalCount of all jsonObjects
            for(int i=0 ; i< count; i++){   // iterate through jsonArray
                JSONObject jsonObject = jsonArray.getJSONObject(i);  // get jsonObject @ i position
                System.out.println("jsonObject " + i + ": " + jsonObject.getString("content") + "  " + jsonObject.getString("dateCreated"));
                //Log.w("myapp", "jsonObject " + i + ": " + jsonObject.getString("content") + "  " + jsonObject.getString("dateCreated"));
                comments.add(new Comment(jsonObject.getString("content"), jsonObject.getInt("likes")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }




        final CommentsCustomListAdapter adapter = new CommentsCustomListAdapter(getActivity(), recent_post_listview, comments);
        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);



        return rootView;
    }

    /**
     *
     * @param p
     * Simple mutator method used to set the post
     */
    public void setPost(Post p){
        post = p;
    }
}
