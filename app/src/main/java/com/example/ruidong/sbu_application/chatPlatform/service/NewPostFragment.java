package com.example.ruidong.sbu_application.chatPlatform.service;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ruidong.sbu_application.R;
import com.example.ruidong.sbu_application.framework.NavigationActivity;

import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
        final EditText et = (EditText)rootView.findViewById(R.id.editText);

        Button button = (Button)rootView.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = et.getText().toString();
                Post p = new Post("",content,0,"", "");
                ChatMainFragment.postList.add(p);
                new HttpRequestTask().execute("http://130.245.191.166:8080/insertPost.php", content, p.getMacAddress(getActivity()));


            }

        });




        return rootView;
    }





    public class HttpRequestTask extends AsyncTask<String, String, String> {
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
                ps.print("firstKey="+params[1] );
                ps.print("&secondKey="+params[2]);
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
}

