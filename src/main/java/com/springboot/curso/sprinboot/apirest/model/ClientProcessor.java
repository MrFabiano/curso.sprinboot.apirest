package com.springboot.curso.sprinboot.apirest.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ClientProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {

        Client client = new Client();
        client.setIdade("teste");
        client.setNome("Fabiano");
        exchange.getMessage().setBody(client);
    }
}
