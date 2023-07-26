package com.vivo.orders.orders.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vivo.orders.orders.dto.ProductsDto;
import com.vivo.orders.orders.dto.UserDto;
import com.vivo.orders.orders.model.ItemsDto;
import com.vivo.orders.orders.model.ResultDto;
import com.vivo.orders.orders.repository.OrdersRepository;
import com.vivo.orders.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final String STATUS_PENDING = "PENDING";
    private static final String URL_Products = "https://fakestoreapi.com/products";
    private static final String URL_USERS = "https://fakestoreapi.com/users";

    private static final int COD_SUCESS = 200;

    @Autowired
    public OrdersRepository repository;

    public ResultDto resultDto = new ResultDto();

    List<ItemsDto> itemsDtoList = new ArrayList<>();

    ItemsDto itemsDuplicate = new ItemsDto();

    public ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ResultDto order(Long id, List<ItemsDto> products) throws Exception {
        HashSet<ItemsDto> newlist = new HashSet<ItemsDto>();


        try {
            UserDto userId = findByUsers(id);
            if (userId.getId().equals(null)) {
                throw new Exception("ERRO: Usuário não possui na base de dados");
            }

            for (ItemsDto item : products) {
              findByProducts(item.getId());
            }

            resultDto.setTotalPrice((float) itemsDtoList.stream().mapToDouble(ItemsDto::getPartialAmount).sum());

            newlist.addAll(itemsDtoList);

            resultDto.setItems( newlist);
            resultDto.setStatus(STATUS_PENDING);
            resultDto.setUserId(id);
            repository.save(resultDto);

            return resultDto;
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }

    @Override
    public ResultDto updateStatus(ResultDto resultDto) throws Exception {
        findByUsers(resultDto.getUserId());
        resultDto.setStatus(resultDto.getStatus());
        repository.save(resultDto);
        return resultDto;
    }

    public ProductsDto findByProducts(Long id) throws Exception {
        String urlId = URL_Products + "/" + id;

        ItemsDto itemsDto = new ItemsDto();
        URL url = new URL(urlId);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

        if (conexao.getResponseCode() != COD_SUCESS)
            throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

        BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));

        ProductsDto productsDto =  objectMapper.readValue(resposta ,ProductsDto.class);

        itemsDtoList.stream().forEach(item -> {
            if(item.getId().equals(productsDto.getId())){
                item.setAmount(item.getAmount() + 1L);
                item.setPartialAmount(productsDto.getPrice() + item.getPartialAmount() );
                item.setId(productsDto.getId());

                itemsDuplicate.setId(productsDto.getId());
                itemsDuplicate.setPrice(productsDto.getPrice());
                itemsDuplicate.setAmount(1L);
                itemsDuplicate.setPartialAmount(productsDto.getPrice());

            }

        });
        if (itemsDto.getAmount() == null) {
            itemsDto.setAmount(1L);
            itemsDto.setId(productsDto.getId());
            itemsDto.setPartialAmount(productsDto.getPrice());
            itemsDto.setPrice(productsDto.getPrice());
        }

        itemsDtoList.add(itemsDto);
        itemsDtoList.remove(itemsDuplicate);

        return productsDto;
    }

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
    public static String converteJsonEmString(BufferedReader buffereReader) throws IOException {
        String resposta, jsonEmString = "";
        while ((resposta = buffereReader.readLine()) != null) {
            jsonEmString += resposta;
        }
        return jsonEmString;
    }

}
