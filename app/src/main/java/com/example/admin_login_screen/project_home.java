package com.example.admin_login_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class project_home extends AppCompatActivity {

    Button ph_create_project, ph_project_name;
    TextView ph_edit_projects, ph_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_home);


        ph_create_project = (Button)findViewById(R.id.ph_btn_create_project);
        ph_project_name = (Button)findViewById(R.id.ph_btn_project_name);
        ph_edit_projects = (TextView) findViewById(R.id.ph_edit_logout);
        ph_logout = (TextView) findViewById(R.id.ph_logout);

        ph_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(project_home.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ph_edit_projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(project_home.this, project_edit.class);
                startActivity(intent);
            }
        });

        ph_create_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(project_home.this, add_project.class);
                startActivity(intent);
            }
        });

        ph_project_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(project_home.this, type_of_transport.class);
                startActivity(intent);
            }
        });



    }
}