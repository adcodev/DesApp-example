package com.appecommerce.ecommerce;

import com.appecommerce.ecommerce.core.entity.User;
import com.appecommerce.ecommerce.core.usecase.port.in.RegisterUserCase;

import com.appecommerce.ecommerce.infrastructure.controller.UserController;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class RegisterUserCaseTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockitoBean
//    private RegisterUserCase registerUserCase;
//
//    @Test
//    void shouldCreateUser() throws Exception {
//
//        User user = new User();
//        user.setName("Darwin");
//        user.setEmail("darwin@test.com");
//        user.setRole("admin");
//
//        when(registerUserCase.register(any(User.class)))
//                .thenReturn(user);
//
//        mockMvc.perform(post("/api/user/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                    {
//                        "name":"Darwin",
//                        "email":"darwin@test.com",
//                        "role":"admin"
//                    }
//                """))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Darwin"))
//                .andExpect(jsonPath("$.email").value("darwin@test.com"))
//                .andExpect(jsonPath("$.role").value("admin"));
//    }
}
