package com.book.read.tracker.controllertest;//package com.book.read.tracker.controllertest;


import com.book.read.tracker.data.dao.BookDao;
import com.book.read.tracker.data.entity.Book;
import com.book.read.tracker.data.entity.ReadingProgress;
import com.book.read.tracker.repository.BookRepository;
import com.book.read.tracker.repository.ReadingProgressRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;



@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;



    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void testAddBook() throws Exception {
        BookDao firstBook = new BookDao ();
        firstBook.setAuthor ( "Sam Walton" );
        firstBook.setTitle ( "Made in America" );



        mockMvc.perform( MockMvcRequestBuilders.post("/api/v1/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstBook)))
                .andExpect(MockMvcResultMatchers.status().isCreated ())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", is("Made in America".toUpperCase ())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", is("Sam Walton".toUpperCase ())));


    }


}



