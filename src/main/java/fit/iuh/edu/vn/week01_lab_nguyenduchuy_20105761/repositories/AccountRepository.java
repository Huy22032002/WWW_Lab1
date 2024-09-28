package fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.repositories;

import fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.entities.Account;

import java.sql.*;

public class AccountRepository {
    private static String url = "jdbc:mariadb://localhost:3306/mydb";
    static final String user = "root";
    static final String pass = "123";

    public Account login(String id, String password) {
        Account account = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, user, pass);
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM account " +
                         "inner join grant_access on account.account_id = grant_access.account_id " +
                         "inner join role on role.role_id = grant_access.role_id " +
                         "WHERE account.account_id = ? AND account.password = ? ")) {

                statement.setString(1, id);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    account = new Account();
                    account.setId(resultSet.getString("account_id"));
                    account.setFullName(resultSet.getString("full_name"));
                    account.setPassword(resultSet.getString("password"));
                    account.setEmail(resultSet.getString("email"));
                    account.setPhone(resultSet.getString("phone"));
                    account.setStatus(resultSet.getInt("status"));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }
    public boolean addAccount(String name, String pass, String email, String phone, String status) throws SQLException {
        String sql = "INSERT INTO account (name, pass, email, phone, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, pass);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, status);
            stmt.executeUpdate();
        }
        return true;
    }
    public boolean updateAccount(String id, String name, String pass, String email, String phone, String status) throws SQLException {
        String sql = "UPDATE account SET name = ?, pass = ?, email = ?, phone = ?, status = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, pass);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, status);
            stmt.setString(6, id);
            stmt.executeUpdate();
        }
        return true;
    }
    public boolean deleteAccount(String id) throws SQLException {
        String sql = "DELETE FROM account WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id); // Xóa theo ID
            stmt.executeUpdate();
        }
        return true;
    }

}

