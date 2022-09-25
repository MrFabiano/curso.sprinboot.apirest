package com.springboot.curso.sprinboot.apirest.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
@Component
public class SimpleRouteBuilder extends RouteBuilder {


    @Autowired
    private final ClientProcessor clientProcessor;

    public SimpleRouteBuilder(ClientProcessor clientProcessor) {
        this.clientProcessor = clientProcessor;
    }


    @Override
    public void configure() throws Exception {
          rest("/")
                  .post("/client")
                  .id("direct-client")
                  .toD("direct:client");
          from("direct:client")
                  .process(clientProcessor).marshal().json(JsonLibrary.Jackson, Client.class)
                  //.setHeader(Exchange.HTTP_METHOD, simple(String.valueOf(POST)))
                  .setHeader(Exchange.CONTENT_TYPE, constant(APPLICATION_JSON_VALUE))
                  .log("Message received from mongodb : ${body}")
                  .toD("direct:insertMongoDb")
                  .end();

          from("direct:insertMongoDb").routeId("insertMongoDb")
                  .toD("mongodb:connectionBean?database=databasename&collection=me&operation=insert");



    }
}
