package com.example.wtcell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ClienteNovo extends AppCompatActivity {

    EditText usuario, email, senha;
    TextView back;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_novo);

        usuario = findViewById(R.id.ad_name);
        email = findViewById(R.id.ad_email);
        senha = findViewById(R.id.ad_pass);

         back = findViewById(R.id.id_retu_login);
         button = findViewById(R.id.button_new);

         back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(ClienteNovo.this, MainActivity.class);
                 startActivity(intent);
             }
         });

         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(ClienteNovo.this, "Dados cadastrados com sucesso", Toast.LENGTH_LONG).show();
             }
         });

    }
}