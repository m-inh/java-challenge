package jp.co.axa.apidemo.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GetEmployeeResponse {
    @Schema(title = "Employee ID", example = "1")
    private String id;

    @Schema(title = "Employee Name", example = "John Doe")
    private String name;

    @Schema(title = "Employee Salary", example = "120000")
    private Integer salary;

    @Schema(title = "Employee Department", example = "Accounting")
    private String department;
}
