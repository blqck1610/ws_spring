package com.example.demo.util;

public enum OrderStatus {

	WAITING("Đang đợi duyệt"), SHIPPING("Đang vận chuyển"), DONE("đã giao");
	
	private String display;
	
	private OrderStatus(String display) {
		// TODO Auto-generated constructor stub
		this.display = display;
	}
	
	public String getDisplay() {
		return display;
	}
	
}
