package tech.abdel_hamid.stoneagesocialbackend.repository;

import java.util.Optional;

import tech.abdel_hamid.stoneagesocialbackend.entity.UserEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);
    
}
