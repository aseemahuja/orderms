package com.orderms.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.orderms.app.requestvalidator.OrderMSRequestValidator;
import com.orderms.app.service.OrderMSService;
import com.orderms.model.CommonResponse;
import com.orderms.model.OrderStatusResponse;
import com.orderms.model.SubmitOrderRequest;
import com.orderms.model.SubmitOrderResponse;

@RestController
@RequestMapping(value = "/order")
public class OrderMSController {
	private static final Logger logger = LoggerFactory.getLogger(OrderMSController.class);
	
	@Autowired
	OrderMSRequestValidator validator;
	
	@Autowired
	OrderMSService service;
	
	@RequestMapping(value="/submit", method = RequestMethod.POST)
	 public ResponseEntity<SubmitOrderResponse> addProduct(@RequestBody SubmitOrderRequest request){
		
		 //Validate the request
		 validator.validateSubmitOrder(request);
		 
		 SubmitOrderResponse response = service.submitorder(request);
		 
		 response.setStatus("SUCCESS");
		 return new ResponseEntity<>(response, HttpStatus.OK);
		 
	 }
	
	@RequestMapping(value="/status/{orderNumber}", method = RequestMethod.GET)
	 public ResponseEntity<OrderStatusResponse> orderStatus(@PathVariable("orderNumber") String orderNumber){
		
		 //Validate the request
		 validator.validateOrderNumber(orderNumber);
		 
		 OrderStatusResponse response = service.checkOrderStatus();
		 
		 response.setStatus("SUCCESS");
		 return new ResponseEntity<>(response, HttpStatus.OK);
		 
	 }
	
	@RequestMapping(value="/cancel/{orderNumber}", method = RequestMethod.DELETE)
	 public ResponseEntity<CommonResponse> orderCancel(@PathVariable("orderNumber") String orderNumber){
		
		 //Validate the request
		 validator.validateOrderNumber(orderNumber);
		 
		 CommonResponse response = service.cancelOrder();
		 
		 response.setStatus("SUCCESS");
		 return new ResponseEntity<>(response, HttpStatus.OK);
		 
	 }
	
	

}
