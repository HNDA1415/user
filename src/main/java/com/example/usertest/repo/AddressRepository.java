package com.example.usertest.repo;

import com.example.usertest.entity.Addresses;
import com.example.usertest.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Addresses,Integer> {
    Iterable<Addresses> findAllByUser(UserEntity userEntity);


}
