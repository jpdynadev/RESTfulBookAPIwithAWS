package com.example.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ModelImp.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String>{

}
