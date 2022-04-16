package com.example.guessit;

import static com.example.guessit.R.color.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.Random;

public class easyGame extends AppCompatActivity {

    private TextView timerTxt;
    private TextView leftChanceTxt;
    private TextView predictTxt;
    private EditText editTextNumber;
    private Button predictBtn;
    private int number;
    int tried = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_game);

        timerTxt = findViewById(R.id.timerTxt);
        leftChanceTxt = findViewById(R.id.leftChanceTxt);
        predictTxt = findViewById(R.id.predictTxt);
        editTextNumber = findViewById(R.id.editTextNumber);
        predictBtn = findViewById(R.id.predictBtn);

        Toast.makeText(easyGame.this, "This level consist of \n0-51\n60 seconds\n15 chances.", Toast.LENGTH_LONG).show();


        Random r = new Random();
        number = r.nextInt(51);
//        Log.e("Değer", String.valueOf(number));

        new CountDownTimer(60000, 1000) {

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
                Intent i = new Intent(easyGame.this, resultActivity.class);
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
                    Toast.makeText(easyGame.this, "Predict a Number !", Toast.LENGTH_SHORT).show();
                    tried++;
                }
                else {
                    int prediction = Integer.parseInt(editTextNumber.getText().toString());

                    if(tried <= 0) {
                        Intent intent = new Intent(easyGame.this, resultActivity.class);
                        intent.putExtra("nochance", 0); // If no chance
                        startActivity(intent);
                    }

                    if(prediction == number) {
                        Intent intent = new Intent(easyGame.this, resultActivity.class);
                        intent.putExtra("Result", true); // If correct answer
                        startActivity(intent);
                    }

                    else if(prediction < number) {
                        Toast.makeText(easyGame.this, "Your number is small !", Toast.LENGTH_SHORT).show();
                    }

                    else if(prediction > number) {
                        Toast.makeText(easyGame.this, "Your number is large !", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });





    }
}

//    /** For every single clicking, increment the 'tried' value. */
//    tried--;
//
//    /** Convert the predicted value to int. */
//    int prediction = Integer.parseInt(editTextNumber.getText().toString());
//
//    /** Users can see their prediction on the screen. */
//                predictTxt.setText("Predicted : " + editTextNumber.getText().toString());
//
//                if(editTextNumber.getText().length() == 0)
//            Toast.makeText(easyGame.this, "Predicted value cannot be empty ! ", Toast.LENGTH_SHORT).show();
//                else {
//        if(prediction == number) {
//            Intent i = new Intent(easyGame.this, resultActivity.class);
//            i.putExtra("resultEasy", true);
//            i.putExtra("triedEasy", tried);
//            startActivity(i);
//            finish();
//            return; // If the prediction is true, then do not do the rest.
//        }
//
//        if(prediction < number) {
//            Toast.makeText(easyGame.this, "Your prediction is small ! ", Toast.LENGTH_SHORT).show();
//        }
//
//        if(prediction > number) {
//            Toast.makeText(easyGame.this, "Your prediction is large ! ", Toast.LENGTH_SHORT).show();
//        }
//
//    }
//}

