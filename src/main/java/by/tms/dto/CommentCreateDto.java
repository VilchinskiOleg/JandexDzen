package by.tms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CommentCreateDto {
    @NotEmpty(message = "Comment is empty!")
    @NotBlank(message = "Comment is empty!")
    private String massage;

}
