package pl.adrianix2000.Engboost.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class OperationRespone {
    private HttpStatus status;
    private String information;
    private Object obj;
}
