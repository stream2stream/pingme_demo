package com.celestial.pingme.controllers;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.celestial.pingme.services.PingService;


@RestController	
@RequestMapping("/api")
public class PingController 
{
	final private	String	PINGME_ENDPOINT = "PINGME_ENDPOINT"; 
	private final String TIME_API = "/api/time";
	private	PingService thePingService;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	public	PingController( PingService ps )
	{
		thePingService = ps;
	}
	
	@GetMapping("/status")
	public	String getStatus()
	{
		return "Healthy";
	}
	
	@GetMapping("/time")
	public	String getTime()
	{
		String theTime = thePingService.getTime();
		
		return theTime;
	}

	@GetMapping("/timefromhelper")
	public	String getTimex()
	{
		LocalTime time = LocalTime.now();
		String theTime = "[NOT SET]";
		String dbEnpoint = System.getenv().getOrDefault(PINGME_ENDPOINT, null);
		
		if(dbEnpoint != null )
		{
			theTime = webClientBuilder.build()
						.get()
						.uri(dbEnpoint + TIME_API)
						.retrieve()
						.bodyToMono(String.class)
						.block();
		}
		else
			theTime = "**" + thePingService.getTime();
		
		return theTime;
	}
}
