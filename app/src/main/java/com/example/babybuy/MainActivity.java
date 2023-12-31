package com.example.babybuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button start;
    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
//
//        progressBar.setVisibility(View.VISIBLE);
//        startActivity(new Intent(MainActivity.this,HomeActivity.class));
//        Toast.makeText(this, "Please wait you are already logged in", Toast.LENGTH_SHORT).show();
        //finish();

        // Check if the user is already logged in
        if (auth.getCurrentUser() != null) {
            // User is already logged in, redirect to HomeActivity
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish(); // Finish this activity to prevent going back to it
        }

        start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to LoginActivity when the "start" button is clicked
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });


    }
    public void start(View view){
//        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }
}