package br.unisul.pweb.quarta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.pweb.quarta.domain.Cidade;
import br.unisul.pweb.quarta.repository.CidadeRepository;

@Service
public class CidadeService {
	@Autowired
	private CidadeRepository rep;
	
	@Autowired
	private EstadoService estadoService;
	
	//BUSCA POR ESTADO
	public List<Cidade> findByEstado(Integer estadoId) {
		return rep.findCidades(estadoId);
	}
	
	//BUSCAR POR ID
	public Cidade find (Integer id) {
		Optional<Cidade> obj = rep.findById(id);
		return obj.orElse(null);
	}
	
	//INSERIR
	public Cidade insert (Cidade obj) {
		obj.setId(null);
		obj.setEstado(estadoService.find(obj.getEstado().getId()));
		obj = rep.save(obj);
		return obj;
	}
	
	//ATUALIZAR
	public Cidade update (Cidade obj) {
		find(obj.getId());
		return rep.save(obj);
	}
	
	//DELETAR
	public void delete (Integer id) {
		find(id);
		rep.deleteById(id);
	}
	
	//LISTAR TODAS
	public List<Cidade> findAll(){
		return rep.findAll();
	}
	
	//BUSCAR POR NOME ((((NOVO))))	
	public List<Cidade> findByName(String nome){
		List<Cidade> list = rep.findLikeNome(nome);
		return list;
	}
	
	////BUSCA ID DO ESTADO POR NOME
	//public List<Cidade> findStateByName(String nome){
	//	List<Cidade> list = rep.findEstadoLikeNome(nome);
	//	return list;
	//}
}