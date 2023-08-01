package com.example.admin_login_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class user_home_page extends AppCompatActivity
{

    Button uhp_create_project, uhp_project_name;
    TextView uhp_edit_projects;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);

        uhp_create_project = (Button)findViewById(R.id.uhp_btn_create_project);
        uhp_project_name = (Button)findViewById(R.id.uhp_btn_project_name);
        uhp_edit_projects = (TextView) findViewById(R.id.uhp_edit_project);

        uhp_edit_projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_home_page.this, user_project_edit.class);
                startActivity(intent);
            }
        });

        uhp_create_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_home_page.this, user_add_project.class);
                startActivity(intent);
            }
        });

        uhp_project_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_home_page.this, type_of_transport.class);
                startActivity(intent);



            }
        });


    }
}