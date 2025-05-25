public class User {
    private int id;
    private String name;
    private String emailId;

    public User(String emailId, int id, String name) {
        this.emailId = emailId;
        this.id = id;
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "emailId='" + emailId + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
