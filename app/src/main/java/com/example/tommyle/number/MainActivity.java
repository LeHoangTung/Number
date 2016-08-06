package com.example.tommyle.number;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStart(View view) {
        Intent intent = new Intent(MainActivity.this,OfflineActivity.class);
        startActivity(intent);
    }

    public void onTop(View view) {
    }
}
