import java.util.*;

class Book {
    private String bookId;
    private String title;
    private boolean isIssued;

    public Book(String bookId, String title) {
        this.bookId = bookId;
        this.title = title;
        this.isIssued = false;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issue() {
        isIssued = true;
    }

    public void returned() {
        isIssued = false;
    }

    public void displayBook() {
        System.out.println("ID: " + bookId + " | Title: " + title + " | Status: " + (isIssued ? "Issued" : "Available"));
    }
}

class User {
    private String userId;
    private String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}

class Library {
    private Map<String, Book> books = new HashMap<>();
    private Map<String, User> users = new HashMap<>();
    private Map<String, String> issuedBooks = new HashMap<>(); // bookId -> userId

    public void addBook(Book book) {
        books.put(book.getBookId(), book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void addUser(User user) {
        users.put(user.getUserId(), user);
        System.out.println("User added: " + user.getName());
    }

    public void issueBook(String bookId, String userId) {
        if (!books.containsKey(bookId)) {
            System.out.println("Book not found.");
            return;
        }
        if (!users.containsKey(userId)) {
            System.out.println("User not found.");
            return;
        }

        Book book = books.get(bookId);
        if (book.isIssued()) {
            System.out.println("Book already issued.");
        } else {
            book.issue();
            issuedBooks.put(bookId, userId);
            System.out.println("Book issued to " + users.get(userId).getName());
        }
    }

    public void returnBook(String bookId) {
        if (!books.containsKey(bookId)) {
            System.out.println("Book not found.");
            return;
        }

        Book book = books.get(bookId);
        if (!book.isIssued()) {
            System.out.println("Book was not issued.");
        } else {
            book.returned();
            issuedBooks.remove(bookId);
            System.out.println("Book returned successfully.");
        }
    }

    public void viewBooks() {
        System.out.println("\nAvailable Books:");
        for (Book book : books.values()) {
            book.displayBook();
        }
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library lib = new Library();

        // Sample Data
        lib.addBook(new Book("B001", "Java Programming"));
        lib.addBook(new Book("B002", "Python Basics"));
        lib.addBook(new Book("B003", "React Basics"));
        lib.addBook(new Book("B004", "DBMS"));
        lib.addUser(new User("U001", "Charan"));
        lib.addUser(new User("U002", "Lahari"));
        lib.addUser(new User("U003", "Pandu"));
        lib.addUser(new User("U004", "Chinnu"));

        while (true) {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. View Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    lib.viewBooks();
                    break;
                case 2:
                    System.out.print("Enter Book ID: ");
                    String issueBookId = scanner.nextLine();
                    System.out.print("Enter User ID: ");
                    String userId = scanner.nextLine();
                    lib.issueBook(issueBookId, userId);
                    break;
                case 3:
                    System.out.print("Enter Book ID to return: ");
                    String returnBookId = scanner.nextLine();
                    lib.returnBook(returnBookId);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
