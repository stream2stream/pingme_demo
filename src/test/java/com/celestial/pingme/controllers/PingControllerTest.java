package com.celestial.pingme.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.celestial.pingme.services.PingService;


// Configure JUnit to work with SpringBoot
// Start the SpringBoot context but not the server
@WebMvcTest	
class PingControllerTest
{
	@Autowired
	private	MockMvc mockMvc;
	// Create the SpringBoot HTTP layer for testing
	
	@MockBean
	private PingService pingMe;		
	// PringCotroller is dependent on the PingService, so add a mocked version to the SpringBoot context
	
	@Test
	void testGetStatus() throws Exception
	{
		ResultActions result = mockMvc.perform(get("/api/status")).andDo(print()).andExpect(status().isOk());
		
		result.andExpect(content().string(containsString("Healthy")));	
	}

	@Test
	void testGetTime() throws Exception
	{
		// arrange
		String expectedResult = "[pingMe time: 11:36:52.707503300]";
		Mockito.when(pingMe.getTime()).thenReturn( expectedResult );
		
		// act
		ResultActions result = mockMvc.perform(get("/api/time"));
		
		// assert
		result.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(expectedResult)));	
	}

}
