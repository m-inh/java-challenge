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
public class CreateEmployeeRequest {
    @Schema(title = "Employee Name", required = true, example = "John Doe")
    private String name;

    @Schema(title = "Employee Salary", required = true, example = "120000")
    private Integer salary;

    @Schema(title = "Employee Department", required = true, example = "Accounting")
    private String department;
}
