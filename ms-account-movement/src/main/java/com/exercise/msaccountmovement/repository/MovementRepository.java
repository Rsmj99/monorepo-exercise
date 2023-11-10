package com.exercise.msaccountmovement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.msaccountmovement.model.Movement;

public interface MovementRepository extends JpaRepository<Movement, Long> {

}
