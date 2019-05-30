package br.unisul.pweb.quarta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.unisul.pweb.quarta.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
	@Query("SELECT categoria FROM Categoria categoria WHERE categoria.nome LIKE %:nome%")
	List<Categoria> findLikeNome(String nome);
	
}
