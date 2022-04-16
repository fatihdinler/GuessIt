package com.example.guessit;

import static com.example.guessit.R.color.time;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class normalGame extends AppCompatActivity {
    private TextView timerTxt;
    private TextView leftChanceTxt;
    private TextView predictTxt;
    private EditText editTextNumber;
    private Button predictBtn;
    private int number;
    int tried = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_game);

        timerTxt = findViewById(R.id.timerTxt);
        leftChanceTxt = findViewById(R.id.leftChanceTxt);
        predictTxt = findViewById(R.id.predictTxt);
        editTextNumber = findViewById(R.id.editTextNumber);
        predictBtn = findViewById(R.id.predictBtn);


        Random r = new Random();
        number = r.nextInt(101);
        Log.e("Değer", String.valueOf(number));

        Toast.makeText(normalGame.this, "This level consist of \n0-101\n45 seconds\n10 chances.", Toast.LENGTH_LONG).show();

        new CountDownTimer(45000, 1000) {

            /**
             * Do something for every single second.
             */
            public void onTick(long millisUntilFinished) {
                timerTxt.setText("00 : " + String.valueOf(millisUntilFinished / 1000));
                int a = (int) (millisUntilFinished / 1000);
                if (a < 10)
                    timerTxt.setTextColor(getResources().getColor(time));
            }

            /**
             * When time is done
             */
            public void onFinish() {
                timerTxt.setText("Done ! ");
                Intent i = new Intent(normalGame.this, resultNormalActivity.class);
                i.putExtra("time", 0);
                startActivity(i);
                finish();
            }
        }.start();

        predictBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tried--;
                leftChanceTxt.setText("Left : " + String.valueOf(tried));
                predictTxt.setText("Prediction : " + String.valueOf(editTextNumber.getText().toString()));

                if(editTextNumber.getText().toString().matches("")) {
                    Toast.makeText(normalGame.this, "Predict a Number !", Toast.LENGTH_SHORT).show();
                    tried++;
                }
                else {
                    int prediction = Integer.parseInt(editTextNumber.getText().toString());

                    if(tried <= 0) {
                        Intent intent = new Intent(normalGame.this, resultNormalActivity.class);
                        intent.putExtra("nochance", 0); // If no chance
                        startActivity(intent);
                        finish();
                    }

                    if(prediction == number) {
                        Intent intent = new Intent(normalGame.this, resultNormalActivity.class);
                        intent.putExtra("Result", true); // If correct answer
                        startActivity(intent);
                        finish();
                    }

                    else if(prediction < number) {
                        Toast.makeText(normalGame.this, "Your number is small !", Toast.LENGTH_SHORT).show();
                    }

                    else if(prediction > number) {
                        Toast.makeText(normalGame.this, "Your number is large !", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
    }
}