package com.example.ruidong.sbu_application.chatPlatform.service;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;



import android.widget.ListView;
import android.widget.TabHost;


import com.example.ruidong.sbu_application.R;
import com.example.ruidong.sbu_application.framework.NavigationActivity;
import com.example.ruidong.sbu_application.framework.common.tool.FragmentIdPair;

import java.util.ArrayList;
import java.util.Hashtable;


 
/**Class that represents the main fragment
 * Created by Albert Ibragimov and Sikho Wong on 11/7/2015.
 */
public class ChatMainFragment extends Fragment {
    // Fragment TabHost as mTabHost
    private FragmentTabHost mTabHost;
    Fragment MenuFragment = NavigationActivity.MenuFragment;

    public static ArrayList<Post> postList = new ArrayList<Post>();
    public static Hashtable<String,ArrayList<Comment>> commentHashTable = new Hashtable<>();

    //public static ObservableArrayList<Post> postList = new ObservableArrayList<Post>();


    /**
     *  This method is called to do initial creation of the fragment.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    /**
     *  This is the overriden method for the Chat Main Fragment class.
     *  Here in this method, we set up the tabHosts. The tab hosts contain the
     *  Recent Posts Tab, the Popular Posts Tab, and the My Stuff Tab. We add it
     *  to root and return root
     *
     * @param inflater
     * @param containter
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.chat_main_fragment, containter, false);


        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator("Recent Posts"),
                RecentPostsTab.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator("Popular Posts"),
                PopularPostsTab.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentd").setIndicator("My Stuff"),
                MyStuffTab.class, null);


        return rootView;
    }

    /**
     * Overriden method used to return the options menu. This is where we can store the chat
     * compuse button. This will be used when users decide to make a new post. It will
     * set up a listener to react to when a user presses the button
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        // inflater = getActivity().getMenuInflater();
       // inflater.inflate(R.menu.chat_compose_action_menu, menu);
        menu.clear();

        ((NavigationActivity)getActivity()).getMenuInflater().inflate(R.menu.chat_compose_action_menu, menu);
        //super.onCreateOptionsMenu(menu, inflater);

    }

    /**
     * Ovverided method used to determine which iten in the Menu was opened. This is used to
     * determine if a user has clicked a feature different than the chat platform.
     *
     * For example, a user can click the Course Discussion or Course management button
     * and leave from the chat platform.
     *
     * We need to decide what exactly will happen when a user clicks a new item
     * Will the information in the chatplatform reset, or refresh.
     *
     * Will possibly update
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.miCompose:
                if(MenuFragment != null){
                    FragmentTransaction tran7 = getActivity().getSupportFragmentManager().beginTransaction().remove(MenuFragment);
                    tran7.commit();
                }
                if(!NavigationActivity.backButtonStack.isEmpty()){
                    FragmentTransaction tran = getActivity().getSupportFragmentManager().beginTransaction().remove(NavigationActivity.backButtonStack.peek().getFragment());
                    tran.commit();
                }
                NewPostFragment newPostFragment = new NewPostFragment();
                MenuFragment = newPostFragment;
                FragmentTransaction new_post_tran = getActivity().getSupportFragmentManager().beginTransaction().add(R.id.CourseHistory_container,MenuFragment);
                FragmentIdPair newPostPair= new FragmentIdPair(MenuFragment,R.id.CourseHistory_container,1);
                NavigationActivity.backButtonStack.push(newPostPair);

                new_post_tran.commit();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
