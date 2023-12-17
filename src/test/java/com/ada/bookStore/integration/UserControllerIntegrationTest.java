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

import java.util.ArrayList;
import java.util.List;

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
    public void salvar_user_no_banco_via_post_com_dados_validos_deve_ter_sucesso() throws Exception {
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
                MockMvcResultMatchers.status().is2xxSuccessful()
        );
    }
    @Test
    public void busca_de_user_por_id_valida_via_get_deve_ter_sucesso() throws Exception {
        User user = new User();
        user.setName("mario");
        user.setEmail("mario@teste.com");
        user.setPassword("78631524");
        userRepository.save(user);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/user/{id}", user.getId())
                .accept(MediaType.APPLICATION_JSON)
                        ).andDo(MockMvcResultHandlers.print()).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        );
    }

    @Test
    public void busca_de_lista_de_users_por_nome_existente_via_get_deve_retornar_lista_nao_vazia() throws Exception {
        User user1 = new User();
        user1.setName("lucia");
        user1.setEmail("lucia@teste.com");
        user1.setPassword("1234569852");
        User user2 = new User();
        user2.setName("julia");
        user2.setEmail("julia@teste.com");
        user2.setPassword("75632414");
        User user3 = new User();
        user3.setName("lucia");
        user3.setEmail("lucia32@teste.com");
        user3.setPassword("69853214");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/user/name/{name}", user1.getName())
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        );
    }

    @Test
    public void deletar_user_existente_via_delete_deve_ter_sucesso() throws Exception {
        User user = new User();
        user.setName("lucia");
        user.setEmail("lucia@teste.com");
        user.setPassword("1234569852");
        userRepository.save(user);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/user/{id}", user.getId())
        ).andDo(MockMvcResultHandlers.print()).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        );

    }

    @Test
    public void atualizar_dados_de_user_existente_via_put_deve_ter_sucesso() throws Exception {
        User user = new User();
        user.setName("lucia");
        user.setEmail("lucia@teste.com");
        user.setPassword("1234569852");
        userRepository.save(user);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/user/{id}", user.getId())
                        .content("""
                                {
                                  "name": "lucia",
                                  "email": "lucia236@teste.com",
                                  "password": "6325897"
                                }                                
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        );



    }






}
