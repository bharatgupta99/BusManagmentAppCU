package com.bharat.busmanagmentappcu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class ChooseActivity extends AppCompatActivity {

    private CardView adminCard;
    private CardView otherCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        adminCard = findViewById(R.id.admin_card);
        otherCard = findViewById(R.id.other_card);

        if(getCityStored().compareTo("NO CITY")==0 && getRouteStored().compareTo("NO ROUTE")==0) {

            adminCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent adminIntent = new Intent(ChooseActivity.this, AdminLoginActivity.class);
                    startActivity(adminIntent);
                }
            });

            otherCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent userIntent = new Intent(ChooseActivity.this, UserActivity.class);
                    startActivity(userIntent);
                }
            });
        }else{
            Intent userIntent = new Intent(ChooseActivity.this,SettingActivity.class);
            startActivity(userIntent);
            finish();
        }

    }

    public String getCityStored(){
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        String cityStored = sharedPreferences.getString("City","NO CITY");
        return cityStored;
    }

    public String getRouteStored(){
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        String routeStored = sharedPreferences.getString("Route","NO ROUTE");
        return routeStored;
    }


}
