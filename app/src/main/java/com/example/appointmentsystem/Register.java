package com.example.appointmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText ETFirstN;
    EditText ETLastN;
    EditText ETEmail;
    EditText ETPass;
    EditText ETConfPass;
    RadioButton RBMale;
    RadioButton RBFemale;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        myDb = new DatabaseHelper(this);
        ETFirstN = (EditText)findViewById(R.id.ETFirstName);
        ETLastN = (EditText)findViewById(R.id.ETLastName);
        ETEmail = (EditText)findViewById(R.id.ETemail);
        ETPass = (EditText)findViewById(R.id.ETpassword);
        ETConfPass = (EditText)findViewById(R.id.ETconfpassword);
        RBMale = (RadioButton) findViewById(R.id.RBMale);
        RBFemale = (RadioButton) findViewById(R.id.RBFemale);
        register = (Button)findViewById(R.id.btnRegister);

    }

    public void register(View view){
        String pass = ETPass.getText().toString();
        String confpass = ETConfPass.getText().toString();
        boolean result = false;
        if (pass.equals(confpass)){
            String gender = "";
            if (RBMale.isChecked()){
                gender = "male";
            }
            if (RBFemale.isChecked()){
                gender = "female";
            }
            result = myDb.saveUser(ETFirstN.getText().toString(), ETLastN.getText().toString(), ETEmail.getText().toString(), pass, gender);

            if (result == true){
                Intent goToLog = new Intent(Register.this, MainActivity.class);
                Toast.makeText(Register.this,"User registered successfully",Toast.LENGTH_LONG).show();
                startActivity(goToLog);
            }
        }
        if (result == false){
            Toast.makeText(Register.this,"Confirm password must match with password",Toast.LENGTH_LONG).show();
        }

    }

    public void goToLogin(View view){
        Intent goToLog = new Intent(Register.this, MainActivity.class);
        startActivity(goToLog);
    }
}
