package travel.avg.travel.entities;

import com.google.gson.annotations.SerializedName;

public class UserRegister {
    @SerializedName("Name")
    private String name;

    @SerializedName("Email")
    private String email;

    @SerializedName("Password")
    private String password;

    public UserRegister(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
