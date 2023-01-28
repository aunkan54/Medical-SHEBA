package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation__drawer);

        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigationId);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Home_Fragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = new Home_Fragment();

                    switch (menuItem.getItemId()){
                        case R.id.bottom_home_id:
                            selectedFragment = new Home_Fragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() { //programmatically exits the program
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if(toggle.onOptionsItemSelected(item)){
            return true;
        }



        if(item.getItemId()==R.id.settingsId){
            Toast.makeText(this,"Settings is selected",Toast.LENGTH_SHORT).show();
            return true;
        }else if(item.getItemId()==R.id.shareId){
            Toast.makeText(this,"Share is selected",Toast.LENGTH_SHORT).show();
            return true;
        }else if(item.getItemId()==R.id.feedbackId){
            Toast.makeText(this,"Feedback is selected",Toast.LENGTH_SHORT).show();
            return true;
        }else if(item.getItemId()==R.id.aboutusId){
            Toast.makeText(this,"About us is selected",Toast.LENGTH_SHORT).show();
            return true;
        }else if(item.getItemId()==R.id.infoId){

            finish();
            Intent intent = new Intent(Profile.this,View_user_info.class);
            startActivity(intent);
            return true;
        }else if(item.getItemId()==R.id.driverId){

            finish();
            Intent intent = new Intent(Profile.this,Ambulance_driver_view.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.signoutId){
            Toast.makeText(this,"Signed Out Successfully",Toast.LENGTH_SHORT).show();

            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(Profile.this,Sign_in.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(menuItem.getItemId()==R.id.nav_home){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Home_Fragment()).commit();
        } else if(menuItem.getItemId()==R.id.nav_personal_info){

            Intent intent = new Intent(Profile.this,View_user_info.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
