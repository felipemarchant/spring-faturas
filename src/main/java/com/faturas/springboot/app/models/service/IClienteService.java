package com.faturas.springboot.app.models.service;

import java.util.List;

import com.faturas.springboot.app.models.entity.Fatura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.faturas.springboot.app.models.entity.Cliente;
import com.faturas.springboot.app.models.entity.Produto;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);

	public void save(Cliente cliente);
	
	public Cliente findOne(Long id);
	
	public Cliente fetchByIdWithFaturas(Long id);
	
	public void delete(Long id);
	
	public List<Produto> findByNome(String term);
	
	public void saveFatura(Fatura fatura);
	
	public Produto findProdutoById(Long id);
	
	public Fatura findFaturaById(Long id);
	
	public void deleteFatura(Long id);
	
	public Fatura fetchFaturaByIdWithClienteWhithItemFaturaWithProduto(Long id);

}
