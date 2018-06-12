package com.bharat.busmanagmentappcu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class UserActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView userBottomNavigation;
    private UserFragment userFragment;
    private AllRoutesFragment allRoutesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userBottomNavigation = findViewById(R.id.bottomNavigationView);
        toolbar = findViewById(R.id.user_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Welcome");


        allRoutesFragment = new AllRoutesFragment();
        userFragment = new UserFragment();
        replaceFragment(userFragment);

        userBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int user_id = item.getItemId();
                if(user_id == R.id.user_home){
                    replaceFragment(userFragment);
                }else if(user_id == R.id.all_routes){
                    replaceFragment(allRoutesFragment);
                }
                return false;
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_toolbar_layout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int userId = item.getItemId();
        if(userId == R.id.change_prefrences){
            Intent settingIntent = new Intent(UserActivity.this,SettingActivity.class);
            startActivity(settingIntent);
        }
        return true;
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();

    }


}
