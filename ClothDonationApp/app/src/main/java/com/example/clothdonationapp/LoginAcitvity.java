package com.example.clothdonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAcitvity extends AppCompatActivity {

    EditText username1,password1;
    Button btnsignin1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitvity);

        username1 = (EditText) findViewById(R.id.username1);
        password1 = (EditText) findViewById(R.id.password1);
        btnsignin1 = (Button) findViewById(R.id.btnsignin1);
        DbHelper MyDb = new DbHelper(this);

        btnsignin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username1.getText().toString();
                String pass = password1.getText().toString();

                if (uname.equals("") || pass.equals("") ) {
                    Toast.makeText(LoginAcitvity.this, "Please enter all fields", Toast.LENGTH_LONG).show();
                } else {

                    String type = MyDb.getTypeOfUser(uname,pass);
                    Boolean isValidCrendential = MyDb.checkUsernameAndPassword(uname,pass);

                    if(isValidCrendential){
                        if(type.equals("Admin")){
                            Toast.makeText(LoginAcitvity.this, "Loggedin SuccessFUlly", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginAcitvity.this,HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(LoginAcitvity.this, "Loggedin SuccessFUlly", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginAcitvity.this,DonorActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                    else{
                        Toast.makeText(LoginAcitvity.this, "Invalid credentials", Toast.LENGTH_LONG).show();

                    }

                }
            }
        });
    }
}
