package pl.adrianix2000.Engboost.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionDto {
    private long id;
    private String title;
    private Date createdate;
    private boolean isshared;
    private long viewnumber;
    private String username;
}
