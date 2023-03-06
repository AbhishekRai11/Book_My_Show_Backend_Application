package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Entities.UserEntity;
import com.example.Book_My_Show.EntryDto.UserEntryDto;
import com.example.Book_My_Show.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
@RequestMapping("/users")
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

}
