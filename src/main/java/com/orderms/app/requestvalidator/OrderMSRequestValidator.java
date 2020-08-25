package com.orderms.app.requestvalidator;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.orderms.app.exception.ServiceException;
import com.orderms.model.SubmitOrderRequest;

@Component
public class OrderMSRequestValidator {
	
	public void validateSubmitOrder(SubmitOrderRequest request) {
		if(null==request || StringUtils.isEmpty(request.getProductId()) || null==request.getAddress() || StringUtils.isEmpty(request.getDate()))
		throw new ServiceException("SUBMIT_REQUEST_INVALID");
	}

	public void validateOrderNumber(String orderNumber) {
		if(StringUtils.isEmpty(orderNumber)) {
			throw new ServiceException("INVALID_ORDER_NUMBER");
		}
		
	}
	
	

}
