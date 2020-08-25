package com.orderms.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderms.app.exception.ServiceException;
import com.orderms.app.productms.model.AllProductsResponse;
import com.orderms.app.restclient.ProductMSRestClient;
import com.orderms.model.CommonResponse;
import com.orderms.model.OrderStatus;
import com.orderms.model.OrderStatusResponse;
import com.orderms.model.SubmitOrderRequest;
import com.orderms.model.SubmitOrderResponse;

@Service
public class OrderMSService {
	
	@Autowired
	ProductMSRestClient productmsRestClient;
	
	public SubmitOrderResponse submitorder(SubmitOrderRequest request) {
		
		SubmitOrderResponse response = null;
		
		String allproductsResponseString = productmsRestClient.getAllProductsByZip(request.getAddress().getZipCode());
		
		//Convert String Response to a Class Object
		ObjectMapper objectmapper = new ObjectMapper();
		objectmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		AllProductsResponse allProductsResponse = null;
		try {
			allProductsResponse = objectmapper.readValue(allproductsResponseString, AllProductsResponse.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//2bd way of getting the response
		//allProductsResponse = productmsRestClient.getAllProductsByZipNew(request.getAddress().getZipCode());
		
		if(null!=allProductsResponse && allProductsResponse.getNumberOfProducts()>0
				&& allProductsResponse.getProductList()
				.stream()
				.filter(product -> product.getProductId()==request.getProductId())
				.findFirst()
				.isPresent()
				) {
			response = new SubmitOrderResponse();
			
			response.setOrderNumber("O0012345");
			
			response.setStatus("SUCCESS");
		} else {
			throw new ServiceException("");
		}
		
		return response;
		
	}

	public OrderStatusResponse checkOrderStatus() {
		OrderStatusResponse orderStatus = new OrderStatusResponse();
		orderStatus.setOrderStatus(OrderStatus.RECEIVED.toString());
		orderStatus.setStatus("SUCCESS");
		return orderStatus;
	}

	public CommonResponse cancelOrder() {
		CommonResponse response = new CommonResponse();
		response.setStatus("SUCCESS");
		return null;
	}

}
