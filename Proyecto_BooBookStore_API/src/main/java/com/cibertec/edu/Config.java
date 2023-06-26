package com.cibertec.edu;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

	@Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
			
			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				// TODO Auto-generated method stub
				
			}
		});
        return restTemplate;
    }
	
}
