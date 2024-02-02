package com.crio.starter.repository;

import java.util.List;
import com.crio.starter.data.MemeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MemesRepository extends MongoRepository<MemeEntity, String> {
    
    @Query("SELECT e FROM MemeEntity e WHERE e.createdAt <= CURRENT_TIMESTAMP ORDER BY e.createdAt DESC LIMIT 100")
    List<MemeEntity> findTopMemes();

    List<MemeEntity> findByNameAndCaptionAndUrlMeme(String name, String caption, String urlMeme);
}
