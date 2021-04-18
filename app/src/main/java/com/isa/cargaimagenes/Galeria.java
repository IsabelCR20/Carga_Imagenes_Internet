package com.isa.cargaimagenes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class Galeria extends AppCompatActivity {
    TextView lblTitulo1;
    TextView lblTitulo2;
    RecyclerView rvCards;
    String StringJson;
    Vector<Vector<Object>> lista_cartas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_galeria);
        lblTitulo1 = findViewById(R.id.lblTitle1);
        lblTitulo2 = findViewById(R.id.lblTitle2);
        rvCards = findViewById(R.id.rvCards);

        lblTitulo1.setText(getIntent().getExtras().getString("t1"));
        lblTitulo2.setText(getIntent().getExtras().getString("t2"));
        StringJson = getIntent().getExtras().getString("t3");

        lista_cartas = new Vector<>();
        try {
            JSONObject objJSON = new JSONObject(StringJson);
            JSONArray jsonArray = objJSON.getJSONArray("obras");
            for(int i=0; i<jsonArray.length(); i++){
                Vector<Object> card = new Vector<>();
                JSONObject item = jsonArray.getJSONObject(i);
                card.add(item.getString("nombre"));
                card.add(item.getString("url"));
                lista_cartas.add(card);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("cosa", "Error: " + e.getMessage());
        }
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getBaseContext());
        CardsAdapter adaptador = new CardsAdapter(lista_cartas,getBaseContext());
        rvCards.setLayoutManager(lm);
        rvCards.setAdapter(adaptador);

    }


}