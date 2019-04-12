package com.webapp.demorest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Stock 
{
	private String name;
	private int price;
	private boolean down;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean getDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	
}
