package com.ken.beans;

import java.io.Serializable;

public class CreditCard implements Serializable {
	private static final long serialVersionUID = -492988369283683312L;
	private String number;

	public CreditCard(String number) {
		this.number = number;
	}

        @Override
	public String toString() {
		return number;
	}
}