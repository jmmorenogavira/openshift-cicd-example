package com.jmmoreno.openshift.cicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmmoreno.openshift.cicd.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
