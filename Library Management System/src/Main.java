import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookDAO bookDAO = new BookDAO();
        UserDAO userDAO = new UserDAO();
        TransactionDAO transDAO = new TransactionDAO();

        while (true) {

            System.out.println("\n===== LIBRARY SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Remove Book");
            System.out.println("4. Update Book");
            System.out.println("5. Search Book");
            System.out.println("6. Add User");
            System.out.println("7. View Users");
            System.out.println("8. Issue Book");
            System.out.println("9. Return Book");
            System.out.println("10. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Title: ");
                    String title = sc.nextLine();

                    System.out.print("Author: ");
                    String author = sc.nextLine();

                    bookDAO.addBook(id, title, author);
                    break;

                case 2:
                    bookDAO.viewBooks();
                    break;

                case 3:
                    System.out.print("Book ID: ");
                    int rid = sc.nextInt();
                    bookDAO.removeBook(rid);
                    break;

                case 4:
                    System.out.print("Book ID: ");
                    int ubid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Title: ");
                    String utitle = sc.nextLine();

                    System.out.print("Author: ");
                    String uauthor = sc.nextLine();

                    bookDAO.updateBook(ubid, utitle, uauthor);
                    break;

                case 5:
                    sc.nextLine();
                    System.out.print("Search keyword: ");
                    String key = sc.nextLine();
                    bookDAO.searchBook(key);
                    break;

                case 6:
                    System.out.print("User ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    userDAO.addUser(uid, name);
                    break;

                case 7:
                    userDAO.viewUsers();
                    break;

                case 8:
                    System.out.print("Book ID: ");
                    int bid = sc.nextInt();

                    System.out.print("User ID: ");
                    int uid2 = sc.nextInt();

                    transDAO.issueBook(bid, uid2);
                    break;

                case 9:
                    System.out.print("Book ID: ");
                    int rb = sc.nextInt();

                    transDAO.returnBook(rb);
                    break;

                case 10:
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}