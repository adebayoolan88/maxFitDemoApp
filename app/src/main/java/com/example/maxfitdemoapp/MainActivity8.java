package com.example.maxfitdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView addWorkoutPageNav = findViewById(R.id.addWorkoutPageNav);
        addWorkoutPageNav.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity8.this, MainActivity9.class);
            startActivity(intent);
        });


        ImageView homePageNav = findViewById(R.id.HomePageNav);
        homePageNav.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity8.this, MainActivity4.class);
            startActivity(intent);
        });
    }
}
