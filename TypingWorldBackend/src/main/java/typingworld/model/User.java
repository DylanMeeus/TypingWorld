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

    public static final class UserBuilder{
        private String username;
        private String firstname;
        private String lastname;
        private String email;

        public UserBuilder username(String username){
            this.username = username;
            return this;
        }

        public UserBuilder firstname(String firstname){
            this.firstname = firstname;
            return this;
        }

        public UserBuilder lastname(String lastname){
            this.lastname = lastname;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public User build(){
            return new User(email, username, firstname, lastname);
        }
    }

}
