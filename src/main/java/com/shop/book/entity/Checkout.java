package com.shop.book.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Checkout {
   private String message;
    private List<String> name;
    public List<String> getName() {
		return name;
	}


	public void setName(List<String> name) {
		this.name = name;
	}

	private Double totalPrice;
   
    public Checkout() {
    }

	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}




	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
