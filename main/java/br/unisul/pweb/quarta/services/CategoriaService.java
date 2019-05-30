package br.unisul.pweb.quarta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.pweb.quarta.domain.Categoria;
import br.unisul.pweb.quarta.repository.CategoriaRepository;

@Service //estereótipo da classe
public class CategoriaService {
	
	@Autowired //evita instanciar variáveis repetidamente
	private CategoriaRepository rep; //variável gerenciada pelo framework
	
	//BUSCAR POR ID
	public Categoria find (Integer id) {
		Optional<Categoria> obj = rep.findById(id);
		return obj.orElse(null);
	}
	
	//INSERIR
	public Categoria insert (Categoria obj) {
		obj.setId(null);
		return rep.save(obj);
	}

	//ATUALIZAR
	public Categoria update (Categoria obj) {
		find(obj.getId());
		return rep.save(obj);
	}

	//DELETAR
	public void delete (Integer id) {
		find(id);
		rep.deleteById(id);
	}
	
	//LISTAR TODAS
	public List<Categoria> findAll(){
		return rep.findAll();
	}
	
	//BUSCAR POR NOME ((((NOVO))))	
	public List<Categoria> findByName(String nome){
		List<Categoria> list = rep.findLikeNome(nome);
		return list;
	}
	
}
