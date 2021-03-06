package com.example.ruidong.sbu_application.chatPlatform.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruidong.sbu_application.R;
import com.example.ruidong.sbu_application.framework.NavigationActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static com.example.ruidong.sbu_application.R.layout.recent_post_listview;

/**
 * Created by Albert and Sikho Wong on 11/11/2015.
 */


public class ViewPostFragment extends Fragment {
    Post post;
    EditText inputComment ;
    ArrayList<Comment> comments = new ArrayList<>();
    View rootView;
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
        rootView = inflater.inflate(R.layout.fragment_view_post, containter, false);
        TextView tv1 = (TextView)rootView.findViewById(R.id.textView7);
        TextView tv2 = (TextView)rootView.findViewById(R.id.textView8);
        TextView tv3 = (TextView)rootView.findViewById(R.id.textViewNum);
        tv1.setText(post.getContent());
        tv2.setText(post.getDate().toString());
        tv3.setText(post.getLikes()+"");


        Button submitCommentBtn = (Button)rootView.findViewById(R.id.button3);

        AlertDialog.Builder builder = new AlertDialog.Builder((NavigationActivity)getActivity());
        builder.setTitle("Submit comment");
        inputComment = new EditText((NavigationActivity)getActivity());
        builder.setView(inputComment);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String txt = inputComment.getText().toString();
                Comment c = new Comment("", txt,0,"", "", "");
                if (ChatMainFragment.commentHashTable.containsKey(post.getContent())){
                    ChatMainFragment.commentHashTable.get(post.getContent()).add(c);
                }else{
                    ChatMainFragment.commentHashTable.put(post.getContent(), new ArrayList<Comment>());
                    ChatMainFragment.commentHashTable.get(post.getContent()).add(c);
                }

                new InsertCommentHttpRequestTask().execute("http://130.245.191.166:8080/insertComment.php", txt, post.getID(),c.getMacAddress(getActivity()));
                new LoadCommentHttpRequestTask().execute("http://130.245.191.166:8080/getComments.php", post.getID());
    //            reload(rootView);




                Toast.makeText(getActivity(), txt, Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });

        final AlertDialog ad = builder.create();

        submitCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.show();
            }
        });

        //Button button = (Button)rootView.findViewById(R.id.button2);
        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Submitting Post", Toast.LENGTH_SHORT).show();
            }

        });
        */
/*
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
                //comments.add(new Comment(jsonObject.getString("content"), jsonObject.getInt("likes")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
*/

        // new for showing
        new LoadCommentHttpRequestTask().execute("http://130.245.191.166:8080/getComments.php", post.getID());





        return rootView;
    }
    public void reload(View rootView){
      //  ArrayList<Comment> comments = new ArrayList<>();
        //comments = ChatMainFragment.commentHashTable.get(post.getContent());
        if(comments != null){
            final CommentsCustomListAdapter adapter = new CommentsCustomListAdapter(getActivity(), recent_post_listview, comments);
            ListView listView = (ListView) rootView.findViewById(R.id.listView);
            listView.setAdapter(adapter);

        }
    }



    /**
     *
     * @param p
     * Simple mutator method used to set the post
     */
    public void setPost(Post p){
        post = p;
    }


    public class InsertCommentHttpRequestTask extends AsyncTask<String, String, String> {
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
                ps.print("content="+params[1] );
                ps.print("&postid="+params[2]);
                ps.print("&mac="+params[3]);
                //ps.print("&thirdKey="+params[3]);

                // we have to get the input stream in order to actually send the request
                con.getInputStream();
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
            Toast.makeText(getActivity(), "Post Submitted "+result, Toast.LENGTH_SHORT).show();
        }
    }

    public class LoadCommentHttpRequestTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params){
            try {
                // open a connection to the site
                URL url = new URL(params[0]);
                URLConnection con = url.openConnection();
                // activate the output
                con.setDoOutput(true);
                PrintStream ps = new PrintStream(con.getOutputStream());
                ps.print("postid="+params[1] );


                // we have to get the input stream in order to actually send the request
                InputStream is = con.getInputStream();

                String readLine;
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String jstring = "";
                while (((readLine = br.readLine()) != null)) {
                    //System.out.println(readLine);
                    Log.w("myapp44", readLine);
                  /*  if(!readLine.equals("matched")){
                        //posts.get(Integer.parseInt(params[3])).setLikes(posts.get(Integer.parseInt(params[3])).getLikes() + 1);
                    }*/
                    jstring += readLine;


                }

                JSONArray jsonArray = new JSONArray(jstring);
                int count = jsonArray.length(); // get totalCount of all jsonObjects
                comments = new ArrayList<Comment>();
                for(int i=0 ; i< count; i++){   // iterate through jsonArray
                    JSONObject jsonObject = jsonArray.getJSONObject(i);  // get jsonObject @ i position
                    //System.out.println( jsonObject.getString("id") + " " + jsonObject.getString("name") + "  " + jsonObject.getString("age"));
                    Log.w("comment22", "comment jsonObject " + i + ": " + jsonObject.getString("PostID") + "  " + jsonObject.getString("Content") + "  " + jsonObject.getString("UserID")+ " " + jsonObject.getString("id"));
                    //posts.add(new Post(jsonObject.getString("content"), jsonObject.getInt("likes")));
                    comments.add(new Comment(jsonObject.getString("id"), jsonObject.getString("Content"), jsonObject.getInt("Likes"), jsonObject.getString("UserID"), jsonObject.getString("DateCreated"), jsonObject.getString("PostID")));
                }

                ps.close();
                return "done";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch(JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            reload(rootView);
        }
    }

}
