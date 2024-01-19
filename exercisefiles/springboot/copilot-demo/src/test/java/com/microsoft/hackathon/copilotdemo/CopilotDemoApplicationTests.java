package com.microsoft.hackathon.copilotdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;;


@SpringBootTest()
@AutoConfigureMockMvc 
class CopilotDemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
	void hello() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/hello?key=world"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("hello world"));
	}

	@Test
	void helloNoKey() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("key not passed"));
	}

	// Test diffdates
	@Test
	void diffdates() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/diffdates?date1=01-01-2021&date2=01-02-2021"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("31"));
	}

	// test validatephone
	@Test
	void validatephone() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/validatephone?phone=+34666666666"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("true"));
	}

	@Test
	void validatephone2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/validatephone?phone=+3466666666"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("false"));
	}

	// test /color
	@Test
	void color() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/color/red"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("#FF0000"));
	}

}