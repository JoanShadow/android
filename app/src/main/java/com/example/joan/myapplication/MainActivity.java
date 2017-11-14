package com.example.joan.myapplication;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity implements View.OnClickListener {

    private final static String TAG = MainActivity.class.getSimpleName();
    private final static int REQUEST_CODE_ACTIVITY = 0;
    private TextView textView = null;
    //private String fechaRetorno = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main); // Setear interfaz  R == carpeta res
        textView = findViewById(R.id.textView1);
        textView.setText("Esta és la Activity 1");

        Button buttonNewActivity = findViewById(R.id.buttonNewActivity);
        Button btnSendEmail = findViewById(R.id.btnSendEmail);

        buttonNewActivity.setOnClickListener(this);
        btnSendEmail.setOnClickListener(this);

        Log.d(TAG,"onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "onSaveInstanceState");
        if(textView != null) {
            outState.putString("fechaRetorno", textView.getText().toString());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null && savedInstanceState.containsKey("fechaRetorno")) {
            Log.d(TAG, "onRestoreInstanceState");
            //fechaRetorno = savedInstanceState.getString("fechaRetorno");
            textView.setText(savedInstanceState.getString("fechaRetorno"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE_ACTIVITY) {
            Toast.makeText(MainActivity.this, "Intent devuelto!", Toast.LENGTH_LONG).show();

            if(data.getExtras() != null && data.getExtras().containsKey("date")) {
                //fechaRetorno = data.getExtras().getString("date");
                textView.setText(data.getExtras().getString("date"));
            }
        }
    }

    @Override
    public void onClick(View v) {

       switch (v.getId()) {
           case R.id.btnSendEmail:
               // Enviar email
               Intent intentEmail = new Intent(Intent.ACTION_SENDTO);
               intentEmail.setData(Uri.parse("mailto:"));
               intentEmail.putExtra(Intent.EXTRA_EMAIL, "email@email.com");
               intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Asunto email");
               try {
                   startActivity(Intent.createChooser(intentEmail, "Selecciona app email"));
               } catch (ActivityNotFoundException e) {
                   e.printStackTrace();
                   Toast.makeText(this, "No se ha encontrado ningún gestor de correo electrónico!", Toast.LENGTH_SHORT).show();
               }
               break;
           case R.id.buttonNewActivity:
               // Abrir la nueva actibity
               Log.d(TAG, "OnClick");
               Intent intent = new Intent(MainActivity.this, SecondActivity.class);
               Date currentDate = Calendar.getInstance().getTime();
               intent.putExtra("date", currentDate.toString());
               startActivityForResult(intent, REQUEST_CODE_ACTIVITY);
               break;
           default:
               // Comportamiento por defecto
               break;
       }
    }
}