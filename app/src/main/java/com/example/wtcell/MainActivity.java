package com.example.wtcell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText edtemail, edsenha;
    TextView conta_nv;
    Button button;

    Cliente cliente = new Cliente();
    Cliente clienteEnviar= new Cliente();
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        edtemail = findViewById(R.id.cmp_email);
        edsenha = findViewById(R.id.cmp_pass);
        button = findViewById(R.id.acess_log);
        conta_nv = findViewById(R.id.add_conta);
        reference = FirebaseDatabase.getInstance().getReference("cliente");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginClinte(edtemail.getText().toString(), edsenha.getText().toString());
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

    public void loginClinte(String email, String senha){

        if(email.trim().isEmpty()) edtemail.setError("Preencha o campo E-mail!");
        else if(senha.trim().isEmpty()) edsenha.setError("Preencha o campo Senha!");
        else {

            Query query = reference.orderByChild("email")
                    .equalTo(email);

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        cliente = snapshot1.getValue(Cliente.class);
                        clienteEnviar = cliente;
                    }


                    if(clienteEnviar.getSenha().equals(senha)){

                        Intent intent = new Intent(MainActivity.this, ClientePage.class);
                        intent.putExtra("cliente", clienteEnviar);
                        startActivity(intent);
                        finish();

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
    }
}