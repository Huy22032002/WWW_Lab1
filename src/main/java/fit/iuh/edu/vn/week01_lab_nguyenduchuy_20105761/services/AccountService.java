package fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.services;

import fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.entities.Account;
import fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.repositories.AccountRepository;
import fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.repositories.RoleRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

public class AccountService {
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;

    public AccountService() {
        accountRepository = new AccountRepository();
        roleRepository = new RoleRepository();
    }

    public void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        System.out.println(id);
        System.out.println(password);

        Account account = accountRepository.login(id, password);

        if (account != null) {
            String role = roleRepository.getRoleAccount(account); //getRole
            HttpSession session1 = request.getSession();
            session1.setAttribute("account", account);
            session1.setAttribute("role", role);
            response.sendRedirect("dashboard.jsp");
        } else {
            System.out.println("Login Failed");
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }
}
