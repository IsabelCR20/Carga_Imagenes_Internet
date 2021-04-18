package com.isa.cargaimagenes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnGriego;   // Renacentista
    Button btnRomano;   // Romano
    Button btnLeo;      // Leonardo da Vinci
    Button btnPicasso;  // PAblo Picasso
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        btnGriego = findViewById(R.id.btnGriego);
        btnRomano = findViewById(R.id.btnRomano);
        btnLeo = findViewById(R.id.btnLeo);
        btnPicasso =  findViewById(R.id.btnPicasso);

        btnGriego.setTag(new String[]{"Pintura", "Renacentista", getString(R.string.json_rena)});
        btnPicasso.setTag(new String[]{"Obras de", "Pablo Picasso", "JSON"});
        btnLeo.setTag(new String[]{"Obras de", "Leonardo da Vinci", getString(R.string.json_leo)});
        btnRomano.setTag(new String[]{"Pintura", "Romana", "JSON"});

        btnGriego.setOnClickListener(btnClick);
        btnLeo.setOnClickListener(btnClick);
        btnRomano.setOnClickListener(btnClick);
        btnPicasso.setOnClickListener(btnClick);
    }


    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String tag[] = (String[])view.getTag();
            Intent intento = new Intent(getBaseContext(), Galeria.class);
            intento.putExtra("t1", tag[0]);
            intento.putExtra("t2", tag[1]);
            intento.putExtra("t3", tag[2]);
            startActivity(intento);
        }
    };


}