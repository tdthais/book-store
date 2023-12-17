package com.ada.bookStore.integration;


import com.ada.bookStore.model.Product;
import com.ada.bookStore.model.TypeProduct;
import com.ada.bookStore.model.User;
import com.ada.bookStore.repository.ProductRepository;
import com.ada.bookStore.repository.TypeProductRepository;
import com.ada.bookStore.repository.UserRepository;
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

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductControllerIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TypeProductRepository typeProductRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void busca_de_books_por_subject_id_via_get_deve_ter_sucesso() throws Exception {
        TypeProduct bookSubject1 = new TypeProduct();
        bookSubject1.setName("AssuntoTeste1");
        typeProductRepository.save(bookSubject1);

        TypeProduct bookSubject2 = new TypeProduct();
        bookSubject2.setName("AssuntoTeste2");
        typeProductRepository.save(bookSubject2);

        Product product1 = new Product();
        product1.setName("LivroTeste1");
        product1.setBookSubject(bookSubject1);
        product1.setPrice(BigDecimal.valueOf(22));

        Product product2 = new Product();
        product2.setName("LivroTeste2");
        product2.setBookSubject(bookSubject1);
        product2.setPrice(BigDecimal.valueOf(39));

        Product product3 = new Product();
        product3.setName("LivroTeste3");
        product3.setBookSubject(bookSubject2);
        product3.setPrice(BigDecimal.valueOf(27));

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/book").param("bookSubject", String.valueOf(bookSubject1.getId()))
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        );




    }


}
