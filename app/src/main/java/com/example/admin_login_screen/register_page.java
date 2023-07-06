package com.example.admin_login_screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import kotlin.collections.ArrayDeque;

public class register_page extends AppCompatActivity
{
    //create object of DatabaseReference class to access firebase's Realtime Database
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://funartapp-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        EditText ed_register_username, ed_register_company, ed_register_email, ed_register_password, ed_register_confirm_password;
        Button btn_submit_register;
        TextView tv_move_to_login;
        List<String> job_positions;
        Spinner sp_job_position_register;


        ed_register_username = (EditText)findViewById(R.id.edit_register_username);
        ed_register_company = (EditText)findViewById(R.id.edt_register_company);
        sp_job_position_register = (Spinner)findViewById(R.id.spinner);
        ed_register_password = (EditText) findViewById(R.id.ed_confirm_pass_register1);
        ed_register_email = (EditText)findViewById(R.id.ed_email_register1);
        ed_register_confirm_password = (EditText)findViewById(R.id.ed_confirm_pass_register1);
        btn_submit_register = (Button)findViewById(R.id.btn_submit_register);
        job_positions = new ArrayDeque<>();

        btn_submit_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //Getting data from EDIT_TEXT into String
            final String username = ed_register_username.getText().toString();
            final String company = ed_register_company.getText().toString();
            final String password = ed_register_password.getText().toString();
            final String confirmPassword = ed_register_confirm_password.getText().toString();
            final String email = ed_register_email.getText().toString();
//            final String job_position = sp_job_position_register.getTransitionName().toString();

            //Check if the user fill all fields before sending data to firebase
                if(username.isEmpty() || company.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty())
                {
                    Toast.makeText(register_page.this, "Fill the fields", Toast.LENGTH_SHORT).show();
                }
//              //Check if passwords are matching with each other
                else if(!password.equals(confirmPassword)){
                    Toast.makeText(register_page.this, "Password and confirmed password are not matching", Toast.LENGTH_SHORT).show();

                }else{

                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot childSnapshot:snapshot.child("").getChildren())
                            {
                            String spinner_position = childSnapshot.child("job_position").getValue(String.class);
                            job_positions.add(spinner_position);
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(register_page.this, android.R.layout.simple_spinner_item, job_positions);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                            sp_job_position_register.setAdapter(arrayAdapter);}
                        }



                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    //Storing data to firebase database
                    databaseReference.child("user").child("username").setValue(username);
//                    databaseReference.child("user").child("job_position").setValue(job_position);
                    databaseReference.child("user").child("company").setValue(company);
                    databaseReference.child("user").child("email").setValue(email);
                    databaseReference.child("user").child("password").setValue(password);
                    databaseReference.child("user").child("confirmPassword").setValue(confirmPassword);
                    Toast.makeText(register_page.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    finish();

                }

            }
        });


        tv_move_to_login = (TextView)findViewById(R.id.tv_move_to_login);

        tv_move_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register_page.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}