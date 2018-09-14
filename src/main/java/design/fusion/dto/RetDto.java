package design.fusion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetDto {
    private Boolean success;
    private String message;
    private String url;
}
