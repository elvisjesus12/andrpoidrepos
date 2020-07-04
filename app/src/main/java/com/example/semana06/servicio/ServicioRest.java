package com.example.semana06.servicio;

import com.example.semana06.entidad.Usuario;
import com.example.semana06.entidad.tiporeclamo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServicioRest {



    @GET("tiporeclamo")
    public abstract Call<List<tiporeclamo>> listaTipoReclamo();

    @POST("tiporeclamo")
    public abstract Call<tiporeclamo> agregaTipoReclamo(@Body tiporeclamo user);

    @PUT("tiporeclamo")
    public abstract Call<tiporeclamo> actualizaTipoReclamo(@Body tiporeclamo user);

    @DELETE("tiporeclamo/{idtipoReclamo}")
    public abstract Call<tiporeclamo> eliminaTipoReclamo(@Path("idtipoReclamo") int id);





    @GET("usuario")
    public abstract Call<List<Usuario>> listaUsuario();

    @POST("usuario")
    public abstract Call<Usuario> agregaUsuario(@Body Usuario user);

    @PUT("usuario")
    public abstract Call<Usuario> actualizaUsuario(@Body Usuario user);

    @DELETE("usuario/{idUsuario}")
    public abstract Call<Usuario> eliminaUsuario(@Path("idUsuario") int id);

}
