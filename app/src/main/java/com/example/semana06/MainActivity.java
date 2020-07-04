package com.example.semana06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.semana06.adaptador.TipoReclamoAdapter;
import com.example.semana06.adaptador.UsuarioAdapter;
import com.example.semana06.entidad.Usuario;
import com.example.semana06.entidad.tiporeclamo;
import com.example.semana06.servicio.ServicioRest;
import com.example.semana06.util.ConnectionRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAddUser;
    Button btnGetUsersList;
    ListView listView;

    ServicioRest servicio;
    List<tiporeclamo> list = new ArrayList<tiporeclamo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Retrofit 2 CRUD Demo");

        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        btnGetUsersList = (Button) findViewById(R.id.btnGetUsersList);
        listView = (ListView) findViewById(R.id.listView);
        servicio = ConnectionRest.getConnection().create(ServicioRest.class);

        btnGetUsersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje("Se pulsó el listado");
                getReclamoList();
            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje("Se pulsó el agregar");
                Intent intent = new Intent(MainActivity.this, ReclamoActivity.class);
                intent.putExtra("tiporeclamo_name", "");
                startActivity(intent);
            }
        });
    }


    public void getReclamoList(){
        Call<List<tiporeclamo>> call = servicio.listaTipoReclamo();
        call.enqueue(new Callback<List<tiporeclamo>>() {
            @Override
            public void onResponse(Call<List<tiporeclamo>> call, Response<List<tiporeclamo>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new TipoReclamoAdapter(MainActivity.this, R.layout.activity_list, list));
                }
            }

            @Override
            public void onFailure(Call<List<tiporeclamo>> call, Throwable t) {
                mensaje(t.getMessage());
                Log.e("ERROR: ", t.getMessage());

            }
        });
    }

    void mensaje(String mensaje){
        Toast toast1 =  Toast.makeText(getApplicationContext(),mensaje, Toast.LENGTH_LONG);
        toast1.show();
    }
}
