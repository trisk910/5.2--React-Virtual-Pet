package cat.itacademy.s05.t02.DTOs.Fight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFightDTO {
    private Long robo1Id;
    private Long robo2Id;
}