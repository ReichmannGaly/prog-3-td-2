package integration;

import app.foot.FootApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(classes = FootApi.class)
@AutoConfigureMockMvc
public class HealthIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void ping_ok() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/ping"))
                .andReturn()
                .getResponse();

        String actual = response.getContentAsString();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("pong", actual);
    }
}
