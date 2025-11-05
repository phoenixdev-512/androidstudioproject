package com.example.qrattendance;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class AttendanceListActivity extends AppCompatActivity {

    ListView listView;
    TextView countText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);

        listView = findViewById(R.id.listView);
        countText = findViewById(R.id.countText);

        ArrayList<String> students = getIntent().getStringArrayListExtra("students");

        if (students != null) {
            countText.setText("Total Students: " + students.size());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    students
            );
            listView.setAdapter(adapter);
        } else {
            countText.setText("Total Students: 0");
        }
    }
}
