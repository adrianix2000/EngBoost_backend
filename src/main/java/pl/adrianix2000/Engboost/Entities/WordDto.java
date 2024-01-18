package pl.adrianix2000.Engboost.Entities;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WordDto {
    private String englishmean;
    private String polishmean;
}
