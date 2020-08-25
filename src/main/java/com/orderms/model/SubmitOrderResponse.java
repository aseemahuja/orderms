package com.orderms.model;

public class SubmitOrderResponse extends CommonResponse{
	
	String orderNumber;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

}
