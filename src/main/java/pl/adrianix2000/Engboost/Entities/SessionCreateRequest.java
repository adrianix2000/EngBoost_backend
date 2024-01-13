package pl.adrianix2000.Engboost.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionCreateRequest {
    private String title;
    private String username;
    private boolean isshared;
}
