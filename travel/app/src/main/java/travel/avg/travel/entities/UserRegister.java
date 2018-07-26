package travel.avg.travel.entities;

import com.google.gson.annotations.SerializedName;

public class UserRegister {
    @SerializedName("email")
    private String email_register;

    @SerializedName("name")
    private String name;

    @SerializedName("pass")
    private String password_register;

    public UserRegister(String email_register, String name, String password_register) {
        this.email_register = email_register;
        this.name = name;
        this.password_register = password_register;
    }
}
