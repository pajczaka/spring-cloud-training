package pl.training.cloud.users.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "User")
@Data
public class UserDto {

    private String firstName;
    private String lastName;

}
