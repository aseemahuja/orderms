package com.orderms.app.restclient;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.orderms.app.exception.ServiceException;
import com.orderms.app.productms.model.AllProductsResponse;

import org.springframework.http.HttpStatus;

@Service
public class ProductMSRestClient {
	
	RestTemplate restTemplate;
	
	public ProductMSRestClient (RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public String getAllProductsByZip(int zipCode) {
		String url = "http://localhost:8080/product/all/" + zipCode;
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		if(null==responseEntity || HttpStatus.OK !=responseEntity.getStatusCode()) {
			throw new ServiceException("PRODUCT_MS_GET_ALL_PRODUCTS_FAILED");
		}
		return responseEntity.getBody();
		
		
	}
	
	public AllProductsResponse getAllProductsByZipNew(int zipCode) {
		String url = "http://localhost:8080/product/all/" + zipCode;
		AllProductsResponse allProductsResponse = restTemplate.getForObject(url, AllProductsResponse.class);
		if(null==allProductsResponse) {
			throw new ServiceException("PRODUCT_MS_GET_ALL_PRODUCTS_FAILED");
		}
		return allProductsResponse;
		
		
	}

}
