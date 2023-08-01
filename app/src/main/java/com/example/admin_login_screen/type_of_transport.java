package com.example.admin_login_screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class type_of_transport extends AppCompatActivity
{
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://funartapp-default-rtdb.firebaseio.com/");
    EditText tt_project_name, tt_referenceno, tt_password, tt_depart_date, tt_depart_time, tt_actual_date, tt_actual_time, tt_userno, tt_city, tt_country, tt_city1, tt_country1, tt_borrower, tt_transport;
    TextView tt_create_project, tt_go_back;
    Spinner tt_type_transport;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_transport);


        tt_create_project = (TextView) findViewById(R.id.tt_create_project);
        tt_go_back = (TextView) findViewById(R.id.tt_goback);

        tt_project_name = (EditText)findViewById(R.id.tt_projectname);
        tt_referenceno = (EditText) findViewById(R.id.tt_reference_no);
        tt_password = (EditText) findViewById(R.id.tt_password);
        tt_depart_date = (EditText) findViewById(R.id.tt_depart_date);
        tt_depart_time = (EditText) findViewById(R.id.tt_depart_time);
        tt_actual_date = (EditText) findViewById(R.id.tt_actual_date);
        tt_actual_time = (EditText) findViewById(R.id.tt_actal_time);

        tt_userno = (EditText) findViewById(R.id.tt_user_no);
        tt_city = (EditText) findViewById(R.id.tt_city);
        tt_city1 = (EditText) findViewById(R.id.tt_city1);
        tt_country = (EditText) findViewById(R.id.tt_country);
        tt_country1 = (EditText) findViewById(R.id.tt_country1);
        tt_borrower = (EditText) findViewById(R.id.tt_borrower);
        tt_transport = (EditText) findViewById(R.id.tt_transport_plan);
        tt_type_transport = (Spinner)findViewById(R.id.tt_type_transport);

        tt_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(type_of_transport.this, project_home.class);
                startActivity(intent);

            }
        });




        tt_create_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting data from EDIT_TEXT into String
                final String project_name = tt_project_name.getText().toString();
                final String reference_no = tt_referenceno.getText().toString();
                final String password = tt_password.getText().toString();
                final String depart_date = tt_depart_date.getText().toString();
                final String depart_time = tt_depart_time.getText().toString();
//
                final String actual_date = tt_actual_date.getText().toString();
                final String actual_time = tt_actual_time.getText().toString();
                final String userno = tt_userno.getText().toString();
                final String city = tt_city.getText().toString();
                final String city1 = tt_city1.getText().toString();
//
                final String country = tt_country.getText().toString();
                final String country1 = tt_country1.getText().toString();
                final String borrower = tt_borrower.getText().toString();
                final String transport_plan = tt_transport.getText().toString();
//

//            final String job_position = sp_job_position_register.getTransitionName().toString();

                //Check if the user fill all fields before sending data to firebase
                if(project_name.isEmpty() || reference_no.isEmpty() || password.isEmpty() || depart_time.isEmpty() || depart_date.isEmpty()||actual_date.isEmpty()||actual_time.isEmpty() || userno.isEmpty() || city.isEmpty() || city1.isEmpty() || country.isEmpty() || country1.isEmpty() || transport_plan.isEmpty() || borrower.isEmpty())
                {
                    Toast.makeText(type_of_transport.this, "Fill the fields", Toast.LENGTH_SHORT).show();
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
                    databaseReference.child("project").child("project_name").setValue(tt_project_name);
                    databaseReference.child("project").child("reference_number").setValue(tt_referenceno);
                    databaseReference.child("project").child("actual_date").setValue(tt_actual_date);
                    databaseReference.child("project").child("actual_time").setValue(tt_actual_time);
                    databaseReference.child("project").child("city").setValue(tt_city);
                    databaseReference.child("project").child("country").setValue(tt_country);
                    databaseReference.child("project").child("city1").setValue(tt_city1);
                    databaseReference.child("project").child("country1").setValue(tt_country1);
                    databaseReference.child("project").child("depart_date").setValue(tt_depart_date);
                    databaseReference.child("project").child("depart_time").setValue(tt_depart_time);
                    databaseReference.child("project").child("borrower").setValue(tt_borrower);
                    databaseReference.child("project").child("transport").setValue(tt_transport);
                    databaseReference.child("project").child("password").setValue(tt_password);
                    Toast.makeText(type_of_transport.this, "Type of Transport added successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(type_of_transport.this, user_home_page.class);
                    startActivity(intent);

                    finish();

                }

            }
        });


    }
}