package com.example.qrattendance;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button teacherBtn, studentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teacherBtn = findViewById(R.id.teacherBtn);
        studentBtn = findViewById(R.id.studentBtn);

        teacherBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TeacherActivity.class));
        });

        studentBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, StudentActivity.class));
        });
    }
}
