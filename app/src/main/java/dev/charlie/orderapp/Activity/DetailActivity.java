package dev.charlie.orderapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import dev.charlie.orderapp.Domain.FoodDomain;
import dev.charlie.orderapp.Helper.ManagmentCart;
import dev.charlie.orderapp.R;

public class DetailActivity extends AppCompatActivity {
    private Button addToCartBtn;
    private TextView plusBtn, minusBtn, titleTxt, feeTxt, descriptionTxt, numberOrderTxt, startTxt, caloryTxt, timeTxt;
    private ImageView picFood;
    private FoodDomain object;
    private int numberOrder = 1;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        managmentCart = new ManagmentCart(DetailActivity.this);

        initView();
        getBundle();
    }

    private void getBundle() {
        object = (FoodDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPicUrl(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("$" + object.getPrice());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText("" + numberOrder);
        caloryTxt.setText(object.getEnergy() + " 大卡");
        startTxt.setText(object.getScore() + "");
        timeTxt.setText(object.getTime() + " 分");
        addToCartBtn.setText("加入購物車 - $" + Math.round(numberOrder * object.getPrice()));

        plusBtn.setOnClickListener(v -> {
            numberOrder = numberOrder + 1;
            numberOrderTxt.setText("" + numberOrder);
            addToCartBtn.setText("加入購物車 - $" + Math.round(numberOrder * object.getPrice()));
        });

        minusBtn.setOnClickListener(v -> {
            numberOrder = numberOrder - 1;
            numberOrderTxt.setText("" + numberOrder);
            addToCartBtn.setText("加入購物車 - $" + Math.round(numberOrder * object.getPrice()));
        });

        addToCartBtn.setOnClickListener(v -> {
            object.setNumberinCart(numberOrder);
            managmentCart.insertFood(object);
            finish();
        });
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        timeTxt = findViewById(R.id.timeTxt);
        feeTxt = findViewById(R.id.priceTxt);
        titleTxt=findViewById(R.id.titleTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberItemTxt);
        plusBtn = findViewById(R.id.plusCardBtn);
        minusBtn = findViewById(R.id.MinusCartBtn);
        picFood = findViewById(R.id.foodPic);
        startTxt = findViewById(R.id.StarTxt);
        caloryTxt = findViewById(R.id.calTxt);

    }
}