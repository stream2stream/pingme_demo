package com.celestial.pingme.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.celestial.pingme.services.*;



@WebMvcTest()	
class PingControllerTest
{
	@Autowired
	private	MockMvc mockMvc;
	
	@MockBean
	private PingService pingMe;		
	
	@Test
	void testGetStatus() throws Exception
	{
		ResultActions result = mockMvc.perform(get("/api/status")).andDo(print()).andExpect(status().isOk());
		
		result.andExpect(content().string(containsString("Healthy")));	
	}

	@Test
	void testGetTime()
	{
		fail("Not yet implemented");
	}

}
