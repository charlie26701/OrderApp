package dev.charlie.orderapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import dev.charlie.orderapp.R;

public class SettingsActivity extends AppCompatActivity {

    private ImageView backBtn;
    // 卡路里 按鈕
    private Button caloryBtn;
    // BMI 按鈕
    private Button bmiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initView();

        backBtn.setOnClickListener(v -> finish());
        caloryBtn.setOnClickListener(v -> startActivity(new Intent(this, CaloryActivity.class)));
        bmiBtn.setOnClickListener(v -> startActivity(new Intent(this, BmiActivity.class)));

    }

    private void initView() {
        backBtn = findViewById(R.id.backBtn);
        caloryBtn = findViewById(R.id.caloryBtn);
        bmiBtn = findViewById(R.id.bmiBtn);
    }
}