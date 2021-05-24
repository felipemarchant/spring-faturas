package com.faturas.springboot.app.models.dao;

import java.util.List;

import com.faturas.springboot.app.models.entity.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IProdutoDao extends CrudRepository<Produto, Long>{

	@Query("select p from Produto p where p.nome like %?1%")
	public List<Produto> findByNome(String term);
	
	public List<Produto> findByNomeLikeIgnoreCase(String term);
}
