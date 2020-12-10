package by.tms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class PostCreateDto {
    @NotEmpty(message = "Title is empty!")
    @NotBlank(message = "Title is empty!")
    private String title;
    @NotEmpty(message = "Title is empty!")
    @NotBlank(message = "Title is empty!")
    private String massage;

}
