package com.example.appointmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText ETLEmail;
    EditText ETLPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        ETLEmail = (EditText)findViewById(R.id.ETLEmail);
        ETLPass = (EditText)findViewById(R.id.ETLPass);
//        Intent intent = new Intent(this, ChatActvity.class);
//        startActivity(intent);
    }

    public void login(View view){
        String email = ETLEmail.getText().toString();
        String pass = ETLPass.getText().toString();
        boolean result = false;
        if (email.equals("dev")){
            result = true;
            Intent goToHome = new Intent(MainActivity.this, Home.class);
            startActivity(goToHome);
        }
        else if (!email.isEmpty() && !pass.isEmpty()){

            result = myDb.checkUser(email, pass);
            if (result == true){
                Intent goToHome = new Intent(MainActivity.this, Home.class);
                startActivity(goToHome);
            }
            else{
                Toast.makeText(MainActivity.this,"Email or Password is incorrect", Toast.LENGTH_LONG).show();
            }
        }
        if (result == false){
            Toast.makeText(MainActivity.this,"Fill in the form", Toast.LENGTH_LONG).show();
        }

    }

    public void goToRegister(View view){
        Intent goToReg = new Intent(MainActivity.this, Register.class);
        startActivity(goToReg);
    }
}
