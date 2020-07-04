package com.example.semana06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.semana06.entidad.Usuario;
import com.example.semana06.entidad.tiporeclamo;
import com.example.semana06.servicio.ServicioRest;
import com.example.semana06.util.ConnectionRest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReclamoActivity extends AppCompatActivity {

    ServicioRest servicio;
    EditText edtUId, edtDescripcion, edtEstado,edtFecha ;
    Button btnSave;
    Button btnDel;
    TextView txtUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamo);

        setTitle("tiporeclamo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = (TextView) findViewById(R.id.txtUId);
        edtUId = (EditText) findViewById(R.id.edtUId);
        edtDescripcion = (EditText) findViewById(R.id.edtDescripcion);
        edtEstado = (EditText) findViewById(R.id.edtEstado);
        edtFecha = (EditText) findViewById(R.id.edtFecha);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        servicio = ConnectionRest.getConnection().create(ServicioRest.class);

        Bundle extras = getIntent().getExtras();
        final String userId = extras.getString("tiporeclamo_id");
        String name = extras.getString("tiporeclamo_name");
        String estado = extras.getString("tiporeclamo_estado");
        String fecha = extras.getString("tiporeclamo_fecha");

        edtUId.setText(userId);
        edtDescripcion.setText(name);
        edtEstado.setText(estado);
        edtFecha.setText(fecha);

        if(userId != null && userId.trim().length() > 0 ){
            edtUId.setFocusable(false);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje("Se pulsó agregar o actualizar");
                tiporeclamo u = new tiporeclamo();

                u.setDescripcion(edtDescripcion.getText().toString());
                u.setEstado(edtEstado.getText().toString());
                u.setFechaRegistro(edtFecha.getText().toString());
                if(userId != null && userId.trim().length() > 0){
                    u.setIdtipoReclamo(Integer.parseInt(userId));
                    updateTipoReclamo(u);
                } else {
                    addReclamo(u);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje("Se pulsó eliminar");
                deleteReclamo(Integer.parseInt(userId));
                Intent intent = new Intent(ReclamoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addReclamo(tiporeclamo u){
        Call<tiporeclamo> call = servicio.agregaTipoReclamo(u);
        call.enqueue(new Callback<tiporeclamo>() {
            @Override
            public void onResponse(Call<tiporeclamo> call, Response<tiporeclamo> response) {
                mensaje("-->" + response.isSuccessful());
                if(response.isSuccessful()){
                    Toast.makeText(ReclamoActivity.this, "User created successfully!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<tiporeclamo> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateTipoReclamo(tiporeclamo u){
        Call<tiporeclamo> call = servicio.actualizaTipoReclamo(u);
        call.enqueue(new Callback<tiporeclamo>() {
            @Override
            public void onResponse(Call<tiporeclamo> call, Response<tiporeclamo> response) {
                mensaje("-->" + response.isSuccessful());
                  if(response.isSuccessful()){
                    Toast.makeText(ReclamoActivity.this, "User updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<tiporeclamo> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteReclamo(int idtipoReclamo){
        mensaje("1");
        Call<tiporeclamo> call = servicio.eliminaTipoReclamo(idtipoReclamo);
        call.enqueue(new Callback<tiporeclamo>() {
            @Override

            public void onResponse(Call<tiporeclamo> call, Response<tiporeclamo> response) {
                mensaje("2");
                mensaje("3->" + response.isSuccessful());
                if(response.isSuccessful()){
                    Toast.makeText(ReclamoActivity.this, "User deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<tiporeclamo> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void mensaje(String mensaje){
        Toast toast1 =  Toast.makeText(getApplicationContext(),mensaje, Toast.LENGTH_LONG);
        toast1.show();
    }
}
