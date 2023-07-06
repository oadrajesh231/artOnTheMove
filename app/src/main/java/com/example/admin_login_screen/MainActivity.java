package com.example.admin_login_screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://funartapp-default-rtdb.firebaseio.com/");
    String email_pattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    Button btn_login;
    TextView tv_login_email, tv_login_pass, tv_new_user;
    EditText ed_login_email, ed_login_pass;

    Firebase mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); // hide the title bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);

        btn_login = (Button) findViewById(R.id.btn_login);

        tv_login_email = (TextView) findViewById(R.id.tv_login_email);
        tv_login_pass = (TextView) findViewById(R.id.tv_login_pass);
        tv_new_user = (TextView) findViewById(R.id.tv_login_new_user);

        ed_login_email = (EditText) findViewById(R.id.ed_login_email);
        ed_login_pass = (EditText) findViewById(R.id.ed_login_pass);

        tv_new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, register_page.class);
                startActivity(intent);
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email_login = ed_login_email.getText().toString();
                final String password = ed_login_pass.getText().toString();

                if (email_login.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter your email and password", Toast.LENGTH_SHORT).show();

                } else if (!email_login.matches(email_pattern)) {
                    ed_login_email.setError("Enter an valid email address");
                } else {
                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            final String get_email = snapshot.child("email").getValue(String.class);
                            final String get_password = snapshot.child("password").getValue(String.class);

                            if (get_password.equals(ed_login_pass) && get_email.equals(ed_login_pass)) {
                                Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, project_home.class));
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, "Enter valid email and password.", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });
    }
}