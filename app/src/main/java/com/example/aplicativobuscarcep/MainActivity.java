package com.example.aplicativobuscarcep;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aplicativobuscarcep.GoogleGeoCoding.GoogleGeoCode;
import com.example.aplicativobuscarcep.GoogleGeoCoding.GoogleGeoResult;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicativobuscarcep.model.CEP;
import com.example.aplicativobuscarcep.service.HTTPService;
import com.example.aplicativobuscarcep.service.HTTPServiceCEP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;


public class MainActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button mostrarmapa = findViewById(R.id.btn_MostrarMapa);
        final Button buscarcep = findViewById(R.id.btn_BuscarCEP);
        final EditText inserircep = findViewById(R.id.txtbox_InserirCEP);
        final TextView dadoscep = findViewById(R.id.tv_dadosCEP);
        final String[] coordenadas = new String[2];

        buscarcep.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if (inserircep.getText().toString().length() > 0 && !inserircep.getText().toString().equals("") && inserircep.getText().toString().length() == 8){
                    HTTPServiceCEP service = new HTTPServiceCEP(inserircep.getText().toString());
                    try {
                        CEP retorno = service.execute().get();
                        dadoscep.setText(retorno.toString());
                        mostrarmapa.setVisibility(View.VISIBLE);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    dadoscep.setText("CEP INVÁLIDO");
                }


            }
        });

        mostrarmapa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (inserircep.getText().toString().length() > 0 && !inserircep.getText().toString().equals("") && inserircep.getText().toString().length() == 8){
                    HTTPService service = new HTTPService(inserircep.getText().toString());
                    try {
                        GoogleGeoCode geoCode = service.execute().get();
                        for( GoogleGeoResult item : geoCode.getResults()){
                            coordenadas[0] =  item.getGeometry().getLocation().getLat();
                            coordenadas[1] =  item.getGeometry().getLocation().getLng();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
            }
        });

       /* coordenadas cd = (coordenadas) getApplicationContext();

        cd.setLatitude(Double.parseDouble(coordenadas[0]));
        cd.setLongitude(Double.parseDouble(coordenadas[1]));*/



    }


}
