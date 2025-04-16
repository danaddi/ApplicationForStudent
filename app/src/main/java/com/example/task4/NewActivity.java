package com.example.task4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.task4.R;

public class NewActivity extends AppCompatActivity {

    private TextView textViewUserInfo, textViewSubjectInfo;
    private Button buttonEnterSubject, buttonBackToMain;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private String firstName, lastName, subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        textViewUserInfo = findViewById(R.id.textViewUserInfo);
        textViewSubjectInfo = findViewById(R.id.textViewSubjectInfo);
        buttonEnterSubject = findViewById(R.id.buttonEnterSubject);
        buttonBackToMain = findViewById(R.id.buttonBackToMain);

        Intent intent = getIntent();
        if (intent != null) {
            firstName = intent.getStringExtra("FIRST_NAME");
            lastName = intent.getStringExtra("LAST_NAME");
            textViewUserInfo.setText("Студент: " + firstName + " " + lastName);
        }

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                subject = data.getStringExtra("subject");
                                String preferredTime = data.getStringExtra("PREFERRED_TIME");
                                textViewSubjectInfo.setText("Доп. занятие: " + subject + "\nВремя: " + preferredTime);
                                Toast.makeText(NewActivity.this, "Время успешно передано", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

        buttonEnterSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewActivity.this, ThirdActivity.class);
                activityResultLauncher.launch(intent);
            }
        });

        buttonBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
