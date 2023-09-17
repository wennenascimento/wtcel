package com.example.wtcell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClienteNovo extends AppCompatActivity {

    EditText edtcliente, edtemail, edtsenha;
    TextView back;
    Button button;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_novo);

        edtcliente = findViewById(R.id.ad_name);
        edtemail = findViewById(R.id.ad_email);
        edtsenha = findViewById(R.id.ad_pass);

        FirebaseApp.initializeApp(this);

         back = findViewById(R.id.id_retu_login);
         button = findViewById(R.id.button_new);

         reference = FirebaseDatabase.getInstance().getReference("cliente");

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

                 CadastrarCliente(edtcliente.getText().toString(),
                         edtemail.getText().toString(),
                         edtsenha.getText().toString());

                 Toast.makeText(ClienteNovo.this, "Dados cadastrados com sucesso", Toast.LENGTH_LONG).show();
             }
         });

    }

    public void CadastrarCliente(String nomeCliente, String emailCliente, String senhaCliente){

        if(nomeCliente.trim().isEmpty()) edtcliente.setError("Preencha o campo Usuario!");
        else if(emailCliente.trim().isEmpty()) edtemail.setError("Preencha o campo E-mail!");
        else if (senhaCliente.trim().isEmpty()) edtsenha.setError("Preencha o campo Senha!");
        else {
            Cliente cliente = new Cliente();
            cliente.setNomeCliente(nomeCliente);
            cliente.setEmail(emailCliente);
            cliente.setSenha(senhaCliente);

            DatabaseReference getKeyId = reference.push();
            cliente.setKey(getKeyId.getKey());
            reference.child(cliente.getKey()).setValue(cliente);

        }

    }
}