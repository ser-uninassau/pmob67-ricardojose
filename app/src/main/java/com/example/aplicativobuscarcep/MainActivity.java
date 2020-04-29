package com.example.aplicativobuscarcep;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicativobuscarcep.model.CEP;
import com.example.aplicativobuscarcep.service.HTTPService;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buscarcep = findViewById(R.id.btn_BuscarCEP);
        final EditText inserircep = findViewById(R.id.txtbox_InserirCEP);
        final TextView dadoscep = findViewById(R.id.tv_dadosCEP);


        
        buscarcep.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if (inserircep.getText().toString().length() > 0 && !inserircep.getText().toString().equals("") && inserircep.getText().toString().length() == 8){
                    HTTPService service = new HTTPService(inserircep.getText().toString());
                    try {
                        CEP retorno = service.execute().get();
                        dadoscep.setText(retorno.toString());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        });

           }
}
