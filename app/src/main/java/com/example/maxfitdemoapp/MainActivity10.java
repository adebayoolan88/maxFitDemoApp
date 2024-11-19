package com.example.maxfitdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main10);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView yesTextView = findViewById(R.id.yesTextView);
        yesTextView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity10.this, MainActivity11.class);
            startActivity(intent);
        });

        TextView noTextView = findViewById(R.id.noTextView);
        noTextView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity10.this, MainActivity8.class);
            startActivity(intent);
        });
    }
}
