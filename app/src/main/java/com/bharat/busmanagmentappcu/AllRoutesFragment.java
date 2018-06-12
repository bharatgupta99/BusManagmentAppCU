package com.bharat.busmanagmentappcu;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AllRoutesFragment extends Fragment {

    private List<String> parentHeading;
    private Map<String,List<String>> childContent;
    private ExpandableListView expandableListView;
    private ExpandableAdapter expandableAdapter;
    private FrameLayout frameLayout;
    private FirebaseFirestore firebaseFirestore;


    public AllRoutesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_routes, container, false);

        fillData();
        firebaseFirestore = FirebaseFirestore.getInstance();
        frameLayout = view.findViewById(R.id.all_frame_layout);
        expandableListView = view.findViewById(R.id.expand_lv);
        expandableAdapter = new ExpandableAdapter(getContext(),parentHeading,childContent);
        expandableListView.setAdapter(expandableAdapter);


        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                makeSnackBar(expandableAdapter.getGroup(i),expandableAdapter.getChild(i,i1));
                return true;
            }
        });

        return view;


    }

    private void makeSnackBar(Object group, Object child) {
        String city = group.toString();
        String route = child.toString();
        getSlotFromFirebase(city,route);
    }

    private void getSlotFromFirebase(final String city, final String route) {
        firebaseFirestore.collection("slotInfo").document(city).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                   String slot = task.getResult().getString(route);
                    Snackbar.make(frameLayout,"SLOT -  "+slot,Snackbar.LENGTH_INDEFINITE)
                            .setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            }).setActionTextColor(getResources().getColor(R.color.white)).show();
                }
            }
        });
    }

    private void fillData() {

        parentHeading = new ArrayList<>();



        parentHeading.add("Chandigarh");
        parentHeading.add("Ambala");
        parentHeading.add("Panchkula");
        parentHeading.add("Rajpura");
        parentHeading.add("Mohali");
        parentHeading.add("Zirakpur");
        parentHeading.add("Patiala");

        List<String> chandigarhRoutes = new ArrayList<>();
        chandigarhRoutes.add("CR1");
        chandigarhRoutes.add("CR2");
        chandigarhRoutes.add("CR3");
        chandigarhRoutes.add("CR4");
        chandigarhRoutes.add("CR5");


        List<String> ambalaRoutes = new ArrayList<>();
        ambalaRoutes.add("AR1");
        ambalaRoutes.add("AR2");
        ambalaRoutes.add("AR3");
        ambalaRoutes.add("AR4");
        ambalaRoutes.add("AR5");

        List<String> panchkulaRoutes = new ArrayList<>();
        panchkulaRoutes.add("PR1");
        panchkulaRoutes.add("PR2");
        panchkulaRoutes.add("PR3");
        panchkulaRoutes.add("PR4");

        List<String> rajpuraRoutes = new ArrayList<>();
        rajpuraRoutes.add("RR1");
        rajpuraRoutes.add("RR2");

        List<String> mohaliRoutes = new ArrayList<>();
        mohaliRoutes.add("MR1");
        mohaliRoutes.add("MR2");
        mohaliRoutes.add("MR3");
        mohaliRoutes.add("MR4");

        List<String> patialaRoutes = new ArrayList<>();
        patialaRoutes.add("PaR1");
        patialaRoutes.add("PaR2");
        patialaRoutes.add("PaR3");
        patialaRoutes.add("PaR4");

        List<String> zirakpurRoutes = new ArrayList<>();
        zirakpurRoutes.add("ZR1");
        zirakpurRoutes.add("ZR2");


        childContent = new HashMap<>();
        childContent.put(parentHeading.get(0),chandigarhRoutes);
        childContent.put(parentHeading.get(1),ambalaRoutes);
        childContent.put(parentHeading.get(2),panchkulaRoutes);
        childContent.put(parentHeading.get(3),rajpuraRoutes);
        childContent.put(parentHeading.get(4),mohaliRoutes);
        childContent.put(parentHeading.get(5),zirakpurRoutes);
        childContent.put(parentHeading.get(6),patialaRoutes);


    }

}
