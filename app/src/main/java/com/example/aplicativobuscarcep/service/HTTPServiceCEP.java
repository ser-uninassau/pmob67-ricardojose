package com.example.aplicativobuscarcep.service;

import android.os.AsyncTask;


import com.example.aplicativobuscarcep.model.CEP;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.example.aplicativobuscarcep.model.CEP;

public class HTTPServiceCEP extends AsyncTask<Void, Void, CEP> {
    private final String cep;
    public HTTPServiceCEP(String cep) {
        this.cep = cep;
    }
    @Override
    protected CEP doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
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

        Gson gson = new Gson();
        return gson.fromJson(String.valueOf(resposta), CEP.class);

    }
}


