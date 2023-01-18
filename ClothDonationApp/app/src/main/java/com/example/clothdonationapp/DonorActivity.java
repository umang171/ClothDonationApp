package com.example.clothdonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class DonorActivity extends AppCompatActivity {

    EditText fName,lName,edtemail,phone_no,address,clothType,clothSize;
    RadioButton male,female;
    Button btnDonate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);
        fName=(EditText) findViewById(R.id.donorFirstName);
        lName=(EditText) findViewById(R.id.donorLastName);
        edtemail=(EditText) findViewById(R.id.donorEmail);
        phone_no=(EditText) findViewById(R.id.donorPhone);
        address=(EditText) findViewById(R.id.donorAddress);
        clothSize=(EditText) findViewById(R.id.clothSize);
        clothType=(EditText) findViewById(R.id.clothType);
        RadioButton male=(RadioButton) findViewById(R.id.gender1);
        RadioButton female=(RadioButton) findViewById(R.id.gender2);
        btnDonate=(Button) findViewById(R.id.btnDonate);
//        DBHelper2 MyDb = new DBHelper2(this);
        DbHelper MyDb=new DbHelper(this);

        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname=fName.getText().toString();
                String lname=lName.getText().toString();
                String email=edtemail.getText().toString();
                String phone=phone_no.getText().toString();
                String addr=address.getText().toString();
                String clothSiz=clothSize.getText().toString();
                String clothTyp=clothType.getText().toString();
                Boolean isMale=male.isChecked();
                Boolean isFemale=female.isChecked();
                String gender = (isMale) ? "Male":"Female";
                Log.d("Donor_data",fname);
                Log.d("Donor_data",lname);
                Log.d("Donor_data",email);
                Log.d("Donor_data",phone);
                Log.d("Donor_data",addr);
                Log.d("Donor_data",clothSiz);
                Log.d("Donor_data",clothTyp);
                Log.d("Donor_data",gender);

                Boolean insert = MyDb.insertDonorData(fname,lname,email,phone,addr,gender,clothTyp,clothSiz);
                if(insert){
                    Toast.makeText(DonorActivity.this, "Cloths Successfully donated", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DonorActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(DonorActivity.this, "Failed", Toast.LENGTH_LONG).show();

                }

            }
        });
    }

}