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

public class add_project extends AppCompatActivity {

    //create object of DatabaseReference class to access firebase's Realtime Database
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://funartapp-default-rtdb.firebaseio.com/");
    EditText ap_project_name, ap_referenceno, ap_password, ap_depart_date, ap_depart_time, ap_actual_date, ap_actual_time, ap_userno, ap_city, ap_country, ap_city1, ap_country1, ap_borrower, ap_transport;
    TextView ap_create_project, ap_go_back;
    Spinner ap_type_transport;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        ap_create_project = (TextView) findViewById(R.id.ap_create_project);
        ap_go_back = (TextView) findViewById(R.id.ap_goback);

        ap_project_name = (EditText)findViewById(R.id.ap_projectname);
        ap_referenceno = (EditText) findViewById(R.id.ap_reference_no);
        ap_password = (EditText) findViewById(R.id.ap_password);
        ap_depart_date = (EditText) findViewById(R.id.ap_depart_date);
        ap_depart_time = (EditText) findViewById(R.id.ap_depart_time);
        ap_actual_date = (EditText) findViewById(R.id.ap_actual_date);
        ap_actual_time = (EditText) findViewById(R.id.ap_actal_time);

        ap_userno = (EditText) findViewById(R.id.ap_user_no);
        ap_city = (EditText) findViewById(R.id.ap_city);
        ap_city1 = (EditText) findViewById(R.id.ap_city1);
        ap_country = (EditText) findViewById(R.id.ap_country);
        ap_country1 = (EditText) findViewById(R.id.ap_country1);
        ap_borrower = (EditText) findViewById(R.id.ap_borrower);
        ap_transport = (EditText) findViewById(R.id.ap_transport_plan);
        ap_type_transport = (Spinner)findViewById(R.id.ap_type_transport);

        ap_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(add_project.this, project_home.class);
                startActivity(intent);

            }
        });







        ap_create_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting data from EDIT_TEXT into String
                final String project_name = ap_project_name.getText().toString();
                final String reference_no = ap_referenceno.getText().toString();
                final String password = ap_password.getText().toString();
                final String depart_date = ap_depart_date.getText().toString();
                final String depart_time = ap_depart_time.getText().toString();
//
                final String actual_date = ap_actual_date.getText().toString();
                final String actual_time = ap_actual_time.getText().toString();
                final String userno = ap_userno.getText().toString();
                final String city = ap_city.getText().toString();
                final String city1 = ap_city1.getText().toString();
//
                final String country = ap_country.getText().toString();
                final String country1 = ap_country1.getText().toString();
                final String borrower = ap_borrower.getText().toString();
                final String transport_plan = ap_transport.getText().toString();
//

//            final String job_position = sp_job_position_register.getTransitionName().toString();

                //Check if the user fill all fields before sending data to firebase
                if(project_name.isEmpty() || reference_no.isEmpty() || password.isEmpty() || depart_time.isEmpty() || depart_date.isEmpty()||actual_date.isEmpty()||actual_time.isEmpty() || userno.isEmpty() || city.isEmpty() || city1.isEmpty() || country.isEmpty() || country1.isEmpty() || transport_plan.isEmpty() || borrower.isEmpty())
                {
                    Toast.makeText(add_project.this, "Fill the fields", Toast.LENGTH_SHORT).show();
                }
                else{

                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot childSnapshot : snapshot.child("").getChildren()) {
//                                String spinner_position = childSnapshot.child("job_position").getValue(String.class);
//                                type_of_transport.add(spinner_position);
//                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(register_page.this, android.R.layout.simple_spinner_item, job_positions);
//                                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
//                                sp_job_position_register.setAdapter(arrayAdapter);}
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    //Storing data to firebase database
                    databaseReference.child("project").child("project_name").setValue(ap_project_name);
                    databaseReference.child("project").child("project_name").setValue(ap_referenceno);
                    databaseReference.child("project").child("project_name").setValue(ap_actual_date);
                    databaseReference.child("project").child("project_name").setValue(ap_actual_time);
                    databaseReference.child("project").child("project_name").setValue(ap_city);
                    databaseReference.child("project").child("project_name").setValue(ap_country);
                    databaseReference.child("project").child("project_name").setValue(ap_city1);
                    databaseReference.child("project").child("project_name").setValue(ap_country1);
                    databaseReference.child("project").child("project_name").setValue(ap_depart_date);
                    databaseReference.child("project").child("project_name").setValue(ap_depart_time);
                    databaseReference.child("project").child("project_name").setValue(ap_borrower);
                    databaseReference.child("project").child("project_name").setValue(ap_transport);
                    databaseReference.child("project").child("project_name").setValue(ap_password);
                    Toast.makeText(add_project.this, "Project added successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(add_project.this, user_home_page.class);
                    startActivity(intent);

                    finish();

                }

            }
        });


    }
}