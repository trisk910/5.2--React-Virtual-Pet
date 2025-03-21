package cat.itacademy.s05.t02.DTOs.User;

import cat.itacademy.s05.t02.Models.Enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    private String name;
    private String username;
    private String email;
    private String password;
    private RoleType roleType = RoleType.USER;
    private int currency = 100;
    private String profileImage;
}