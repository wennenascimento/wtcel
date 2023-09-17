package com.example.wtcell;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class Fragment1 extends Fragment {


        View view;
        EditText modelo, marca, contato, problem;
        ImageView camera;
        Button button;

        Bitmap foto;
        Cliente cliente;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_1, container, false);

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

        modelo = view.findViewById(R.id.id_model);
        marca = view.findViewById(R.id.id_marca);
        contato = view.findViewById(R.id.id_ctt);
        problem = view.findViewById(R.id.id_prb);

        camera = view.findViewById(R.id.id_foto);
        button = view.findViewById(R.id.add_atdd);

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 0);


            }

            camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AcessCamera();
                }
            });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificarUsuario mNew = new NotificarUsuario(getActivity());

                NotificationCompat.Builder avisoGerado =
                        mNew.builder
                                (" Seu atendiemento está em análise.. "
                                        ,"Obrigado pela preferência");

                mNew.getManager().notify(new Random().nextInt(), avisoGerado.build());

            }
        });



            return view;
        }

    public void AcessCamera(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);

        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == getActivity().RESULT_OK ){

            Bundle extras = data.getExtras();
            foto = (Bitmap) extras.get("data");
            camera.setImageBitmap(foto);

        }

        super.onActivityResult(requestCode, resultCode , data);
    }



}
