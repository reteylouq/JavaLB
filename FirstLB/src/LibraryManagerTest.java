import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LibraryManagerTest {
    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
        library.addBook(new Book("Book1", "Author1", "1234567890", 2020));
        library.addBook(new Book("Book2", "Author2", "0987654321", 2019));
        library.addBook(new Book("Book3", "Author3", "5432109876", 2018));
    }

    @Test
    void testAddBook() {
        Book newBook = new Book("New Book", "New Author", "1111111111", 2022);
        library.addBook(newBook);
        assertEquals(4, library.getAllBooks().size());
    }

    @Test
    void testFindBookByTitle() {
        Book foundBook = library.findBookByTitle("Book2");
        assertNotNull(foundBook);
        assertEquals("Book2", foundBook.getTitle());
    }

    @Test
    void testRemoveBookByIsbn() {
        library.removeBookByIsbn("1234567890");
        assertNull(library.findBookByTitle("Book1"));
    }
}
