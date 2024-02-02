package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;
import com.crio.starter.data.MemeEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class GetMemesResponse {
    private String name;
    private String caption;
    @JsonProperty("url")
    private String urlMeme;
}
