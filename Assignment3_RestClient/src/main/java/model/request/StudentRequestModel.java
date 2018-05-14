package model.request;

public class StudentRequestModel {
    private String username;

    private String fullName;

    private String email;

    private String groupName;

    private String hobby;

    public StudentRequestModel(String username, String fullName, String email, String groupName, String hobby) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.groupName = groupName;
        this.hobby = hobby;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
