package com.isa.cargaimagenes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.Vector;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.ViewHolder> {
    private Vector<Vector<Object>> cards;
    private Context contexto;
    private LayoutInflater inflador;
    RequestQueue cola;

    public CardsAdapter(Vector<Vector<Object>> cards, Context contexto) {
        this.cards = cards;
        this.contexto = contexto;
        this.inflador = (LayoutInflater) contexto
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View carta = inflador.inflate(R.layout.card,null);
        return new ViewHolder(carta);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Vector<Object> item = cards.get(position);
        holder.nombre.setText((String) item.elementAt(0));

        cola = Volley.newRequestQueue(contexto);
        ImageRequest solicitud = new ImageRequest(
                (String) item.elementAt(1),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        holder.imagen.setImageBitmap(response);
                    }
                }
                , 0, 0, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        holder.imagen.setImageBitmap(BitmapFactory
                                .decodeResource(contexto.getResources(),R.drawable.imgno));
                    }
                });
        solicitud.setTag("MiTag");
        cola.add(solicitud);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nombre;
        public ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.lblNombre);
            imagen = itemView.findViewById(R.id.imgObra);
        }
    }



}
