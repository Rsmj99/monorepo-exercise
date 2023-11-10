package com.exercise.msclientperson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.msclientperson.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
