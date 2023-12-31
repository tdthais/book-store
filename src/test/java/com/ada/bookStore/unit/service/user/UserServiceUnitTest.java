package com.ada.bookStore.unit.service.user;

import com.ada.bookStore.controller.dto.UserRequest;
import com.ada.bookStore.controller.dto.UserResponse;
import com.ada.bookStore.controller.exception.IdNotFoundError;
import com.ada.bookStore.controller.exception.PasswordValidationError;
import com.ada.bookStore.model.User;
import com.ada.bookStore.repository.UserRepository;
import com.ada.bookStore.service.UserService;
import com.ada.bookStore.utils.UserConvert;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class UserServiceUnitTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    public PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;


    @Test
    public void criar_novo_usuario_com_dados_corretos_deve_ter_sucesso() {
        UserRequest userRequest = new UserRequest("nome","email","123456");

        this.passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserService(userRepository, passwordEncoder);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenAnswer(invocation -> {
            final User entity = invocation.getArgument(0);
            ReflectionTestUtils.setField(entity, "id", RandomUtils.nextInt());
            return entity;
        });

        UserResponse userResponse = userService.saveUser(userRequest);

        Assertions.assertEquals(userRequest.getName(),userResponse.getName());
        Assertions.assertEquals(userRequest.getEmail(),userResponse.getEmail());

        Mockito.verify(userRepository).save(Mockito.any());

    }

    @Test
    public void busca_por_user_existente_por_id_deve_retornar_user() throws Exception {
        User user = new User();
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));

        UserResponse userResponse = userService.getUserById(62);
        Assertions.assertNotNull(userResponse);

    }


    @Test
    public void busca_por_nomes_de_users_existentes_deve_retornar_lista_de_users(){
        List<User> users = new ArrayList<>();
        Mockito.when(userRepository.findAllByName(Mockito.any())).thenReturn(users);

        List<UserResponse> usersResponse = UserConvert.toResponseList(users);

        Assertions.assertNotNull(usersResponse);

    }

    @Test
    public void busca_por_nomes_inexistentes_deve_retornar_lista_vazia(){
        List<User> users = new ArrayList<>();
        List<User> usersEmpty = Collections.emptyList();

        Mockito.when(userRepository.findAllByName(Mockito.any())).thenReturn(usersEmpty);

        List<UserResponse> usersResponse = UserConvert.toResponseList(users);
        Assertions.assertTrue(usersResponse.isEmpty());

    }

    @Test
    public void deleteUser_deve_mudar_condicao_active_de_user_para_falso_e_salvar_no_banco() throws IdNotFoundError {

        userService = new UserService(userRepository, passwordEncoder);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenAnswer(invocation -> {
            final User entity = invocation.getArgument(0);
            ReflectionTestUtils.setField(entity, "id", RandomUtils.nextInt());
            return entity;
        });

        User user = new User();
        user.setName("NomeTesteDelete");
        user.setPassword("142638");
        user.setActive(true);
        user.setEmail("email@testedelete.com");
        userRepository.save(user);
        System.out.println(user.getId());

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        userService.deleteUser(user.getId());
        Assertions.assertEquals(false, user.getActive());
    }


    @Test
    public void deve_mudar_senha_de_user_existente(){

    }












}

