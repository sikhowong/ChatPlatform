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
 * Created by shw on 11/18/2015.
 */
public class NewPostFragment extends Fragment {
    public NewPostFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

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
