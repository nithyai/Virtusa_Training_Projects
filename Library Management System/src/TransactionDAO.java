import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO {

    Connection con = DBConnection.getConnection();

    public void issueBook(int bookId, int userId) {
        try {
            String check = "SELECT is_issued FROM books WHERE book_id=?";
            PreparedStatement ps1 = con.prepareStatement(check);
            ps1.setInt(1, bookId);
            ResultSet rs = ps1.executeQuery();

            if (rs.next() && rs.getBoolean(1)) {
                System.out.println("Book already issued");
                return;
            }

            String update = "UPDATE books SET is_issued=true WHERE book_id=?";
            PreparedStatement ps2 = con.prepareStatement(update);
            ps2.setInt(1, bookId);
            ps2.executeUpdate();

            String insert = "INSERT INTO transactions(book_id, user_id, issue_date) VALUES (?, ?, CURDATE())";
            PreparedStatement ps3 = con.prepareStatement(insert);
            ps3.setInt(1, bookId);
            ps3.setInt(2, userId);
            ps3.executeUpdate();

            System.out.println("Book issued");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void returnBook(int bookId) {
        try {
            String query = "SELECT issue_date FROM transactions WHERE book_id=? AND return_date IS NULL";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("Invalid return");
                return;
            }

            java.sql.Date issueDate = rs.getDate(1);

            String update1 = "UPDATE transactions SET return_date=CURDATE() WHERE book_id=? AND return_date IS NULL";
            PreparedStatement ps1 = con.prepareStatement(update1);
            ps1.setInt(1, bookId);
            ps1.executeUpdate();

            String update2 = "UPDATE books SET is_issued=false WHERE book_id=?";
            PreparedStatement ps2 = con.prepareStatement(update2);
            ps2.setInt(1, bookId);
            ps2.executeUpdate();

            long days = (System.currentTimeMillis() - issueDate.getTime()) / (1000 * 60 * 60 * 24);

            int fine = 0;
            if (days > 7) {
                fine = (int)(days - 7) * 10;
            }

            System.out.println("Returned. Fine = " + fine);

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
}