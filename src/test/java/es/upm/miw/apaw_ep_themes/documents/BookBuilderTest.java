package es.upm.miw.apaw_ep_themes.documents;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookBuilderTest {

    @Test
    void testBookBuilder(){
        Library library = new Library("Biblioteca");
        assertEquals("Biblioteca", library.getName());
        Book book1 = new BookBuilder("Si no despierto", "Lauren Oliver", library).build();
        assertEquals("Si no despierto", book1.getTitle());
        assertEquals("Lauren Oliver", book1.getAuthor());
        assertEquals("Biblioteca", book1.getLibrary().getName());
        Book book2 = new BookBuilder("Clementine", "Clara Cortés", library).build();
        assertEquals("Clementine", book2.getTitle());
        assertEquals("Clara Cortés", book2.getAuthor());
        assertEquals("Biblioteca", book2.getLibrary().getName());
    }
}
