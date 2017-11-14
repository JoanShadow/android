package com.example.joan.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity {

    private final static int REQUEST_CODE_ACTIVITY = 0;
    private TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Setear interfaz  R == carpeta res

        textView = findViewById(R.id.textView1);
        textView.setText("Esta és la Activity 1");

        Button buttonNewActivity = findViewById(R.id.buttonNewActivity);
        buttonNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(MainActivity.class.getSimpleName(), "onClick");
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                //startActivity(intent);
                Date currentDate = Calendar.getInstance().getTime();
                intent.putExtra("date", currentDate.toString());
                startActivityForResult(intent, REQUEST_CODE_ACTIVITY);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE_ACTIVITY) {
            Toast.makeText(MainActivity.this, "Intent devuelto!", Toast.LENGTH_LONG).show();

            if(data.getExtras() != null && data.getExtras().containsKey("date")) {
                String fecha = data.getExtras().getString("date");
                textView.setText(fecha);
            }
        }
    }
}