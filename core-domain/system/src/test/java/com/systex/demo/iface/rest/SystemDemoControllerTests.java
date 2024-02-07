package com.systex.demo.iface.rest;

import com.systex.demo.springboot.SystemApplication;
import com.systex.f68w.Library;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = SystemDemoController.class)
@ContextConfiguration(classes = SystemApplication.class)
class SystemDemoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void hello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/demo")
                                              .accept("application/json"))
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk())
               .andExpect(MockMvcResultMatchers.content()
                                               .string("Hello World!"));
    }

    @Test
    void testLibrary() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/demo/lib")
                                              .accept("application/json"))
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk())
               .andExpect(MockMvcResultMatchers.content()
                                               .string(new Library().newMethodDemo()));

    }

}
