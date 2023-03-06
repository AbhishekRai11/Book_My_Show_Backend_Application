package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {
}
