package br.unisul.pweb.quarta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.pweb.quarta.domain.Categoria;
import br.unisul.pweb.quarta.domain.Cidade;
import br.unisul.pweb.quarta.domain.Produto;
import br.unisul.pweb.quarta.repository.CategoriaRepository;
import br.unisul.pweb.quarta.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository rep;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = rep.findById(id);
		return obj.orElse(null);
	}
	
	public List<Produto> search(String nome, List<Integer> ids) {
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return rep.findDistinctByNomeContainingAndCategoriasIn(nome, categorias);
	}
	
	//INSERIR
	public Produto insert (Produto obj) {
		obj.setId(null);
		return rep.save(obj);
	}

	//ATUALIZAR
	public Produto update (Produto obj) {
		find(obj.getId());
		return rep.save(obj);
	}

	//DELETAR
	public void delete (Integer id) {
		find(id);
		rep.deleteById(id);
	}
	
		
}