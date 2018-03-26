package business.model;

public class UserModel {
    private int id;
    private String password;
    private String username;
    private boolean is_admin;

    public UserModel(int id, String password, String username, boolean is_admin) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.is_admin = is_admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }
}
