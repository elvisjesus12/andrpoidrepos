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
import com.example.semana06.entidad.Usuario;


import java.util.List;

public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    private Context context;
    private List<Usuario> users;

    public UsuarioAdapter(Context context, int resource, List<Usuario> objects) {
        super(context, resource, objects);
        this.context = context;
        this.users = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_list, parent, false);


        TextView txtUserId = (TextView) rowView.findViewById(R.id.txtUserId);
        TextView txtUsername = (TextView) rowView.findViewById(R.id.txtUsername);

        txtUserId.setText(String.format("#ID: %d", users.get(pos).getId()));
        txtUsername.setText(String.format("NOMBRE: %s", users.get(pos).getName()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReclamoActivity.class);
                intent.putExtra("user_id", String.valueOf(users.get(pos).getId()));
                intent.putExtra("user_name", users.get(pos).getName());
                intent.putExtra("user_email", users.get(pos).getEmail());
                intent.putExtra("user_phone", users.get(pos).getPhone());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
