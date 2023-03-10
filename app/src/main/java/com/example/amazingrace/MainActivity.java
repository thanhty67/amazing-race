package com.example.amazingrace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    int sodiem = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();
        DisableSeekBar();
        txtDiem.setText(sodiem+"");
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
                // kiểm tra win 1
                if (skOne.getProgress() >= skOne.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "ONE WIN", Toast.LENGTH_SHORT).show();
                    //trả điểm
                    if(cbOne.isChecked()) {
                        sodiem +=10;
                        Toast.makeText(MainActivity.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    }else {
                        sodiem -=10;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai rồi", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(sodiem+"");
                    EnableCheckBox();
                }
                // kiểm tra win 2
                if (skTwo.getProgress() >= skTwo.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "TWO WIN", Toast.LENGTH_SHORT).show();
                    if(cbTwo.isChecked()) {
                        sodiem +=10;
                        Toast.makeText(MainActivity.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    }else {
                        sodiem -=10;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai rồi", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(sodiem+"");
                    EnableCheckBox();
                }
                // kiểm tra win 3
                if (skThree.getProgress() >= skThree.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "THREE WIN", Toast.LENGTH_SHORT).show();
                    if(cbThree.isChecked()) {
                        sodiem +=10;
                        Toast.makeText(MainActivity.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    }else {
                        sodiem -=10;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai rồi", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(sodiem+"");
                    EnableCheckBox();
                }
            }

            @Override
            public void onFinish() {

            }
        };

        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()) {
                    ibtnPlay.setVisibility(View.INVISIBLE);
                    skOne.setProgress(0);
                    skTwo.setProgress(0);
                    skThree.setProgress(0);
                    countDownTimer.start();
                    DisableCheckBox();
                } else {
                    Toast.makeText(MainActivity.this, "Please bet before play",Toast.LENGTH_SHORT).show();
                }
            }
        });
        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    //bỏ check 2 3
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });
        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cbOne.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });
        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cbOne.setChecked(false);
                    cbTwo.setChecked(false);
                }
            }
        });

    }
    private void EnableCheckBox() {
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }
    private void DisableCheckBox() {
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
    }
    private void DisableSeekBar() {
        skOne.setEnabled(false);
        skTwo.setEnabled(false);
        skThree.setEnabled(false);
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