package com.orderms.app.productms.model;

import java.util.List;

import com.orderms.model.CommonResponse;

public class AllProductsResponse extends CommonResponse{
	int zipCode;
	List<Product> productList;
	int numberOfProducts;
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public int getNumberOfProducts() {
		return numberOfProducts;
	}
	public void setNumberOfProducts(int numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}

}
