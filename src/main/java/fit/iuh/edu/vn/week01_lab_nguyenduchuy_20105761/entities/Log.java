package fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.entities;

import java.util.Date;

public class Log {
    private int id;
    private Account account;
    private Date loginTime;
    private Date logoutTime;
    private String notes;

    public Log(){

    }
    public Log(int id, Account account, Date loginTime, Date logoutTime, String notes) {
        this.id = id;
        this.account = account;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", account=" + account +
                ", loginTime=" + loginTime +
                ", logoutTime=" + logoutTime +
                ", notes='" + notes + '\'' +
                '}';
    }
}
