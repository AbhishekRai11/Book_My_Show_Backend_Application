package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Convertor.UserConvertor;
import com.example.Book_My_Show.Entities.UserEntity;
import com.example.Book_My_Show.EntryDto.UserEntryDto;
import com.example.Book_My_Show.Repository.UserRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDto userEntryDto) throws Exception{
        /*
        here we need to convert
        old method: create object and save its value
        but here we use builder annotation
        this set all attribute in one go

         */
        UserEntity userEntity= UserConvertor.convertDtoToEntity(userEntryDto);

        userRepository.save(userEntity);
        return "User Added Successfully";
    }
}
