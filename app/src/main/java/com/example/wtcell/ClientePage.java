package com.example.wtcell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ClientePage extends AppCompatActivity {

    Cliente cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cliente);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottonsx);
        bottomNavigationView.setOnNavigationItemSelectedListener(botton_list);
        getSupportFragmentManager().beginTransaction().
        replace(R.id.views, new Fragment1()).commit();

        Bundle cl = getIntent().getExtras();
        if ((cl!= null) && (cl.containsKey("cliente"))) {
            cliente  = (Cliente) cl.getSerializable("cliente");

        }else {

            Intent intent = new Intent(ClientePage.this, MainActivity.class);
            Toast.makeText(ClientePage.this,
                    "Login expirado!", Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();

        }
    }


    BottomNavigationView.OnNavigationItemSelectedListener botton_list =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment opt = null;

                    switch (item.getItemId()){

                        case R.id.add_man:
                            opt = new Fragment1();
                            break;
                        case R.id.meus_atd:
                            opt = new Fragment2();
                            break;
                        case R.id.conta:
                            opt = new Fragment3();
                            break;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.views, opt).commit();
                    return true;
                }
            };
}