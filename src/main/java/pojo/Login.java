package pojo;

public class Login {
    private String userEmail;
    private String userPassword;

    // Getters
    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    // Setters
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    // Builder method
    public static Login LoginBodyBuilder(String userEmail, String userPassword) {
        Login login = new Login();
        login.setUserEmail(userEmail);
        login.setUserPassword(userPassword);
        return login;
    }
}
