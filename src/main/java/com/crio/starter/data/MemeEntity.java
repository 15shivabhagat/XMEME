package com.crio.starter.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "memes")
@NoArgsConstructor
public class MemeEntity {
    @Id
    private String id;

    private String name;
    
    private String caption;
    
    @JsonProperty("url")
    private String urlMeme;

    private LocalTime createdAt;
    
}
