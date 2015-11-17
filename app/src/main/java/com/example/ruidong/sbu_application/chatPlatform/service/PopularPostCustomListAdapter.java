package com.example.ruidong.sbu_application.chatPlatform.service;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruidong.sbu_application.R;

/**
 * Created by shw on 11/17/2015.
 */
public class PopularPostCustomListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;

    public PopularPostCustomListAdapter(Activity context,String[] web) {
        super(context, R.layout.recent_post_listview, web);
        this.context = context;
        this.web = web;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.recent_post_listview, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.textView1);
        TextView txtTitle2 = (TextView) rowView.findViewById(R.id.textView2);
        ImageView iv = (ImageView) rowView.findViewById(R.id.imageView2);
        final int p = position;
        iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "You Clicked at " + p, Toast.LENGTH_SHORT).show();


            }
        });
        txtTitle.setText(web[position]);
        txtTitle2.setText("testing");

        return rowView;
    }
}
