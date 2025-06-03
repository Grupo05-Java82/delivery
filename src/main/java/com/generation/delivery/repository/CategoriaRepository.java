package com.generation.delivery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.delivery.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	List<Optional<Categoria>> findAllByPalavraChaveContainingIgnoreCase(String palavraChave);
}
