package com.vivo.orders.orders.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Data

public class ApplicationConfiguration {



    @Value("users.url")
    private String usersUrl ;

}
