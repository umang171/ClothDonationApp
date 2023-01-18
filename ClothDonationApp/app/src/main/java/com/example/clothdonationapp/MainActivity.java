package com.example.clothdonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button signup,signin;
    RadioButton radioButton1,radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        DbHelper MyDb = new DbHelper(this);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                Boolean isDonor = radioButton1.isChecked();
                Boolean isAdmin = radioButton2.isChecked();
                String type = (isAdmin) ? "Admin":"Donor";

                if (uname.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_LONG).show();
                } else {
                    if (pass.equals(repass)) {
                        Boolean userExist = MyDb.checkUsername(uname);
                        if(!userExist){
                            Boolean insert = MyDb.insertData(uname,pass,type);
                            if(insert){
                                Toast.makeText(MainActivity.this, "Registered SuccessFUlly", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MainActivity.this,LoginAcitvity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Registeration Failed", Toast.LENGTH_LONG).show();

                            }
                        }else{
                            Toast.makeText(MainActivity.this, "User ALready Exist Please signin", Toast.LENGTH_LONG).show();

                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Passworeds not matching", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginAcitvity.class);
                startActivity(intent);
            }
        });
    }



}