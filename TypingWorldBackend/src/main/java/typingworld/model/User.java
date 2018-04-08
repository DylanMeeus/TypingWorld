package typingworld.model;

public class User {

    private final String email;
    private final String username;
    private final String firstname;
    private final String lastname;


    public User(final String email,
                final String userName,
                final String firstname,
                final String lastname) {
        this.username = userName;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
