package es.upm.miw.apaw_ep_themes.documents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TreeLibraryCompositeTest {

    private TreeLibrary libraryRoot;
    private TreeLibrary libraryNode;
    private TreeLibrary library;

    @BeforeEach
    void beforeEach() {
        libraryRoot = new TreeLibraryComposite("Library Root");
        libraryNode = new TreeLibraryComposite("LibraryNode");
        library = new Library("Library");
        libraryRoot.add(libraryNode);
        libraryNode.add(library);
    }

    @Test
    void testAdd() {
        TreeLibrary node1 = new Library("Library1");
        TreeLibrary node2 = new Library("Library2");
        TreeLibrary node3 = new Library("Library3");
        TreeLibrary node4 = new Library("Library4");
        TreeLibrary node5 = new Library("Library4");
        TreeLibrary node6 = new Library("Library6");
        TreeLibrary node7 = new Library("Library7");
        TreeLibrary node8 = new Library("Library8");
        TreeLibrary node9 = new Library("Library9");
        TreeLibrary node10 = new Library("Library10");
        TreeLibrary node11 = new Library("Library11");
        libraryRoot.add(node1);
        assertEquals(2, libraryRoot.size());
        libraryRoot.add(node2);
        libraryRoot.add(node3);
        libraryRoot.add(node4);
        libraryRoot.add(node5);
        libraryRoot.add(node6);
        assertEquals(7, libraryRoot.size());
        libraryNode.add(node7);
        assertEquals(2, libraryNode.size());
        libraryNode.add(node8);
        libraryNode.add(node9);
        libraryNode.add(node10);
        assertEquals(5, libraryNode.size());
        libraryNode.add(node11);
        assertEquals(6, libraryNode.size());
    }

    @Test
    void testIsComposite() {
        assertTrue(libraryRoot.isComposite());
        assertTrue(libraryNode.isComposite());
        assertFalse(library.isComposite());
    }

    @Test
    void testRemove() {
        TreeLibrary node1 = new Library("Library1");
        TreeLibrary node2 = new Library("Library2");
        TreeLibrary node3 = new Library("Library3");
        TreeLibrary node4 = new Library("Library4");
        TreeLibrary node5 = new Library("Library4");
        TreeLibrary node6 = new Library("Library6");
        libraryRoot.add(node1);
        libraryRoot.add(node2);
        libraryRoot.add(node3);
        libraryRoot.add(node4);
        assertEquals(5, libraryRoot.size());
        libraryRoot.remove(node2);
        assertEquals(4, libraryRoot.size());
        libraryRoot.remove(node1);
        libraryRoot.remove(node3);
        assertEquals(2, libraryRoot.size());
        libraryNode.add(node5);
        libraryNode.add(node6);
        assertEquals(3, libraryNode.size());
        libraryNode.remove(node6);
        libraryNode.remove(node5);
        assertEquals(1, libraryNode.size());
        libraryNode.remove(library);
        assertEquals(0, libraryNode.size());
        libraryRoot.remove(libraryNode);
        assertEquals(1, libraryRoot.size());
        libraryRoot.remove(node4);
        assertEquals(0, libraryRoot.size());
    }
}
