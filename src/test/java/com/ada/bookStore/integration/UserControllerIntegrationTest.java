package com.ada.bookStore.integration;

import com.ada.bookStore.controller.UserController;
import com.ada.bookStore.controller.dto.UserRequest;
import com.ada.bookStore.model.User;
import com.ada.bookStore.repository.UserRepository;
import com.ada.bookStore.service.UserService;
import jakarta.transaction.Transactional;
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

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserControllerIntegrationTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;



    @Test
    public void salvar_user_no_banco_via_post_deve_ter_sucesso() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .content("""
                                {
                                  "name": "alex",
                                  "email": "alex@teste.com",
                                  "password": "1236547"
                                }                                
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        ).andDo(MockMvcResultHandlers.print()).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }
    @Test
    public void busca_de_user_por_id_via_get_deve_ter_sucesso() throws Exception {
        User user = new User();
        user.setName("mario");
        user.setEmail("mario@teste.com");
        user.setPassword("78631524");
        userRepository.save(user);

//        UserRequest userRequest = new UserRequest("mario","mario@teste.com","78631524");
//        userController.saveUser(userRequest);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/user/{id}", user.getId())
                .accept(MediaType.APPLICATION_JSON)
                        ).andDo(MockMvcResultHandlers.print()).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        );
    }




}
