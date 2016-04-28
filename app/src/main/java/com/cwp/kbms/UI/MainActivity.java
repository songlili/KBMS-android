package com.cwp.kbms.UI;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cwp.kbms.Fragment.DocFragment;
import com.cwp.kbms.Fragment.HomeFragment;
import com.cwp.kbms.Fragment.MusicFragment;
import com.cwp.kbms.Fragment.PicFragment;
import com.cwp.kbms.Fragment.VideoFragment;
import com.cwp.kbms.R;
import com.cwp.kbms.Util.HintUtil;

public class MainActivity extends AppCompatActivity
        implements DocFragment.OnFragmentInteractionListener
        , PicFragment.OnFragmentInteractionListener
        , HomeFragment.OnFragmentInteractionListener
        , MusicFragment.OnFragmentInteractionListener
        , VideoFragment.OnFragmentInteractionListener
        , NavigationView.OnNavigationItemSelectedListener {

    //数据域
    private Context context = MainActivity.this;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    //Fragments
    public static Fragment docFragment;
    public static Fragment picFragment;
    public static Fragment musicFragment;
    public static Fragment homeFragment;
    public static Fragment videoFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        initDatas();
        initViews();
    }

    private void initDatas() {

        //初始化Fragements
        if (findViewById(R.id.fragment_container) != null) {

            docFragment = new DocFragment();
            picFragment = new PicFragment();
            musicFragment = new MusicFragment();
            videoFragment = new VideoFragment();
            homeFragment = new HomeFragment();

            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, homeFragment).commit();
        }


    }

    private void initViews() {
        //// TODO: 2016/4/17
        //初始化Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //初始化悬浮按钮
        fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "TODO:Add a new file floder.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        //初始化抽屉
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //初始化ActionBar
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.setDrawerListener(toggle);
        }
        toggle.syncState();

        //初始化导航视图
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //暂时用不到Actionbar上的功能，先不实现了，以后有需求再说吧
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    //deal with the click event of navigation item
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction action = getFragmentManager().beginTransaction();

        switch (id) {
            case R.id.nav_doc:

                action.replace(R.id.fragment_container, docFragment);
                toolbar.setTitle(R.string.menu_doc);

                break;
            case R.id.nav_gallery:

                action.replace(R.id.fragment_container, picFragment);
                toolbar.setTitle(R.string.menu_gallery);

                break;
            case R.id.nav_video:

                action.replace(R.id.fragment_container, videoFragment);
                toolbar.setTitle(R.string.menu_music);

                break;
            case R.id.nav_audio:

                action.replace(R.id.fragment_container, musicFragment);
                toolbar.setTitle(R.string.menu_music);

                break;
            case R.id.nav_home:

                action.replace(R.id.fragment_container, homeFragment);
                toolbar.setTitle("Home");

                break;
            case R.id.nav_settings:

                //// TODO: 2016/4/17
                HintUtil.alert(context, "settings");
                toolbar.setTitle(R.string.action_settings);

                break;
        }
        action.commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
