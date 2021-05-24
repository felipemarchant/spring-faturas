package com.faturas.springboot.app.models.dao;

import com.faturas.springboot.app.models.entity.Fatura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IFaturaDao extends CrudRepository<Fatura, Long>{

	@Query("select f from Fatura f join fetch f.cliente c join fetch f.itens l join fetch l.produto where f.id=?1")
	public Fatura fetchByIdWithClienteWhithItemFaturaWithProduto(Long id);
}
