package pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pojo_RegisterCustomer {

    private String first_name;
    private String last_name;
    private String username;
    private String email;
    private String password;
    private String password_confirmation;
    private String user_type;
    private String phone;
    private String referral_code;


}
