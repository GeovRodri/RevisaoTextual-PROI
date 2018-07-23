package com.example.sandeco.meuprimeirodb.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sandeco.meuprimeirodb.R;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
    EditText edtTexto;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtTexto = (EditText)findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String texto = edtTexto.getText().toString();
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }


}
