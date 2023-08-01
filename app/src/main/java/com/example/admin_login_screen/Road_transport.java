package com.example.admin_login_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.URL;

public class Road_transport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_transport);


        EditText destinationText = (EditText)findViewById(R.id.rt_departure);
        EditText sourceText = (EditText)findViewById(R.id.rt_arrival);
        Button btn = (Button)findViewById(R.id.rt_btnSubmit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String source = sourceText.getText().toString();
                String destination = destinationText.getText().toString();

                if(source.isEmpty() && destination.isEmpty())
                {
                    Toast.makeText(Road_transport.this, "Enter source and destination", Toast.LENGTH_SHORT).show();
                }
                else {
                    Uri uri = Uri.parse("https://google.com/maps/dir/" + source + "/" + destination);

                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.google.android.apps.maps");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });



















        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home_btn);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home_btn:
                    startActivity(new Intent(getApplicationContext(), user_home_screen_map_temperature_humidity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;

                case R.id.bottom_location_btn:
                    return true;
                case R.id.bottom_temperature_btn:
                    startActivity(new Intent(getApplicationContext(), temperature_measurement.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_chat_btn:
                    startActivity(new Intent(getApplicationContext(), chat_page.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_personal_btn:
                    startActivity(new Intent(getApplicationContext(), activity_profile.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });
    }
}