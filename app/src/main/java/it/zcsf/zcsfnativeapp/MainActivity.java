package it.zcsf.zcsfnativeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bundle=getIntent().getExtras();


        //-----------------------------------------------------------------------------------//
        Fragment fragment = null;
        Class fragmentClass;
        fragmentClass = HomeFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        bundle=getIntent().getExtras();
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.layout_for_fragment, fragment).commit();

        //------------------------------------------------------------------------------------//

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().getBackStackEntryAt(0);
        } else {
            super.onBackPressed();
        }

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
// I think it's supposed to be else if however something is going wrong!

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id== R.id.action_sign_out){
            Intent intent=new Intent(MainActivity.this,SignIn.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_profie) {
            // Handle the camera action
            MyProfileFragment myProfileFragment = new MyProfileFragment();
            FragmentManager manager = getSupportFragmentManager();
            bundle=getIntent().getExtras();
            myProfileFragment.setArguments(bundle);
            manager.beginTransaction().replace(R.id.layout_for_fragment, myProfileFragment).addToBackStack("tag").commit();

        } else if (id == R.id.nav_home) {
            HomeFragment homeFragment = new HomeFragment();
            FragmentManager manager = getSupportFragmentManager();
            bundle=getIntent().getExtras();
            homeFragment.setArguments(bundle);
            manager.beginTransaction().replace(R.id.layout_for_fragment, homeFragment).commit();

        } else if (id == R.id.nav_time_table) {


            TimeTableFragment timeTableFragment = new TimeTableFragment();
            bundle=getIntent().getExtras();
            timeTableFragment.setArguments(bundle);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.layout_for_fragment, timeTableFragment).addToBackStack(null).commit();

        } else if (id == R.id.nav_smart_map) {
            SmartMapFragment smartMapFragment = new SmartMapFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.layout_for_fragment, smartMapFragment).addToBackStack(null).commit();

        } else if (id == R.id.nav_contact) {
            ContactUsFragment contactUsFragment = new ContactUsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.layout_for_fragment, contactUsFragment).addToBackStack(null).commit();


        } else if (id == R.id.nav_about) {
            AboutZCSFfragment aboutZCSFfragment = new AboutZCSFfragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.layout_for_fragment, aboutZCSFfragment).addToBackStack(null).commit();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
