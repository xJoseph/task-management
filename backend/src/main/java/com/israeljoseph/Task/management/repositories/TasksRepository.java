package com.israeljoseph.Task.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.israeljoseph.Task.management.models.Tasks;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
    
}
