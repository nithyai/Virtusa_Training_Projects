import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    Connection con = DBConnection.getConnection();

    public void addUser(int id, String name) {
        try {
            String q = "INSERT INTO users VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
            System.out.println("User added");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void viewUsers() {
        try {
            String q = "SELECT * FROM users";
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " | " + rs.getString(2));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void removeUser(int id) {
        try {
            String q = "DELETE FROM users WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("User removed");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}