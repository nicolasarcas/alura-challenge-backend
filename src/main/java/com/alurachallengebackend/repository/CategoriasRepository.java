package com.alurachallengebackend.repository;

import com.alurachallengebackend.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriasRepository extends JpaRepository<Long, Categoria> {
}
