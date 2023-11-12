import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    @Test
    void testLendItem() {
        Library library = new Library();
        Book book = new Book("The Hobbit", "123", "J.R.R. Tolkien");
        Patron patron = new Patron("John Doe", "001");

        library.add(book);
        library.registerPatron(patron);

        library.lendItem(patron, book);

        assertTrue(book.isBorrowed());
        assertTrue(patron.getBorrowedItems().contains(book));
    }

    @Test
    void testReturnItem() {
        Library library = new Library();
        Book book = new Book("The Hobbit", "123", "J.R.R. Tolkien");
        Patron patron = new Patron("John Doe", "001");

        library.add(book);
        library.registerPatron(patron);

        library.lendItem(patron, book);
        library.returnItem(patron, book);

        assertFalse(book.isBorrowed());
        assertFalse(patron.getBorrowedItems().contains(book));
    }

    @Test
    void testListAvailable() {
        Library library = new Library();
        Book book1 = new Book("The Hobbit", "123", "J.R.R. Tolkien");
        Book book2 = new Book("1984", "456", "George Orwell");

        library.add(book1);
        library.add(book2);

        library.listAvailable();
    }

    @Test
    void testListBorrowed() {
        Library library = new Library();
        Book book1 = new Book("The Hobbit", "123", "J.R.R. Tolkien");
        Patron patron = new Patron("John Doe", "001");

        library.add(book1);
        library.registerPatron(patron);

        library.lendItem(patron, book1);
        library.listBorrowed();
    }
}
