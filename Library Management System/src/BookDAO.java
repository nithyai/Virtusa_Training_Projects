import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {

    Connection con = DBConnection.getConnection();

    public void addBook(int id, String title, String author) {
        try {
            String q = "INSERT INTO books(book_id, title, author, is_issued) VALUES (?, ?, ?, false)";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, id);
            ps.setString(2, title);
            ps.setString(3, author);
            ps.executeUpdate();
            System.out.println("Book added");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void viewBooks() {
        try {
            String q = "SELECT * FROM books";
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getBoolean(4));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void removeBook(int id) {
        try {
            String q = "DELETE FROM books WHERE book_id=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Book removed");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateBook(int id, String title, String author) {
        try {
            String q = "UPDATE books SET title=?, author=? WHERE book_id=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, id);
            ps.executeUpdate();
            System.out.println("Book updated");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void searchBook(String key) {
        try {
            String q = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}