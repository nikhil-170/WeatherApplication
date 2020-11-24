package com.example.inclass08;
/*
    FileName: HW04
    Assignment: 08
    Names: Pramukh Nagendra
            Nikhil Surya Peteti (Group 1C)
 */
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class CitiesListFragment extends Fragment {

    ArrayList<String> data = new ArrayList<>(Data.cities.keySet());
    ArrayList<String> cityCountryList = new ArrayList<>();

    public CitiesListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cities_list, container, false);
        getActivity().setTitle(getResources().getString(R.string.cities));
        cityCountryList.clear();
        for (int i=0 ; i<data.size(); i++) {
            for(int j=0; j<Data.cities.get(data.get(i)).size(); j++){
                cityCountryList.add(Data.cities.get(data.get(i)).get(j)+","+data.get(i));
            }
        }

        listView = view.findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1,cityCountryList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mListener.sendDataToCurrentWeather(cityCountryList.get(position));
            }
        });


        return view;
    }

    CitiesListListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CitiesListListener) context;
    }

    public interface CitiesListListener{
        void sendDataToCurrentWeather(String cityName);
    }
}