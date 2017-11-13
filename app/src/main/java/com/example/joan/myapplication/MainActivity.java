package com.example.joan.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Setear interfaz  R == carpeta res

        TextView textView1 = findViewById(R.id.textView1);
        textView1.setText("Esta és la Activity 1");

        Button buttonNewActivity = findViewById(R.id.buttonNewActivity);
        buttonNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(MainActivity.class.getSimpleName(), "onClick");
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        Log.d(MainActivity.class.getSimpleName(),"onCreate");
    }

    protected void onStart() {
        super.onStart();
        Log.d(MainActivity.class.getSimpleName(),"onStart");
    }

    protected void onResume() {
        super.onResume();
        Log.d(MainActivity.class.getSimpleName(),"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MainActivity.class.getSimpleName(), "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MainActivity.class.getSimpleName(), "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MainActivity.class.getSimpleName(), "onDestroy");
    }
}