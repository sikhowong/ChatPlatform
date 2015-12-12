package com.example.ruidong.sbu_application.chatPlatform.service;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ruidong.sbu_application.R;
import com.example.ruidong.sbu_application.framework.NavigationActivity;

/**
 *  * Created by Albert and Sikho Wong on 11/18/2015.
 */
public class NewPostFragment extends Fragment {

    /**
     * Default empty constructor
     */
    public NewPostFragment(){

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
     * This is the overriden method for the New post Fragment class used to create the text
     * views as well as paste exactly what it will contain. The purpose of this
     * method in future will consist of the posts information, the number
     * of likes it contains, and how many replys it has as well. The reply number
     * will be added in future tests.
     *
     * Json formatting will also be done towards the bottom of the method. This will be
     * used to send the data to the database, and if we want to parse it for reloading,
     * we will have the JSON file formatted data to do so
     * @param inflater
     * @param containter
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_new_post, containter, false);
        Button button = (Button)rootView.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Submitting Post", Toast.LENGTH_SHORT).show();
            }

        });




        return rootView;
    }
}
