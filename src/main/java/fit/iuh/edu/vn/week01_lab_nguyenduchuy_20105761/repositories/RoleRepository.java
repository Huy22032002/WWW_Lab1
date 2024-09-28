package fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.repositories;

import fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.entities.Account;
import fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.entities.GrantAccess;
import fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.entities.Role;

import java.sql.*;

public class RoleRepository {
    private static String url = "jdbc:mariadb://localhost:3306/mydb";
    static final String user = "root";
    static final String pass = "123";

    public String getRoleAccount(Account account) throws SQLException {
        String sql = "select g.role_id from grant_access g " +
                "inner join account a on g.account_id = a.account_id" +
                " where a.account_id = ?";
        String role = "";
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, account.getId());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    role = resultSet.getString("role_id");
                    System.out.println(role);
                }
            }
        }
        return role;
    }
}
