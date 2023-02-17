package com.example.usertest.repo;


import com.example.usertest.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    @Query(value = "SELECT u FROM  UserEntity  u WHERE  u.id=:id")
    UserEntity findByUserId(Integer id);

    Optional<UserEntity> findByMobileNumber(String mobileNumber);
}
