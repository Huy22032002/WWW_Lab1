package fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.controller;

import fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.services.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ControlServlet", value = "/control")
public class ControlServlet extends HttpServlet {

    private AccountService accountService;

    @Override
    public void init() throws ServletException {
        accountService = new AccountService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "login":
                try {
                    accountService.handleLogin(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }

    }
}
