package com.example.ruidong.sbu_application.chatPlatform.service;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import com.example.ruidong.sbu_application.R;

/**
 *
 * /**This class, is a custom list adapter for My posts. It extends
 * ArrayAdapter of type posts because it will be stored in the
 * ArrayAdapter, and we want to inherit its methods. It will store
 * specifcally posts into the Array Adapter
 *
 *
 *  EDIT this page is supposed to hold information relating to the user.
 *  This includes all posts that the user has made, all posts that
 *  the user has liked, and all posts that the user has commented on.
 *
 *  Stuff that will probably be included in this class will be
 *  More tab hosts that will hold all the stuff that we will need.
 *
 *  Stuff to add can be mentioned here:
 *
 *  * Created by Albert and Sikho Wong on 11/18/2015.
 */


public class MyStuffCustomListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;

    /**
     * Default constructor used to sett up the adapter of the
     * My stuff page.
     * @param context
     * @param web
     */
    public MyStuffCustomListAdapter(Activity context,String[] web) {
        super(context, R.layout.recent_post_listview, web);
        this.context = context;
        this.web = web;


    }

    /**
     * Overriden methoud used to get the exact view in the My Stuff list adapter.
     * It also sets the position, likes, number and position of the post inside the
     * list.
     *
     * There is also a method inside this method that represents an onClick listener.
     * When a post is clicked, or the heart is clicked, it will increase the number of likes
     * by 1.
     * @param position
     * @param view
     * @param parent
     * @return
     */
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
