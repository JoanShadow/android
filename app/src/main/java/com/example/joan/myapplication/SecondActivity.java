package com.example.joan.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by JoanP on 13/11/2017.
 */

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        if(getIntent().getExtras() != null && getIntent().getExtras().containsKey("date")) {
            /*TextView lblMensajeRecibido = findViewById(R.id.lblMensajeRecibido);
            String fecha = getIntent().getExtras().getString("date");
            lblMensajeRecibido.setText(fecha);*/
            ((TextView)findViewById(R.id.lblMensajeRecibido)).setText(getIntent().getExtras().getString("date"));
        }

        Button btnDevolverResultado = findViewById(R.id.btnDevolverResultado);
        btnDevolverResultado.setOnClickListener(new View.OnClickListener()  {
            @Override
                public void onClick(View v) {
                Intent resultIntent = new Intent();
                Date currentDate = Calendar.getInstance().getTime();
                resultIntent.putExtra("date", currentDate.toString());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}