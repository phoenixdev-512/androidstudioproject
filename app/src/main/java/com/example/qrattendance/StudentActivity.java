package com.example.qrattendance;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class StudentActivity extends AppCompatActivity {

    Button scanBtn, submitBtn;
    EditText nameInput;
    TextView scanStatus;
    
    String scannedSessionCode = null;

    // New Activity Result Launcher for the QR Scanner
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(StudentActivity.this, "Scan cancelled", Toast.LENGTH_LONG).show();
                } else {
                    scannedSessionCode = result.getContents();
                    scanStatus.setText("Status: Session Scanned!");
                    Toast.makeText(StudentActivity.this, "Scanned: " + scannedSessionCode, Toast.LENGTH_LONG).show();
                    // Optional: You could verify the code format
                    submitBtn.setEnabled(true);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        scanBtn = findViewById(R.id.scanBtn);
        submitBtn = findViewById(R.id.submitBtn);
        nameInput = findViewById(R.id.nameInput);
        scanStatus = findViewById(R.id.scanStatus);

        scanBtn.setOnClickListener(v -> {
            // Launch the scanner
            ScanOptions options = new ScanOptions();
            options.setPrompt("Scan the classroom QR code");
            options.setBeepEnabled(true);
            options.setOrientationLocked(false);
            barcodeLauncher.launch(options);
        });

        submitBtn.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            if (scannedSessionCode == null) {
                Toast.makeText(this, "Please scan the class QR code first", Toast.LENGTH_SHORT).show();
            } else if (name.isEmpty()) {
                nameInput.setError("Name cannot be empty");
            } else {
                // Add the student to the static database
                FakeDatabase.addStudent(name);
                Toast.makeText(this, "Attendance marked for " + name, Toast.LENGTH_SHORT).show();
                // Clear fields and disable submit to prevent double-entry
                nameInput.setText("");
                scanStatus.setText("Status: Not Scanned");
                scannedSessionCode = null;
                submitBtn.setEnabled(false); // Re-enable after next scan
            }
        });
        
        // Initially disable submit button
        submitBtn.setEnabled(false);
    }
}
