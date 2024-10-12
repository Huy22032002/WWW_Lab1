package fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.services;

import fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.entities.Account;
import fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.repositories.AccountRepository;
import fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.repositories.RoleRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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

        Account account = accountRepository.login(id, password);

        if (account != null) {
            String role = roleRepository.getRoleAccount(account); //getRole
            HttpSession session1 = request.getSession();
            session1.setAttribute("account", account);
            session1.setAttribute("role", role);
            response.sendRedirect("dashboard.jsp");
        } else {
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }
    public void showAllAccounts(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
         ArrayList<Account> lstAcc = accountRepository.getAllAccount();
         HttpSession session = request.getSession();
         session.setAttribute("lstAcc", lstAcc);
    }
    public void addAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("accountID");
        String name = request.getParameter("fullName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        System.out.println("ID: " + id);
        System.out.println("Full Name: " + name);
        System.out.println("Password: " + password);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);

        boolean result = accountRepository.addAccount(id, name, password, email, phone);
        System.out.println(result);
        if(result) {
            response.getWriter().write("Success");
        } else {
            response.getWriter().write("Fail");
        }
    }
}
