package com.example.admin_login_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class user_add_project extends AppCompatActivity
{
    Button uap_btn_login;
    EditText uap_reference_no, uap_password;
    TextView uap_goback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add_project);


        uap_btn_login = (Button) findViewById(R.id.uap_btnSubmit_login);

        uap_goback = (TextView) findViewById(R.id.uap_user_add_go_back);

        uap_reference_no = (EditText) findViewById(R.id.uap_ed_reference);
        uap_password = (EditText) findViewById(R.id.uap_ed_user_pass);

        uap_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_add_project.this, user_home_page.class);
                startActivity(intent);
            }
        });


        uap_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uap_reference = uap_reference_no.getText().toString();

                if(uap_reference.isEmpty())
                {
                    Toast.makeText(user_add_project.this, "Enter your reference no and password", Toast.LENGTH_SHORT).show();

                }
                else{
                    Intent intent = new Intent(user_add_project.this, user_home_screen_map_temperature_humidity.class);
                    startActivity(intent);

                }
            }
        });



    }
}