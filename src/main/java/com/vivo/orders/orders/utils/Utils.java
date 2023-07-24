package com.vivo.orders.orders.utils;

import com.google.gson.Gson;
import com.vivo.orders.orders.model.ItemsDto;
import com.vivo.orders.orders.model.ResultDto;
import com.vivo.orders.orders.dto.ProductsDto;
import com.vivo.orders.orders.dto.UserDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.vivo.orders.orders.service.impl.OrderServiceImpl.converteJsonEmString;

public class Utils {
    private static final String URL_USERS = "https://fakestoreapi.com/users";

    private static final int COD_SUCESS = 200;


    public UserDto findByUsers(Long id ) throws Exception {
        String urlId = URL_USERS + "/" + id ;
        try {
            URL url = new URL(urlId);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            if (conexao.getResponseCode() != COD_SUCESS)
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

            BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            String jsonEmString = converteJsonEmString(resposta);

            Gson gson = new Gson();
            UserDto userDto = gson.fromJson(jsonEmString , UserDto.class);


            return userDto;
        }catch (Exception e){
            throw new Exception("ERRO: " + e);

        }

    }
}
