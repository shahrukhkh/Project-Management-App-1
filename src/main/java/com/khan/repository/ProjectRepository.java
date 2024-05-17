package com.khan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khan.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
