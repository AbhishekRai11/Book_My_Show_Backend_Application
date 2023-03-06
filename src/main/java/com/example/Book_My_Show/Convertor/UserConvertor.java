package com.example.Book_My_Show.Convertor;

import com.example.Book_My_Show.Entities.UserEntity;
import com.example.Book_My_Show.EntryDto.UserEntryDto;

public class UserConvertor {

    public static UserEntity convertDtoToEntity(UserEntryDto userEntryDto){
        UserEntity userEntity= UserEntity.builder()
                .age(userEntryDto.getAge())
                .name(userEntryDto.getName())
                .address(userEntryDto.getAddress())
                .email(userEntryDto.getEmail())
                .mobNo(userEntryDto.getMobNo()).build();

        return userEntity;
    }
}
