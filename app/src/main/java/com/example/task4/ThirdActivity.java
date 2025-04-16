package com.example.task4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivity extends AppCompatActivity {

    private EditText dayEditText, timeEditText, commentEditText;
    private Button buttonOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dayEditText = findViewById(R.id.editDay);
        timeEditText = findViewById(R.id.editTime);
        commentEditText = findViewById(R.id.editComment);
        buttonOK = findViewById(R.id.buttonOK);
        EditText editSubject = findViewById(R.id.editSubject);


        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = editSubject.getText().toString().trim();
                String preferredTime = "День: " + dayEditText.getText().toString().trim() +
                        ", Время: " + timeEditText.getText().toString().trim() +
                        ", Комментарий: " + commentEditText.getText().toString().trim();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("PREFERRED_TIME", preferredTime);
                resultIntent.putExtra("subject", subject);
                setResult(RESULT_OK, resultIntent);
                finish();
            };
        });
    }
}