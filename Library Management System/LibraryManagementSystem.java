import java.util.*;

class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }
}

class User {
    int id;
    String name;

    User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Transaction {
    int bookId;
    int userId;
    int issueDay;
    int returnDay;

    Transaction(int bookId, int userId, int issueDay) {
        this.bookId = bookId;
        this.userId = userId;
        this.issueDay = issueDay;
        this.returnDay = -1;
    }
}

public class LibraryManagementSystem {

    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Transaction> transactions = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Add Book\n2.Remove Book\n3.Update Book\n4.Register User\n5.View Users\n6.Issue Book\n7.Return Book\n8.Search Book\n9.View Books\n10.Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: addBook(); break;
                case 2: removeBook(); break;
                case 3: updateBook(); break;
                case 4: addUser(); break;
                case 5: viewUsers(); break;
                case 6: issueBook(); break;
                case 7: returnBook(); break;
                case 8: searchBook(); break;
                case 9: viewBooks(); break;
                case 10: System.exit(0);
                default: System.out.println("Invalid choice");
            }
        }
    }

    static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Book added");
    }

    static void removeBook() {
        System.out.print("Enter Book ID to remove: ");
        int id = sc.nextInt();

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).id == id) {
                books.remove(i);
                System.out.println("Book removed");
                return;
            }
        }
        System.out.println("Book not found");
    }

    static void updateBook() {
        System.out.print("Enter Book ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Book b : books) {
            if (b.id == id) {
                System.out.print("Enter new title: ");
                b.title = sc.nextLine();
                System.out.print("Enter new author: ");
                b.author = sc.nextLine();
                System.out.println("Book updated");
                return;
            }
        }
        System.out.println("Book not found");
    }

    static void addUser() {
        System.out.print("Enter User ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        users.add(new User(id, name));
        System.out.println("User registered: " + name);
    }

    static void viewUsers() {
        for (User u : users) {
            System.out.println(u.id + " " + u.name);
        }
    }

    static void issueBook() {
        System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();
        System.out.print("Enter User ID: ");
        int userId = sc.nextInt();
        System.out.print("Enter Issue Day: ");
        int day = sc.nextInt();

        for (Book b : books) {
            if (b.id == bookId && !b.isIssued) {
                b.isIssued = true;
                transactions.add(new Transaction(bookId, userId, day));
                System.out.println("Book issued");
                return;
            }
        }
        System.out.println("Book not available");
    }

    static void returnBook() {
        System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();
        System.out.print("Enter Return Day: ");
        int returnDay = sc.nextInt();

        for (Transaction t : transactions) {
            if (t.bookId == bookId && t.returnDay == -1) {
                t.returnDay = returnDay;

                for (Book b : books) {
                    if (b.id == bookId) {
                        b.isIssued = false;
                    }
                }

                int fine = 0;
                int diff = returnDay - t.issueDay;
                if (diff > 7) {
                    fine = (diff - 7) * 10;
                }

                System.out.println("Book returned by User ID: " + t.userId);
                System.out.println("Fine: " + fine);
                return;
            }
        }
        System.out.println("Transaction not found");
    }

    static void searchBook() {
        sc.nextLine();
        System.out.print("Enter title or author: ");
        String key = sc.nextLine().toLowerCase();

        for (Book b : books) {
            if (b.title.toLowerCase().contains(key) || b.author.toLowerCase().contains(key)) {
                System.out.println(b.id + " " + b.title + " " + b.author + " Issued: " + b.isIssued);
            }
        }
    }

    static void viewBooks() {
        for (Book b : books) {
            System.out.println(b.id + " " + b.title + " " + b.author + " Issued: " + b.isIssued);
        }
    }
}