package es.upm.miw.apaw_ep_themes.documents;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookBuilderTest {

    @Test
    void testBookBuilder(){
        Library library = new Library("Biblioteca");
        assertEquals("Biblioteca", library.getName());
        Book book = new BookBuilder("Si no despierto", "Lauren Oliver", library).build();
        assertEquals("Si no despierto", book.getTitle());
        assertEquals("Lauren Oliver", book.getAuthor());
        assertEquals("Biblioteca", book.getLibrary().getName());
    }
}
