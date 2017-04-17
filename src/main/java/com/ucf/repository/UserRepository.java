package com.ucf.repository;

import com.ucf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/*
* Repositories provide a means for services to communicate with the database.
*
* The User repository provides a means to lookup a user object by various parameters.
* */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select u from User u where u.email=?1")
    User getUserByUsername(String username);

    @Query(value = "select u from User u where u.phone=?1")
    User getUserByPhone(String phone);
}

