package tech.abdel_hamid.stoneagesocialbackend.repository;

import java.util.List;
import java.util.Optional;

import tech.abdel_hamid.stoneagesocialbackend.entity.PostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<PostEntity, String> {
    Optional<List<PostEntity>> findByUserId(String id);
    Optional<List<PostEntity>> findByUserIdOrderByCreatedAtDesc(String id);
    
}
