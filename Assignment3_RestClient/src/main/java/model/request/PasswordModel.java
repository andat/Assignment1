package model.request;

public class PasswordModel {
    private String password;

    public PasswordModel(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
