package com.webapp.demorest;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("stocks")
public class StockResource 
{
	//will return object
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Stock> getStock()
	{
		System.out.println("get stock called...");
		Stock s1 = new Stock();
		s1.setName("Facebook");
		s1.setPrice(170);
		s1.setDown(false);

		Stock s2 = new Stock();
		s2.setName("Google");
		s2.setPrice(340);
		s2.setDown(true);
		
		List<Stock> stocks = Arrays.asList(s1, s2);

		
		return stocks;
	}
	
}
