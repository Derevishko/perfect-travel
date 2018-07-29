package travel.avg.travel.entities;

public class Token {
    String access_token;
    //id
    String username;

    public Token() {
    }

    public Token(String access_token, String username) {
        this.access_token = access_token;
        this.username = username;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getUsername() {
        return username;
    }
}
