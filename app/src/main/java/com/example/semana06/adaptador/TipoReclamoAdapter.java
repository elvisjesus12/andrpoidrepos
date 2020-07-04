package com.example.semana06.adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.semana06.R;
import com.example.semana06.ReclamoActivity;
import com.example.semana06.entidad.tiporeclamo;

import java.util.List;

public class TipoReclamoAdapter extends ArrayAdapter<tiporeclamo> {

    private Context context;
    private List<tiporeclamo> tiporeclamos;

    public TipoReclamoAdapter(Context context, int resource, List<tiporeclamo> objects) {
        super(context, resource, objects);
        this.context = context;
        this.tiporeclamos = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_list, parent, false);


        TextView txtUserId = (TextView) rowView.findViewById(R.id.txtUserId);
        TextView txtUsername = (TextView) rowView.findViewById(R.id.txtUsername);

        txtUserId.setText(String.format("#ID: %d", tiporeclamos.get(pos).getIdtipoReclamo()));
        txtUsername.setText(String.format("Descripcion: %s", tiporeclamos.get(pos).getDescripcion()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReclamoActivity.class);
                intent.putExtra("tiporeclamo_id", String.valueOf(tiporeclamos.get(pos).getIdtipoReclamo()));
                intent.putExtra("tiporeclamo_name", tiporeclamos.get(pos).getDescripcion());
                intent.putExtra("tiporeclamo_estado", tiporeclamos.get(pos).getEstado());
                intent.putExtra("tiporeclamo_fecha", tiporeclamos.get(pos).getFechaRegistro());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
