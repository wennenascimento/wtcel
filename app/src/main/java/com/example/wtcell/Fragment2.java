package com.example.wtcell;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {


        View view;
        Cliente cliente;
        TextView textAPI;
        Button load_api;


        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_2, container, false);

            textAPI = view.findViewById(R.id.textView14);
            load_api = view.findViewById(R.id.load_api);


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

            load_api.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                WriteJSON api = new WriteJSON();
                api.execute("https://api.mocki.io/v2/549a5d8b/Top250Movies");
                }
            });

            return view;
        }

    private class WriteJSON extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = WebAPI.conexaoHTTP(strings[0]);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            textAPI.setText(s);
        }

    }
}
