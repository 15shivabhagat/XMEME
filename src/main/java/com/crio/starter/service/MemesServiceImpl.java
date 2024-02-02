package com.crio.starter.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchange.GetMemeRequest;
import com.crio.starter.exchange.GetMemesResponse;
import com.crio.starter.repository.MemesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemesServiceImpl implements MemesService {

    @Autowired
    private MemesRepository memesRepository;

    @Override
    public GetMemeRequest createMeme(MemeEntity meme) {
        LocalTime now = LocalTime.now();
        meme.setCreatedAt(now);
        MemeEntity newMeme = memesRepository.save(meme);
        GetMemeRequest response = new GetMemeRequest(newMeme.getId());
        return response;
    }

    @Override
    public MemeEntity getMeme(String id) {
        MemeEntity meme = memesRepository.findById(id).orElse(null);
        return meme;
    }

    @Override
    public Boolean isMemeAvailable(MemeEntity meme) {
        if(!memesRepository.findByNameAndCaptionAndUrlMeme(meme.getName(), meme.getCaption(), meme.getUrlMeme()).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<GetMemesResponse> getMemes() {
        List<MemeEntity> memes = memesRepository.findAll()
                                    .stream()
                                    .sorted(Comparator.comparing(MemeEntity::getId).reversed())
                                    .limit(100)
                                    .collect(Collectors.toList());
        
        List<GetMemesResponse> memeResponses = new ArrayList<>();

        for(MemeEntity meme : memes) {
            GetMemesResponse newMeme = new GetMemesResponse();
            newMeme.setName(meme.getName());
            newMeme.setUrlMeme(meme.getUrlMeme());
            newMeme.setCaption(meme.getCaption());

            memeResponses.add(newMeme);
        }
        return memeResponses;
    }

    
    
    
}
