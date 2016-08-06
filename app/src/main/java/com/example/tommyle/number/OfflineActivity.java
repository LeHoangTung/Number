package com.example.tommyle.number;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class OfflineActivity extends AppCompatActivity {
    TextView tvTap, tvTapTap, tvSecond, tvTurn, vColor;
    final int maxCountNo = 3, maxSecond = 5;
    int countNumber = 1, number = 0, maxNumber = 15, level = 16;
    boolean firstTap = false, myTurn = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        bindViews();
        tvSecond.setText(String.valueOf(maxSecond));
        tvTapTap.setText(String.format(Locale.getDefault(),"Stop At: %d", maxNumber));
    }

    private void bindViews() {
        tvTap = (TextView) findViewById(R.id.tvTap);
        tvTapTap = (TextView) findViewById(R.id.tvTapTap);
        tvSecond = (TextView) findViewById(R.id.tvSecond);
        tvTurn = (TextView) findViewById(R.id.tvTurn);
        vColor = (TextView) findViewById(R.id.vColor);
    }

    private void onCountDown() {
        countNumber = 1;
        if (!myTurn) {
            tvTap.setVisibility(View.INVISIBLE);
            tvTurn.setText(R.string.dinobia1);


        } else {
            tvTap.setVisibility(View.VISIBLE);
            tvTurn.setText(R.string.your_turn1);
            tvTap.setText(String.format(Locale.getDefault(), "+%d", countNumber));
        }
        new CountDownTimer((maxSecond + 1) * 1000 + 100, 1000) {
            @Override
            public void onTick(long millisecond) {
                tvSecond.setText(String.format(Locale.getDefault(), "%.0f", Math.ceil(millisecond / 1000) - 1));
            }

            @Override
            public void onFinish() {
                if (myTurn) {
                    number = number + countNumber;
                } else {
                    // Hard
                    int computerCount = (maxNumber - number) % 4;
                    if (computerCount == 0) {
                        Random random = new Random();
                        int i = random.nextInt(3) + 1;
                        number = number + i;
                    } else {
                        number = number + computerCount;
                    }
                    // Easy
//                        Random random = new Random();
//                        int i = random.nextInt(3) + 1;
//                        number = number + i;
                }

                vColor.setText(String.valueOf(number));
                if (number >= maxNumber) {
                    if (myTurn) {
                        tvTurn.setText(R.string.win);
                        firstTap = false;
                        maxNumber = maxNumber + level;
                        level++;
                        tvTapTap.setText(String.format(Locale.getDefault(),"Stop At: %d", maxNumber));
                    } else {
                        tvTurn.setText(R.string.lose);
                    }
                } else {
                    myTurn = !myTurn;
                    onCountDown();//de quy/ Recursive
                }

            }
        }.start();
    }

    public void onTap(View view) {
        if (!firstTap) {
            onCountDown();
            firstTap = true;
        }
        if (myTurn) {
            if (countNumber == maxCountNo) {
                countNumber = 1;
            } else {
                countNumber = countNumber + 1;
            }
            tvTap.setText(String.format(Locale.getDefault(), "+%d", countNumber));

        }
    }

    public void onMenu(View view) {
        finish();
    }
}
