package com.example.wtcell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText email, senha;
    TextView conta_nv;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.cmp_email);
        senha = findViewById(R.id.cmp_pass);
        button = findViewById(R.id.acess_log);
        conta_nv = findViewById(R.id.add_conta);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Home_Cliente.class);
                startActivity(intent);
            }
        });

        conta_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClienteNovo.class);
                startActivity(intent);
            }
        });

    }
}