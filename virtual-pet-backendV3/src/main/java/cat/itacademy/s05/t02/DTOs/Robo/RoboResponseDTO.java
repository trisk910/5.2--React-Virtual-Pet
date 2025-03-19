package cat.itacademy.s05.t02.DTOs.Robo;

import cat.itacademy.s05.t02.Models.Enums.RoboType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoboResponseDTO {
    private Long id;
    private String name;
    private RoboType type;
    private Long userId;
    private int health;
    private int attack;
    private int defense;
    private int speed;
    private int level;
}