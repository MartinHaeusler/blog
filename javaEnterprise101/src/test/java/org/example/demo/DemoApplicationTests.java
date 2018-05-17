package org.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup(){
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

	@Test
	public void contextLoads() {
	}

	@Test
	public void testHelloWorldEndpoint() throws Exception {
        var response = this.mvc.perform(
                get("/api/hello-world")
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertEquals("Hello World!", response);
    }

}
