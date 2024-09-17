package fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.services;

import fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.entities.Account;
import fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.repositories.AccountRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountService() {
        accountRepository = new AccountRepository();
    }

    public void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        System.out.println(id);
        System.out.println(password);

        Account account = accountRepository.login(id, password);
        if (account != null) {
            response.sendRedirect("dashboard.jsp");
        } else {
            System.out.println("Login Failed");
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }
}
