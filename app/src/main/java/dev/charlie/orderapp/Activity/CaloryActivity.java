package dev.charlie.orderapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import dev.charlie.orderapp.R;

public class CaloryActivity extends AppCompatActivity {

    private ImageView backBtn;
    private EditText editTextWeight, editTextHeight, editTextAge;
    private RadioGroup radioGroupGender, radioGroupActivityLevel;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calory);

        initView();

        backBtn.setOnClickListener(v -> finish());
        calculateButton.setOnClickListener(view -> calculateCalories());
    }

    private void initView() {
        backBtn = findViewById(R.id.backBtn);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextAge = findViewById(R.id.editTextAge);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioGroupActivityLevel = findViewById(R.id.radioGroupActivityLevel);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
    }

    private void calculateCalories() {
        // 取得使用者輸入的資訊
        double weight = Double.parseDouble(editTextWeight.getText().toString());
        double height = Double.parseDouble(editTextHeight.getText().toString());
        int age = Integer.parseInt(editTextAge.getText().toString());

        // 取得選擇的性別
        RadioButton selectedGenderRadioButton = findViewById(radioGroupGender.getCheckedRadioButtonId());
        String gender = selectedGenderRadioButton.getText().toString();

        // 取得選擇的活動水平
        RadioButton selectedActivityRadioButton = findViewById(radioGroupActivityLevel.getCheckedRadioButtonId());
        double activityLevel = getActivityLevelMultiplier(selectedActivityRadioButton.getText().toString());

        // 計算基礎代謝率（BMR）
        double bmr;
        if (gender.equals("男性")) {
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }

        // 計算總卡路里需求
        double totalCalories = bmr * activityLevel;

        // 顯示結果
        resultTextView.setText("每日所需卡路里：" + totalCalories + " 大卡");
    }

    private double getActivityLevelMultiplier(String activityLevel) {
        switch (activityLevel) {
            case "靜態（很少或沒有運動）":
                return 1.2;
            case "輕度活動（每週1-3天輕度運動）":
                return 1.375;
            case "中度活動（每週3-5天中度運動）":
                return 1.55;
            case "高度活動（每週6-7天高強度運動）":
                return 1.725;
            case "極度活動（極強運動或每天雙重訓練）":
                return 1.9;
            default:
                return 1.0; // 默認為靜態（很少或沒有運動）
        }
    }
}