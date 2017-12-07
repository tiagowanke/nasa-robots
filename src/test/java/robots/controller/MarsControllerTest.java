package robots.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MarsController.class, secure = false)
public class MarsControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void test() throws Exception {

        final String path = "/rest/mars/%s";

        // curl -s --request POST http://localhost:8080/rest/mars/MMRMMRMM
        // Saída esperada: (2, 0, S)
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(String.format(path, "MMRMMRMM")).accept(MediaType.TEXT_PLAIN);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals("(2, 0, S)", result.getResponse().getContentAsString());

        // Entrada: curl -s --request POST http://localhost:8080/rest/mars/MML
        // Saída esperada: (0, 2, W)
        requestBuilder = MockMvcRequestBuilders.post(String.format(path, "MML")).accept(MediaType.TEXT_PLAIN);
        result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals("(0, 2, W)", result.getResponse().getContentAsString());

        // Entrada: curl -s --request POST http://localhost:8080/rest/mars/MML
        // Saída esperada: (0, 2, W)
        requestBuilder = MockMvcRequestBuilders.post(String.format(path, "MML")).accept(MediaType.TEXT_PLAIN);
        result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals("(0, 2, W)", result.getResponse().getContentAsString());

        // curl -s --request POST http://localhost:8080/rest/mars/AAA
        // Saída esperada: 400 Bad Request
        requestBuilder = MockMvcRequestBuilders.post(String.format(path, "AAA")).accept(MediaType.TEXT_PLAIN);
        result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals("400 Bad Request", result.getResponse().getContentAsString());

        // curl -s --request POST http://localhost:8080/rest/mars/MMMMMMMMMMMMMMMMMMMMMMMM
        // Saída esperada: 400 Bad Request
        requestBuilder = MockMvcRequestBuilders.post(String.format(path, "MMMMMMMMMMMMMMMMMMMMMMMM")).accept(MediaType.TEXT_PLAIN);
        result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals("400 Bad Request", result.getResponse().getContentAsString());

    }
}

