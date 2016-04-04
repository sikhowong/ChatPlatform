package com.example.ruidong.sbu_application.chatPlatform.service;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruidong.sbu_application.R;
import com.example.ruidong.sbu_application.framework.NavigationActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**This class, is a custom list adapter for Recent posts. It extends
 * ArrayAdapter of type posts because it will be stored in the
 * ArrayAdapter, and we want to inherit its methods. It will store
 * specifcally posts into the Array Adapter
 *
 * Created by Albert and Sikho Wong on 11/11/2015.
 */
public class RecentPostCustomListAdapter extends ArrayAdapter<Post> {
    private Activity activity;
    private ArrayList<Post> posts;
   // private final String[] web;
    private static LayoutInflater inflater = null;

    /**
     * Default construtor used to set the Recent post adapters activity and posts
     * @param activity
     * @param posts
     */
    public RecentPostCustomListAdapter(Activity activity, ArrayList<Post> posts) {
        super(activity, R.layout.recent_post_listview, posts);
        try {
            this.activity = activity;
            this.posts = posts;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }


    }

    /**
     * Static class used within the class, containts text views for the
     * display name, display number, and display likes. Will assist
     * with the getView method
     */
    public static class ViewHolder {
        public TextView displayName;
        public TextView displayNumber;
        public TextView displayLikes;
    }

    /**
     * Overriden method used to get the exact view in the recent list adapter.
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

        final ViewHolder holder;

        View rowView = inflater.inflate(R.layout.recent_post_listview, null);
        holder = new ViewHolder();
        holder.displayName = (TextView) rowView.findViewById(R.id.textView1);
        holder.displayNumber = (TextView) rowView.findViewById(R.id.textView2);
        holder.displayLikes = (TextView) rowView.findViewById(R.id.textView7);
        ImageView iv = (ImageView) rowView.findViewById(R.id.imageView2);
        final int p = position;
        iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Toast.makeText(activity, "You Clicked at " + p, Toast.LENGTH_SHORT).show();

                //pass mac address and post id to http request
                String macAddress = posts.get(p).getMacAddress(activity);
                new UpdateLikeHttpRequestTask().execute("http://130.245.191.166:8080/updateLikes.php", macAddress, posts.get(p).getID(), p+"");
                Log.w("myapp33", "inserting likes mac " + macAddress + "id " + posts.get(p).getID());
                reload();

            }
        });

        holder.displayName.setText(posts.get(position).getContent());
        holder.displayNumber.setText(posts.get(position).getDate().toString());
        holder.displayLikes.setText("Likes: " + posts.get(position).getLikes());
        return rowView;
    }

    public void reload(){
        this.notifyDataSetChanged();
    }


    /**
     * Simple method used to get the specific Item we want based on the position
     * in the list. returns it from the posts data structure.
     * @param position
     * @return
     */
    public Post getItem(int position){
        return posts.get(position);
    }

    public class UpdateLikeHttpRequestTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params){
            try {
                // open a connection to the site
                URL url = new URL(params[0]);
                URLConnection con = url.openConnection();
                // activate the output
                con.setDoOutput(true);
                PrintStream ps = new PrintStream(con.getOutputStream());
                // send your parameters to your site
                ps.print("mac="+params[1] );
                ps.print("&postid="+params[2]);
                //ps.print("&thirdKey="+params[3]);

                // we have to get the input stream in order to actually send the request
                InputStream is = con.getInputStream();
                //
                // is = httpConn.getErrorStream();
                String readLine;
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                while (((readLine = br.readLine()) != null)) {
                    //System.out.println(readLine);
                    Log.w("myapp44", readLine);
                    if(!readLine.equals("matched")){
                        posts.get(Integer.parseInt(params[3])).setLikes(posts.get(Integer.parseInt(params[3])).getLikes() + 1);
                    }

                }
               // Log.w("myapp44", con.getInputStream().toString());
                //System.out.println("done");
                // close the print stream
                ps.close();
                return "done";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
           // Toast.makeText(getActivity(), "Post Submitted "+result, Toast.LENGTH_SHORT).show();
        }
    }

}
