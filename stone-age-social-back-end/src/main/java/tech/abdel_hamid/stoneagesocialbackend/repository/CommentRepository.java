package tech.abdel_hamid.stoneagesocialbackend.repository;

import tech.abdel_hamid.stoneagesocialbackend.entity.CommentEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<CommentEntity, String> {
    
}