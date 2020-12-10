package by.tms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class UserAuthDto {
    @NotEmpty(message = "Login is empty!")
    @NotBlank(message = "Login is empty!")
    private String login;
    @NotEmpty(message = "Password is empty!")
    @NotBlank(message = "Password is empty!")
    private String password;

}
