package com.example.guessit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonEasyGame, buttonNormalGame, buttonHardGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonEasyGame = findViewById(R.id.buttonEasyGameScreen);
        buttonNormalGame = findViewById(R.id.buttonNormalGameScreen);
        buttonHardGame = findViewById(R.id.buttonHardGameScreen);

        /** If user choose the easy game, then below code will be executed. */
        buttonEasyGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, easyGame.class);
                startActivity(intent);
                finish();
            }
        });

        /** If user choose the intermediate game, then below code will be executed. */
        buttonNormalGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, normalGame.class);
                startActivity(intent);
                finish();
            }
        });

        /** If user choose the hard game, then below code will be executed. */
        buttonHardGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, hardGame.class);
                startActivity(intent);
                finish();
            }
        });

    }
}