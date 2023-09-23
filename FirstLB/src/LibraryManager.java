import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class Book {
    private String title;
    private String author;
    private String isbn;
    private int year;

    public Book(String title, String author, String isbn, int year) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Year: " + year;
    }
}

class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void showAllBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public void removeBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                books.remove(book);
                break;
            }
        }
    }

    public Collection<Object> getAllBooks() {
        return null;
    }
}

public class LibraryManager {
    public static void main(String[] args) {
        Library library = new Library();

        // Додавання книг в бібліотеку
        library.addBook(new Book("Book1", "Author1", "1234567890", 2020));
        library.addBook(new Book("Book2", "Author2", "0987654321", 2019));
        library.addBook(new Book("Book3", "Author3", "5432109876", 2018));

        // Показ всіх книг у бібліотеці
        library.showAllBooks();

        // Пошук книги за назвою
        Book foundBook = library.findBookByTitle("Book2");
        if (foundBook != null) {
            System.out.println("Found Book: " + foundBook);
        } else {
            System.out.println("Book not found.");
        }

        // Видалення книги за ISBN
        library.removeBookByIsbn("1234567890");

        // Показ всіх книг після видалення
        library.showAllBooks();
    }
}
