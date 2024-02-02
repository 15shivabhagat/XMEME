package com.crio.starter.service;

import java.util.List;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchange.GetMemeRequest;
import com.crio.starter.exchange.GetMemesResponse;

public interface MemesService {
   GetMemeRequest createMeme(MemeEntity meme);
   MemeEntity getMeme(String id);
   Boolean isMemeAvailable(MemeEntity meme);
   List<GetMemesResponse> getMemes();
}
