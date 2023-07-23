package com.vivo.orders.orders.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vivo.orders.orders.config.ApplicationConfiguration;
import com.vivo.orders.orders.dto.ItemsDto;
import com.vivo.orders.orders.dto.ResultDto;
import com.vivo.orders.orders.model.ProductsDto;
import com.vivo.orders.orders.repository.OrdersRepository;
import com.vivo.orders.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final String URL_Products = "https://fakestoreapi.com/products";
    private static final int COD_SUCESS = 200;

    @Autowired
    private OrdersRepository repository;



    ItemsDto itens = new ItemsDto();


    @Override
    public ResultDto order(Long id, List<ItemsDto> products ) throws Exception {
        ResultDto resultDto = new ResultDto();

        //Pegar varios id que vem dentro do products e fazer com que ele busque no findbyproducts
        for (ItemsDto item : products) {
            ResultDto resul = new ResultDto();

            ProductsDto productsDto  = findByProducts(item.getId());
            itens.setPrice(productsDto.getPrice());

        }
        resultDto.getProducts().add(itens);
//
//        //Percorrer uma lista de products e intertar no ResultDTO
//        for (ItemsDto item : products) {
//            itens.setId(item.getId());
//            itens.setPrice(item.getPrice());
//
//        }

        resultDto.setUserId(id);
        repository.save(resultDto);


         return resultDto ;
    }

    private ProductsDto findByProducts(Long id ) throws Exception {
        String urlId = URL_Products + "/" + id ;
         try {
             URL url = new URL(urlId);
             HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

             if (conexao.getResponseCode() != COD_SUCESS)
                 throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

             BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
             String jsonEmString = converteJsonEmString(resposta);

             Gson gson = new Gson();
             ProductsDto productsDto = gson.fromJson(jsonEmString , ProductsDto.class);

             return productsDto;
         }catch (Exception e){
             throw new Exception("ERRO: " + e);

         }

    }

    public static String converteJsonEmString(BufferedReader buffereReader) throws IOException {
        String resposta, jsonEmString = "";
        while ((resposta = buffereReader.readLine()) != null) {
            jsonEmString += resposta;
        }
        return jsonEmString;
    }


}
