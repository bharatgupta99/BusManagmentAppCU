package com.bharat.busmanagmentappcu;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class UserFragment extends Fragment {

    private TextView cityText;
    private TextView routeText;
    private TextView slotText;
    private TextView noPrefText;
    private ConstraintLayout constraintLayout;
    private String cityStored;
    private String routeStored;
    private String slotStored;





    public UserFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);


        noPrefText = view.findViewById(R.id.no_pref_text);
        constraintLayout = view.findViewById(R.id.constraintLayout);


        noPrefText.setVisibility(View.INVISIBLE);
        constraintLayout.setVisibility(View.INVISIBLE);
        cityText = view.findViewById(R.id.city_text);
        routeText = view.findViewById(R.id.route_text);
        slotText = view.findViewById(R.id.slot_text);


        cityStored = getCityStored();
        routeStored = getRouteStored();
        slotStored = getSlotStored();



        if (cityStored.compareTo("NO CITY")!=0 && routeStored.compareTo("NO ROUTE")!=0 && slotStored.compareTo("NO SLOT")!=0) {

            cityText.setText(cityStored);
            routeText.setText(routeStored);
            slotText.setText(slotStored);

            constraintLayout.setVisibility(View.VISIBLE);
        }else {
             noPrefText.setVisibility(View.VISIBLE);
        }

        return view;
    }



    public String getCityStored(){

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("CityAndRoute",Context.MODE_PRIVATE);
        String cityStored = sharedPreferences.getString("City","NO CITY");
        return cityStored;
    }

    public String getRouteStored(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("CityAndRoute",Context.MODE_PRIVATE);
        String routeStored = sharedPreferences.getString("Route","NO ROUTE");
        return routeStored;
    }
    public String getSlotStored(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("CityAndRoute",Context.MODE_PRIVATE);
        String routeStored = sharedPreferences.getString("Slot","NO SLOT");
        return routeStored;
    }




}
