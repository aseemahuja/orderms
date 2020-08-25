package com.orderms.app.service;

import org.springframework.stereotype.Service;

import com.orderms.model.CommonResponse;
import com.orderms.model.OrderStatus;
import com.orderms.model.OrderStatusResponse;
import com.orderms.model.SubmitOrderResponse;

@Service
public class OrderMSService {
	
	public SubmitOrderResponse submitorder() {
		SubmitOrderResponse response = new SubmitOrderResponse();
		
		response.setStatus("SUCCESS");
		
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
