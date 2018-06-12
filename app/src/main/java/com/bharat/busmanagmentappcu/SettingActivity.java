package com.bharat.busmanagmentappcu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.isapanah.awesomespinner.AwesomeSpinner;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {

    private AwesomeSpinner citiesDropDown;
    private AwesomeSpinner routesDropDown;
    private List<String> citiesList;
    private List<String> routesList;
    private String cityChoosen;
    private String routeChoosen;
    private FirebaseFirestore firebaseFirestore;
    private Button setButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


            firebaseFirestore = FirebaseFirestore.getInstance();
            citiesList = new ArrayList<>();
            citiesDropDown = findViewById(R.id.cities_spinner_admin);
            routesList = new ArrayList<>();
            routesDropDown = findViewById(R.id.route_spinner_admin);


            citiesList.add("Chandigarh");
            citiesList.add("Panchkula");
            citiesList.add("Ambala");
            citiesList.add("Rajpura");
            citiesList.add("Mohali");
            citiesList.add("Patiala");
            citiesList.add("Zirakpur");


            ArrayAdapter<String> adapterCities = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, citiesList);
            citiesDropDown.setAdapter(adapterCities);



            citiesDropDown.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
                @Override
                public void onItemSelected(int position, String itemAtPosition) {
                    cityChoosen = itemAtPosition;
                    setRoutes(cityChoosen);
                    ArrayAdapter adapterRoutes = new ArrayAdapter<>(SettingActivity.this, android.R.layout.simple_spinner_dropdown_item, routesList);
                    routesDropDown.setAdapter(adapterRoutes);

                }
            });

            routesDropDown.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
                @Override
                public void onItemSelected(int i, String s) {
                    routeChoosen = s;
                }
            });



            setButton = findViewById(R.id.set_pref_btn);
            setButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(cityChoosen!=null && routeChoosen!=null) {
                        setSlotFromFirebase();

                    }else{
                        Toast.makeText(SettingActivity.this,"Please select both",Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }

    private void setSlotFromFirebase() {
        final String[] slotInfo = new String[1];
        firebaseFirestore.collection("slotInfo").document(cityChoosen).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    slotInfo[0] = task.getResult().getString(routeChoosen);
                    setSharedPrefrence(cityChoosen, routeChoosen,slotInfo[0]);
                    Intent userIntent = new Intent(SettingActivity.this, UserActivity.class);
                    startActivity(userIntent);
                    finish();
                }else{
                    Toast.makeText(SettingActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setRoutes(String cityChoosen) {
        routesList.clear();
        if(cityChoosen.compareTo("Chandigarh")==0){
            routesList.add("CR1");
            routesList.add("CR2");
            routesList.add("CR3");
            routesList.add("CR4");
            routesList.add("CR5");
        }else if(cityChoosen.compareTo("Ambala")==0){
            routesList.add("AR1");
            routesList.add("AR2");
            routesList.add("AR3");
            routesList.add("AR4");
            routesList.add("AR5");
        }else if(cityChoosen.compareTo("Panchkula")==0){
            routesList.add("PR1");
            routesList.add("PR2");
            routesList.add("PR3");
            routesList.add("PR4");
        }else if(cityChoosen.compareTo("Zirakpur")==0){
            routesList.add("ZR1");
            routesList.add("ZR2");
        }else if(cityChoosen.compareTo("Mohali")==0){
            routesList.add("MR1");
            routesList.add("MR2");
            routesList.add("MR3");
            routesList.add("MR4");
        }
        else if(cityChoosen.compareTo("Rajpura")==0){
            routesList.add("RR1");
            routesList.add("RR2");

        }else if(cityChoosen.compareTo("Patiala")==0) {
            routesList.add("PaR1");
            routesList.add("PaR2");
            routesList.add("PaR3");
            routesList.add("PaR4");
        }
    }

    public void setSharedPrefrence(String city, String route,String slotInfo){
        SharedPreferences sharedPreferences = getSharedPreferences("CityAndRoute",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("City",city);
        editor.putString("Route",route);
        editor.putString("Slot",slotInfo);
        editor.apply();
    }


}

