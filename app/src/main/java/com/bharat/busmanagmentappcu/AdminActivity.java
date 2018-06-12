package com.bharat.busmanagmentappcu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bharat.busmanagmentappcu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.isapanah.awesomespinner.AwesomeSpinner;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminActivity extends AppCompatActivity {

    private AwesomeSpinner cityDropDown;
    private AwesomeSpinner routeDropDown;
    private List<String> citiesList;
    private List<String> routesList;
    private String cityChoosen;
    private String routeChoosen;
    private int enableBtn=0;
    private EditText setSlot;
    private Button submitBtn;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth mAuth;
    private Toolbar toolbar;
    private AVLoadingIndicatorView loadingAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mAuth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.admin_toolbar);
        firebaseFirestore = FirebaseFirestore.getInstance();
        submitBtn = findViewById(R.id.submit_btn);
        setSlot = findViewById(R.id.setslot);
        cityDropDown = findViewById(R.id.cities_spinner_admin);
        routeDropDown = findViewById(R.id.route_spinner_admin);
        loadingAnim  = findViewById(R.id.avi);
        citiesList = new ArrayList<>();
        routesList = new ArrayList<>();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bus Management App");

        citiesList.add("Chandigarh");
        citiesList.add("Panchkula");
        citiesList.add("Ambala");
        citiesList.add("Rajpura");
        citiesList.add("Mohali");
        citiesList.add("Zirakpur");
        citiesList.add("Patiala");

        ArrayAdapter<String> adapterCities = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,citiesList);
        cityDropDown.setAdapter(adapterCities);
        cityDropDown.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {
                cityChoosen = itemAtPosition;
                enableBtn++;
                setRoutes(cityChoosen);
                ArrayAdapter adapterRoutes = new ArrayAdapter<>(AdminActivity.this, android.R.layout.simple_spinner_dropdown_item, routesList);
                routeDropDown.setAdapter(adapterRoutes);

            }
        });

        routeDropDown.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int i, String s) {
                enableBtn++;
                routeChoosen = s;
                Log.i("ENABLE",String.valueOf(enableBtn));
                if(enableBtn==2){
                    submitBtn.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(AdminActivity.this,"Please Select Both Oprions",Toast.LENGTH_SHORT).show();
                }

            }
        });


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingAnim.setVisibility(View.VISIBLE);
                loadingAnim.show();
                HashMap<String,Object> data = new HashMap<>();
                data.put(routeChoosen,setSlot.getText().toString());
                firebaseFirestore.collection("slotInfo").document(cityChoosen).set(data, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AdminActivity.this,"Upload Successful",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AdminActivity.this,"Can't Upload Please Try Again..",Toast.LENGTH_SHORT).show();
                        }
                        loadingAnim.hide();
                    }
                });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_toobar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.logout){
            mAuth.signOut();
            Intent loginIntent = new Intent(AdminActivity.this,AdminLoginActivity.class);
            startActivity(loginIntent);
            finish();
        }

        return true;
    }
}
