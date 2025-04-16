package com.example.miaudote.API;

import com.example.miaudote.model.Usuario;
import com.example.miaudote.model.Adotante;
import com.example.miaudote.model.Ong;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/usuarios/login") // ou o endpoint que você usou no Spring Boot
    Call<Usuario> login(@Body Usuario usuario);

    @POST("/adotantes")
    Call<Void> cadastrarAdotante(@Body Adotante adotante);

    @POST("/ongs")
    Call<Void> cadastrarOng(@Body Ong ong);



}
