package com.example.admin_login_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class position_login extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://funartapp-default-rtdb.firebaseio.com/");
    String email_pattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    Button btn_login_pl;
    TextView pl_tv_move_to_register;
    EditText pl_ed_login_email, pl_ed_login_pass, pl_ed_password_confirm;
    Firebase mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_login);



        btn_login_pl = (Button) findViewById(R.id.pl_btnSubmit_login);

        pl_tv_move_to_register = (TextView) findViewById(R.id.pl_tv_move_to_register);

        pl_ed_login_email = (EditText) findViewById(R.id.pl_email_id_edit);
        pl_ed_login_pass = (EditText) findViewById(R.id.pl_pass_id_edit);
        pl_ed_password_confirm = (EditText) findViewById(R.id.pl_confirm_pass_edit);


        pl_tv_move_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(position_login.this, position_register.class);
                startActivity(intent);
            }
        });

        btn_login_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pl_email_login = pl_ed_login_email.getText().toString();
                final String pl_password = pl_ed_login_pass.getText().toString();
                final String pl_password_confirm = pl_ed_password_confirm.getText().toString();

                if (pl_email_login.isEmpty() || pl_password.isEmpty() || pl_password_confirm.isEmpty()) {
                    Toast.makeText(position_login.this, "Enter your email, password and confirm password", Toast.LENGTH_SHORT).show();

                } else if (pl_email_login.equals("jonas@gmail.com") || pl_password.equals("pass123") || pl_password_confirm.equals(pl_password)) {
                    Toast.makeText(position_login.this, "You are registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(position_login.this, project_home.class);
                    startActivity(intent);


                } else {
                    Toast.makeText(position_login.this, "Please enter correct email and password...", Toast.LENGTH_SHORT).show();
                }

            }
        });






    }
}