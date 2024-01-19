package pl.adrianix2000.Engboost.Mappers;

import org.springframework.stereotype.Component;
import pl.adrianix2000.Engboost.Entities.Word;
import pl.adrianix2000.Engboost.Entities.WordDto;
import pl.adrianix2000.Engboost.Entities.WordDtoWithId;

@Component
public class WordMapper {

    public static WordDto map(Word word) {
        return WordDto.builder()
                .englishmean(word.getEnglishmean())
                .polishmean(word.getPolishmean())
                .build();
    }

    public static WordDtoWithId mapWithId(Word word) {
        return WordDtoWithId.builder()
                .id(word.getId())
                .englishmean(word.getEnglishmean())
                .polishmean(word.getPolishmean())
                .build();
    }
}
