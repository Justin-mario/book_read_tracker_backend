package com.book.read.tracker.controllertest;//package com.book.read.tracker.controllertest;


import com.book.read.tracker.data.dao.BookDao;
import com.book.read.tracker.exception.BookNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.is;




@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String baseUrl = "/api/v1/book";


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup ( webApplicationContext ).build ();
    }


    @Test
    @DirtiesContext
    void testAddBook() throws Exception {
        //given
        BookDao firstBook = new BookDao ();
        firstBook.setAuthor ( "Sam Walton" );
        firstBook.setTitle ( "Made in America" );

        //when/then
        mockMvc.perform ( MockMvcRequestBuilders.post ( baseUrl )
                        .contentType ( MediaType.APPLICATION_JSON )
                        .content ( objectMapper.writeValueAsString ( firstBook ) ) )
                .andExpect ( MockMvcResultMatchers.status ().isCreated () )
                .andExpect ( MockMvcResultMatchers.jsonPath ( "$.title", is ( "Made in America".toUpperCase () ) ) )
                .andExpect ( MockMvcResultMatchers.jsonPath ( "$.author", is ( "Sam Walton".toUpperCase () ) ) );
    }

    @Test
    @DirtiesContext
    void shouldGetAllBooks() throws Exception {
        mockMvc.perform ( MockMvcRequestBuilders.get ( baseUrl + "/all" )
                        .contentType ( MediaType.APPLICATION_JSON ) )
                .andExpect ( MockMvcResultMatchers.status ().isOk () )
                .andExpect ( MockMvcResultMatchers.jsonPath ( "$.size()", is ( 7 ) ) );
    }


    @Test
    @DirtiesContext
    void shouldGetBookByTitle() throws Exception {
        String bookTitle = "/by-title/Arrow Of God";
        mockMvc.perform ( MockMvcRequestBuilders.get ( baseUrl + bookTitle )
                        .contentType ( MediaType.APPLICATION_JSON ) )
                .andExpect ( MockMvcResultMatchers.status ().isOk () )
                .andExpect ( MockMvcResultMatchers.jsonPath ( "$.title", is ( "ARROW OF GOD" ) ) );
    }

    @Test
    void shouldGetBookByBookId() throws Exception {
        long bookId = 1L;
        mockMvc.perform ( MockMvcRequestBuilders.get ( baseUrl + "/by-id/" + bookId )
                .contentType ( MediaType.APPLICATION_JSON ))
                .andExpect ( MockMvcResultMatchers.status ().isOk () )
                .andExpect ( MockMvcResultMatchers.jsonPath ( "$.author", is ( "CHINUA ACHEBE" ) ) )
                .andExpect ( MockMvcResultMatchers.jsonPath ( "$.title", is ( "ARROW OF GOD" ) ));
    }

    @Test
    void shouldGetAllBooksByAnAuthor() throws Exception {
        String author = "CHINUA ACHEBE";

        mockMvc.perform ( MockMvcRequestBuilders.get ( baseUrl + "/by-author/" + author )
                .contentType ( MediaType.APPLICATION_JSON ) )
                .andExpect ( MockMvcResultMatchers.status ().isOk () )
                .andExpect ( MockMvcResultMatchers.jsonPath ( "$.size()", is ( 2 ) ) );
    }

    @Test
    void shouldGetAllReadBooks() throws Exception {
        mockMvc.perform ( MockMvcRequestBuilders.get ( baseUrl + "/read" )
                .contentType ( MediaType.APPLICATION_JSON ))
                .andExpect ( MockMvcResultMatchers.status ().isOk () )
                .andExpect ( MockMvcResultMatchers.jsonPath ( "$.size()", is ( 2 ) ) );
    }

    @Test
    void shouldGetAllYetToBeReadBooks() throws Exception {
        mockMvc.perform ( MockMvcRequestBuilders.get ( baseUrl + "/yet-to-read" ) )
                .andExpect ( MockMvcResultMatchers.status ().isOk () )
                .andExpect ( MockMvcResultMatchers.jsonPath ( "$.size()", is ( 5 ) ) );
    }

    @Test
    void shouldGetAll_in_progressBook() throws Exception {
        mockMvc.perform ( MockMvcRequestBuilders.get ( baseUrl + "/in-progress" ) )
                .andExpect ( MockMvcResultMatchers.status ().isOk () )
                .andExpect ( MockMvcResultMatchers.jsonPath ( "$.size()", is ( 0 ) ) );
    }

    @Test
    void shouldDeleteBookByTitle() throws Exception {
        String bookTitle = "MADE IN AMERICA";

        mockMvc.perform ( MockMvcRequestBuilders.delete ( baseUrl + "/by-title/" + bookTitle ) )
                .andExpect ( MockMvcResultMatchers.status ().isOk () );

        mockMvc.perform ( MockMvcRequestBuilders.get ( baseUrl + bookTitle ))
                .andExpect ( MockMvcResultMatchers.status ().isNotFound () );
    }

    @Test
    void shouldDeleteBookByAuthor() throws Exception{
        String author = "CHINUA ACHEBE";

        mockMvc.perform ( MockMvcRequestBuilders.delete ( baseUrl + "/by-author/" + author ) )
                .andExpect ( MockMvcResultMatchers.status ().isOk () );

        mockMvc.perform ( MockMvcRequestBuilders.get ( baseUrl + author ))
                .andExpect ( MockMvcResultMatchers.status ().isNotFound () );
    }

    @Test
    void shouldReturnNotFoundIfAuthorDoesNotExist() throws  Exception {
        String author = "CHINUA ACHEBE";

        mockMvc.perform ( MockMvcRequestBuilders.get ( baseUrl + author ))
                .andExpect ( MockMvcResultMatchers.status ().isNotFound () );
    }

    @Test
    void shouldReturnNotFoundIfBookDoesNotExist() throws BookNotFoundException {
        String bookTitle = "Arrow Of God";
        try {
            mockMvc.perform ( MockMvcRequestBuilders.get ( baseUrl + "/by-title/" +bookTitle ))
                    .andExpect ( MockMvcResultMatchers.status ().isNotFound () );
        } catch (Exception e) {
        }

    }
}



