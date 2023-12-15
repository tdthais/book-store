package com.ada.bookStore.unit.service.user;

import com.ada.bookStore.controller.dto.UserResponse;
import com.ada.bookStore.model.User;
import com.ada.bookStore.repository.UserRepository;
import com.ada.bookStore.service.UserService;
import com.ada.bookStore.utils.UserConvert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class UserServiceUnitTest {

    private User user;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
//        user = new User();
////        user.setId(1);
//        user.setName("maria");
//        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));

    }

    @Test
    public void busca_por_user_existente_por_id_deve_retornar_user(){

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
        Optional<User> userResponse =  userRepository.findById(62);
        Assertions.assertNotNull(userResponse);
    }

    @Test
    public void busca_por_user_inexistente_por_id_deve_retornar_excecao(){
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Optional<User> userResponse =  userRepository.findById(62);
        Assertions.assertTrue(userResponse.isEmpty());

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
    public void deve_mudar_condicao_active_de_user_existente_para_falso(){
        User user = new User();
        user.setId(4);
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));

        userService.deleteUser(4);
    }
//
////    @Test
////    public nao_deve_mudar_condicao_active_de_user_inexistente_para_falso(){
////
////    }
//
//    @Test
//    public void deve_mudar_nome_de_user_existente(){
//
//    }
//
//    @Test
//    public void deve_mudar_email_de_user_existente(){
//
//    }
//
//    @Test
//    public void deve_mudar_senha_de_user_existente(){
//
//    }
//











}

