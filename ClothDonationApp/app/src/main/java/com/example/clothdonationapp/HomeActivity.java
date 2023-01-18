package com.example.clothdonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    DbHelper MyDb=new DbHelper(this);
    ListView donorListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        donorListView=(ListView) findViewById(R.id.donorsListView);
        ArrayList<DonorInfo> arrayList= MyDb.fetchContact();

//        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        ArrayList<String> arr=new ArrayList<String>();
        for (DonorInfo donr :
                arrayList) {

            String temp="\nCloth Type=" + donr.clothType +
                    "\nCloth Size=" + donr.clothSize+
                    "\nGender=" + donr.gender  +
                    "\nDonor Name=" + donr.fname  +
                    "\nDonor phone number=" + donr.phone;
            arr.add(temp);
            Log.d("Donor_info",temp);
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arr);
        donorListView.setAdapter(arrayAdapter);

    }
}