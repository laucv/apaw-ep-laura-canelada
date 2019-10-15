package es.upm.miw.apaw_ep_themes.documents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TreeLibraryCompositeTest {

    private TreeLibrary libraryRoot;
    private TreeLibrary libraryNode;
    private TreeLibrary library;

    @BeforeEach
    void beforeEach() {
        libraryRoot = new TreeLibraryComposite("library1", "Library Root");
        libraryNode = new TreeLibraryComposite("library2", "LibraryNode");
        library = new Library("Library");
        libraryRoot.add(libraryNode);
        libraryNode.add(library);
    }

    @Test
    void testIsComposite(){
        assertTrue(libraryRoot.isComposite());
        assertTrue(libraryNode.isComposite());
        assertFalse(library.isComposite());
    }

    @Test
    void testAdd(){
        TreeLibrary node = new Library("Library2");
        libraryRoot.add(node);
        assertEquals(2, libraryRoot.size());
    }

    @Test
    void testRemove(){
        libraryNode.remove(library);
        assertEquals(0, libraryNode.size());
    }
}
