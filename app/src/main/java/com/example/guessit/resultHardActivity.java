package com.example.guessit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class resultHardActivity extends AppCompatActivity {

    private boolean result;
    private boolean result1;
    private ImageView imageView;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_hard);


        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        result = getIntent().getBooleanExtra("Result", false);
        int getTime = getIntent().getIntExtra("time", 1);
        int getChance = getIntent().getIntExtra("nochance", 1);


        if(result) {
            textView.setText("You Win !");
            imageView.setImageResource(R.drawable.win);
        }
        else if(!result || !(getTime == 0) || !(getChance == 0)){
            textView.setText("You Lose !");
            imageView.setImageResource(R.drawable.lose);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(resultHardActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}