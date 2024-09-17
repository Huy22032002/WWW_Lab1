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
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE account_id = ? AND password = ? ")) {

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

}

