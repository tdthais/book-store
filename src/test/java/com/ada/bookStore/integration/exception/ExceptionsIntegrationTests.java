package com.ada.bookStore.integration.exception;

import com.ada.bookStore.controller.UserController;
import com.ada.bookStore.controller.dto.UserRequest;
import com.ada.bookStore.repository.ProductRepository;
import com.ada.bookStore.repository.TypeProductRepository;
import com.ada.bookStore.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ExceptionsIntegrationTests {

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void criar_novo_usuario_sem_nome_deve_retornar_excecao() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/user")
                                .content("""
                                        {
                                          "email": "emailSemNome@teste.com",
                                          "password": "56971234"
                                        }                                
                                        """)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest()
                );

    }




}
