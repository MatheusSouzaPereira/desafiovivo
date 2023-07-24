package com.vivo.orders.orders.service.impl;

import com.google.gson.Gson;
import com.vivo.orders.orders.dto.ProductsDto;
import com.vivo.orders.orders.model.ItemsDto;
import com.vivo.orders.orders.model.ResultDto;
import com.vivo.orders.orders.dto.UserDto;
import com.vivo.orders.orders.repository.OrdersRepository;
import com.vivo.orders.orders.service.OrderService;
import com.vivo.orders.orders.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {


    private static final String STATUS_PENDING = "PENDING";


    @Autowired
    private OrdersRepository repository;

    private ResultDto resultDto = new ResultDto();


    private Utils utils = new Utils();

    private static final String URL_Products = "https://fakestoreapi.com/products";

    private static final int COD_SUCESS = 200;


    List<ItemsDto> itemsDtoList = new ArrayList<>();
    @Override
    public ResultDto order(Long id, List<ItemsDto> products ) throws Exception {
        try {
        UserDto userId = utils.findByUsers(id);
        if(Boolean.TRUE.equals(userId.getId().equals(null))){
            throw new Exception("ERRO: Usuário não possui na base de dados");
        }


            for (ItemsDto item : products) {
              ProductsDto productsDto = findByProducts(item.getId());
            }

            resultDto.setStatus(STATUS_PENDING);
            resultDto.setUserId(id);
            repository.save(resultDto);


            return resultDto;
        }catch (Exception e){
            throw new Exception("ERRO: " + e);
        }
    }

    @Override
    public ResultDto updateStatus(UUID id, Long userId, String status) throws Exception {
        utils.findByUsers(userId);
        resultDto.setStatus(status);
        repository.save(resultDto);
        return resultDto;
    }

    public ProductsDto findByProducts(Long id ) throws Exception {
        String urlId = URL_Products + "/" + id ;
        try {
            ItemsDto itemsDto = new ItemsDto();
            URL url = new URL(urlId);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            if (conexao.getResponseCode() != COD_SUCESS)
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

            BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            String jsonEmString = converteJsonEmString(resposta);

            Gson gson = new Gson();
            ProductsDto productsDto = gson.fromJson(jsonEmString , ProductsDto.class);

            if(Boolean.FALSE.equals(itemsDtoList.isEmpty())){
                for (ItemsDto item : itemsDtoList) {
                    if(item.getId() == productsDto.getId()) {
                        if (itemsDto.getAmount() == null) {
                            itemsDto.setAmount(1L);
                        }
                        item.setAmount(item.getAmount() + 1L);
                        item.setPrice(productsDto.getPrice() + item.getPrice());
                        // itemsDto.setPartialAmount(item.getPartialAmount() + productsDto.getPrice());

                        resultDto.setTotalPrice(productsDto.getPrice() + item.getPrice());


                    }
                    resultDto.setTotalPrice( productsDto.getPrice() + itemsDto.getPrice());

                }
            }
            if (itemsDto.getAmount() == null) {
                itemsDto.setAmount(1L);
                itemsDto.setId(productsDto.getId());
                itemsDto.setPartialAmount(productsDto.getPrice());
                itemsDto.setPrice(productsDto.getPrice());


            }
            resultDto.setTotalPrice( productsDto.getPrice() + itemsDto.getPrice() + resultDto.getTotalPrice());


            itemsDtoList.add(itemsDto);
            resultDto.setProducts(itemsDtoList);

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
