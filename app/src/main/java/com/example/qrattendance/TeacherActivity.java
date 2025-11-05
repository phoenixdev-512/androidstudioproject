package com.example.qrattendance;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.ArrayList;

public class TeacherActivity extends AppCompatActivity {

    ImageView qrImage;
    Button generateBtn, viewListBtn;
    
    // This will hold the live-updated list of students
    ArrayList<String> studentList = new ArrayList<>();
    
    // A unique code for this attendance session
    String sessionCode = "CLASS_SESSION_" + System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        qrImage = findViewById(R.id.qrImage);
        generateBtn = findViewById(R.id.generateBtn);
        viewListBtn = findViewById(R.id.viewListBtn);

        generateBtn.setOnClickListener(v -> {
            generateQRCode(sessionCode);
            generateBtn.setEnabled(false); // Only generate once
            Toast.makeText(this, "QR code generated for this session", Toast.LENGTH_SHORT).show();
        });

        viewListBtn.setOnClickListener(v -> {
            Intent i = new Intent(TeacherActivity.this, AttendanceListActivity.class);
            // Pass the currently observed list to the new activity
            i.putStringArrayListExtra("students", studentList);
            startActivity(i);
        });

        // *** This is the key part ***
        // Observe the FakeDatabase for changes (new students)
        FakeDatabase.studentNames.observe(this, names -> {
            studentList.clear();
            studentList.addAll(names);
            // Optional: Update a count here
        });
        
        // Auto-generate QR on load
        generateQRCode(sessionCode);
        generateBtn.setEnabled(false);
    }

    private void generateQRCode(String text) {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            int size = 512; // QR code size
            BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, size, size);
            Bitmap bmp = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565);
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            qrImage.setImageBitmap(bmp);
        } catch (WriterException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error generating QR code", Toast.LENGTH_SHORT).show();
        }
    }
}
