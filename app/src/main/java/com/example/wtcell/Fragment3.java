package com.example.wtcell;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Fragment3 extends Fragment {

        View view;
        Button gps, update, apagar_conta, logoutcli;
        EditText edtusuario, email, senha;
        Cliente cliente;

        DatabaseReference reference;

    @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_3, container, false);

            logoutcli = view.findViewById(R.id.logoutcliente);
            edtusuario = view.findViewById(R.id.edt_user_u);
            email = view.findViewById(R.id.edt_email_u);
            senha = view.findViewById(R.id.edt_pass_u);

            update = view.findViewById(R.id.edt_button);
            apagar_conta = view.findViewById(R.id.button_del);

            FirebaseApp.initializeApp(getActivity());

            reference = FirebaseDatabase.getInstance().getReference();

            Bundle cl = getActivity().getIntent().getExtras();

            if ((cl!= null) && (cl.containsKey("cliente"))) {
                cliente  = (Cliente) cl.getSerializable("cliente");

            }else {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                Toast.makeText(getActivity(),
                        "Login expirado!", Toast.LENGTH_LONG).show();
                startActivity(intent);
                getActivity().finish();

            }

            edtusuario.setHint(cliente.getNomeCliente());
            email.setHint(cliente.getEmail());
            senha.setHint(cliente.getSenha());

            gps = view.findViewById(R.id.button_gps);
            gps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), Gpswtcell.class  );
                    startActivity(intent);

                }
            });

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateCliente(edtusuario.getText().toString(),
                            email.getText().toString(),
                            senha.getText().toString());
                }
            });

            apagar_conta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reference.child("cliente").child(cliente.getKey()).removeValue();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    System.exit(0);
                }
            });

            logoutcli.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
            return view;
        }

        public void updateCliente(String nomeCliente, String emailCliente, String senhaCliente){

            if(!nomeCliente.trim().isEmpty()) cliente.setNomeCliente(nomeCliente);
            else if(!emailCliente.trim().isEmpty()) cliente.setEmail(emailCliente);
            else if (!senhaCliente.trim().isEmpty()) cliente.setSenha(senhaCliente);
            edtusuario.setHint(cliente.getNomeCliente());
            email.setHint(cliente.getEmail());
            senha.setHint(cliente.getSenha());
            Toast.makeText(getActivity(), "Dados Cliente atualizados!", Toast.LENGTH_SHORT).show();
            reference.child("cliente").child(cliente.getKey()).setValue(cliente);


            Intent intent = new Intent(getActivity(), ClientePage.class);
            intent.putExtra("cliente", cliente);
            startActivity(intent);

        }

}
