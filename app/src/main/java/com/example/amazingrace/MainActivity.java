package com.example.amazingrace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtDiem;
    ImageButton ibtnPlay;
    CheckBox cbOne, cbTwo, cbThree;
    SeekBar skOne, skTwo, skThree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();
        CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long millisUntilFinished) {
                int max = 7;
                Random random = new Random();
                int oneNum = random.nextInt(max);
                int secondNum = random.nextInt(max);
                int thirdNum = random.nextInt(max);
                skOne.setProgress(skOne.getProgress() + oneNum);
                skTwo.setProgress(skTwo.getProgress() + secondNum);
                skThree.setProgress(skThree.getProgress() + thirdNum);

                if (skOne.getProgress() >= skOne.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "ONE WIN", Toast.LENGTH_SHORT).show();
                }
                if (skTwo.getProgress() >= skTwo.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "TWO WIN", Toast.LENGTH_SHORT).show();
                }
                if (skThree.getProgress() >= skThree.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "THREE WIN", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFinish() {

            }
        };

        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ibtnPlay.setVisibility(View.INVISIBLE);
                skOne.setProgress(0);
                skTwo.setProgress(0);
                skThree.setProgress(0);
                countDownTimer.start();
            }
        });

    }

    private void mapping(){
        txtDiem = (TextView)  findViewById(R.id.txtScore);
        ibtnPlay = (ImageButton) findViewById(R.id.btnPlay);
        cbOne = (CheckBox) findViewById(R.id.checkbox1);
        cbTwo = (CheckBox) findViewById(R.id.checkbox2);
        cbThree = (CheckBox) findViewById(R.id.checkbox3);
        skOne = (SeekBar)  findViewById(R.id.seekbar1);
        skTwo = (SeekBar)  findViewById(R.id.seekbar2);
        skThree = (SeekBar)  findViewById(R.id.seekbar3);

    }
}