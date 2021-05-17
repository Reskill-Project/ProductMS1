package com.infy.microservices.dto;

public class SubscribedProductDTO {

	private Integer buyerId;
	private Integer ProdId;
	private Integer quantity;
	
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	public Integer getProdId() {
		return ProdId;
	}
	public void setProdId(Integer prodId) {
		ProdId = prodId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
