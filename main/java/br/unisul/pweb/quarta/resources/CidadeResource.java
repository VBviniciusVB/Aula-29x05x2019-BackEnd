package br.unisul.pweb.quarta.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.unisul.pweb.quarta.domain.Cidade;
import br.unisul.pweb.quarta.dtos.CidadeDTO;
import br.unisul.pweb.quarta.resources.utils.URL;
import br.unisul.pweb.quarta.services.CidadeService;

@RestController
@RequestMapping(value="/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService service;
	
	//BUSCAR POR ID
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Cidade> find(@PathVariable Integer id){
		Cidade obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//BUSCAR POR NOME
	@RequestMapping(value="/busca",method=RequestMethod.GET)
	public ResponseEntity<List<Cidade>> find(@RequestParam(value="nome", defaultValue="")String nome){
		List <Cidade> list = service.findByName(URL.decodeParam(nome));
		return ResponseEntity.ok().body(list);
	}
	
	////BUSCAR ESTADO POR NOME
	//@RequestMapping(value="/buscaestado",method=RequestMethod.GET)
	//public ResponseEntity<List<Cidade>> findState(@RequestParam(value="nome", defaultValue="")String nome){
	//	List <Cidade> list = service.findStateByName(URL.decodeParam(nome));
	//	return ResponseEntity.ok().body(list);
	//}
	
	//INSERIR
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void>insert(@RequestBody Cidade obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
		path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//ATUALIZAR
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Cidade obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	//EXCLUIR
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//LISTAR TODAS
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findAll() {
		List<Cidade> lista = service.findAll();
		//List<CategoriaDTO> listaDTO = lista.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList()); 
		//ou for para percorrer a lista:
		List<CidadeDTO> listaDTO = new ArrayList<CidadeDTO>();
		for (Cidade c : lista) {
			listaDTO.add(new CidadeDTO(c));
		}
		return ResponseEntity.ok().body(listaDTO);
	}
	
}
