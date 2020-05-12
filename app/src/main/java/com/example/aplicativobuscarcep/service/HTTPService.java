package com.example.aplicativobuscarcep.service;

import android.os.AsyncTask;

import com.example.aplicativobuscarcep.model.CEP;
import com.google.gson.Gson;
import com.example.aplicativobuscarcep.GoogleGeoCoding.GoogleGeoCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HTTPService extends AsyncTask<Void, Void, GoogleGeoCode> {
    private final String cep;
    public HTTPService(String cep) {
        this.cep = cep;
    }
    @Override
    protected GoogleGeoCode doInBackground(Void... voids) {

        StringBuilder gresp = new StringBuilder();  // Para GEOCODE

        StringBuilder resposta = new StringBuilder(); // Para CEP


        if (this.cep != null && this.cep.length() == 8){
        try {
            URL url = new URL("https://viacep.com.br/ws/"+ this.cep +"/json/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","application/json");
            connection.setConnectTimeout(5000);
            connection.connect();
            BufferedReader rd = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = rd.readLine()) != null){
                resposta.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

        CEP cep = new Gson().fromJson(resposta.toString(), CEP.class);
        String logradouro = cep.getLogradouro().replace(" ", "+");
        String cidade = cep.getLocalidade().replace(" ", "+");
        String uf = cep.getUf();


        try {
            URL urlGoogleApi = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+logradouro+","+cidade+","+uf+"\n"+"&key=AIzaSyBFPd77MVVIm0XDNtOcUoHoVS91739uO48");
            HttpURLConnection connection = (HttpURLConnection) urlGoogleApi.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();

            BufferedReader rd = new BufferedReader(new InputStreamReader(urlGoogleApi.openStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                gresp.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GoogleGeoCode geoCode = new Gson().fromJson(gresp.toString(), GoogleGeoCode.class);

        return geoCode;


    }
}
