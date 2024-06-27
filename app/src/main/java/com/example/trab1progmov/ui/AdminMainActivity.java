package com.example.trab1progmov.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trab1progmov.R;

public class AdminMainActivity extends AppCompatActivity {

    private Button buttonManageUsers;
    private Button buttonManageAuthors;
    private Button buttonManageGenres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        buttonManageUsers = findViewById(R.id.buttonManageUsers);
        buttonManageAuthors = findViewById(R.id.buttonManageAuthors);
        buttonManageGenres = findViewById(R.id.buttonManageGenres);

        buttonManageUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, ManageUsersActivity.class);
                startActivity(intent);
            }
        });

        buttonManageAuthors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, ManageAuthorsActivity.class);
                startActivity(intent);
            }
        });

        buttonManageGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, ManageGenresActivity.class);
                startActivity(intent);
            }
        });
    }
}
