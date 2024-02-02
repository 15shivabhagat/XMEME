package com.crio.starter.controller;

import java.util.List;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchange.GetMemeRequest;
import com.crio.starter.exchange.GetMemesResponse;
import com.crio.starter.service.MemesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemesController {
    @Autowired
    private MemesService memeService;

    @PostMapping("/memes")
    public ResponseEntity<GetMemeRequest> createMeme(@RequestBody MemeEntity meme){
        System.out.println(meme.getName() + " " +  meme.getCaption());
        if(meme == null || meme.getName() == null || meme.getCaption() == null || meme.getUrlMeme() == null) {
            return ResponseEntity.status(400).build();
        } else if(memeService.isMemeAvailable(meme)) {
            return ResponseEntity.status(409).build();
        } else {
            GetMemeRequest newMemeId = memeService.createMeme(meme);
            return ResponseEntity.ok().body(newMemeId);
        }  
    }

    @GetMapping("/memes")
    public ResponseEntity<List<GetMemesResponse>> getTopMemes() {
        List<GetMemesResponse> memes = memeService.getMemes();
        return ResponseEntity.ok().body(memes);
    }

    @GetMapping("/memes/{id}")
    public ResponseEntity<GetMemesResponse> getMemeById(@PathVariable String id) {
        MemeEntity  meme = memeService.getMeme(id);
        if(meme != null) {
            GetMemesResponse getMeme = new GetMemesResponse(meme.getName(), meme.getCaption(),  meme.getUrlMeme());
            return ResponseEntity.ok().body(getMeme);
        } else {
            return ResponseEntity.status(404).build();
        }
    }


}
