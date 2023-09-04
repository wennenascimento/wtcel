package com.example.wtcell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home_Cliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cliente);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottonsx);
        bottomNavigationView.setOnNavigationItemSelectedListener(botton_list);
        getSupportFragmentManager().beginTransaction().
        replace(R.id.views, new Fragment1()).commit();
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