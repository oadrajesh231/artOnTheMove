package com.example.admin_login_screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import java.util.List;

import kotlin.collections.ArrayDeque;

public class position_register extends AppCompatActivity
{

    //create object of DatabaseReference class to access firebase's Realtime Database
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://funartapp-default-rtdb.firebaseio.com/");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_register);

        EditText pr_ed_register_username, pr_ed_register_company, pr_ed_register_email, pr_ed_register_password, pr_ed_register_confirm_password;
        Button pr_btn_submit_register;
        List<String> job_positions;
        Spinner sp_job_position_register;


        pr_ed_register_username = (EditText) findViewById(R.id.pr_user_reg_id_edit);
        pr_ed_register_company = (EditText) findViewById(R.id.pr_ed_company_position_register);
        sp_job_position_register = (Spinner) findViewById(R.id.spinner);
        pr_ed_register_password = (EditText) findViewById(R.id.pr_ed_password_position_register);
        pr_ed_register_email = (EditText) findViewById(R.id.pr_ed_email_position_register);
        pr_ed_register_confirm_password = (EditText) findViewById(R.id.pr_ed_confirm_pass_position_register);
        pr_btn_submit_register = (Button) findViewById(R.id.pr_btn_register_position_register);
        job_positions = new ArrayDeque<>();


        pr_btn_submit_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting data from EDIT_TEXT into String
                final String username = pr_ed_register_username.getText().toString();
                final String company = pr_ed_register_company.getText().toString();
                final String password = pr_ed_register_password.getText().toString();
                final String confirmPassword = pr_ed_register_confirm_password.getText().toString();
                final String email = pr_ed_register_email.getText().toString();
//            final String job_position = sp_job_position_register.getTransitionName().toString();

                //Check if the user fill all fields before sending data to firebase
                if (username.isEmpty() || company.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
                    Toast.makeText(position_register.this, "Fill the fields", Toast.LENGTH_SHORT).show();
                }
//              //Check if passwords are matching with each other
                else if (!password.equals(confirmPassword)) {
                    Toast.makeText(position_register.this, "Password and confirmed password are not matching", Toast.LENGTH_SHORT).show();

                } else {

                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot childSnapshot : snapshot.child("").getChildren()) {
                                String spinner_position = childSnapshot.child("job_position").getValue(String.class);
                                job_positions.add(spinner_position);
                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(position_register.this, android.R.layout.simple_spinner_item, job_positions);
                                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                                sp_job_position_register.setAdapter(arrayAdapter);
                            }
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
                    Toast.makeText(position_register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(position_register.this, position_login.class);
                    startActivity(intent);

                    finish();


                }
            }
        });
    }
}
