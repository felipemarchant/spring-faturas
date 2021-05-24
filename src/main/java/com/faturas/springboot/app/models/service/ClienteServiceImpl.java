package com.faturas.springboot.app.models.service;

import java.util.List;

import com.faturas.springboot.app.models.entity.Fatura;
import com.faturas.springboot.app.models.entity.Produto;
import com.faturas.springboot.app.models.dao.IClienteDao;
import com.faturas.springboot.app.models.dao.IFaturaDao;
import com.faturas.springboot.app.models.dao.IProdutoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faturas.springboot.app.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IProdutoDao produtoDao;
	
	@Autowired
	private IFaturaDao faturaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() { return (List<Cliente>) clienteDao.findAll(); }

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return clienteDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Cliente fetchByIdWithFaturas(Long id) {
		return clienteDao.fetchByIdWithFaturas(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Produto> findByNome(String term) {
		return produtoDao.findByNomeLikeIgnoreCase("%"+term+"%");
	}

	@Override
	@Transactional
	public void saveFatura(Fatura fatura) {
		faturaDao.save(fatura);
	}

	@Override
	@Transactional(readOnly=true)
	public Produto findProdutoById(Long id) {
		return produtoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Fatura findFaturaById(Long id) {
		return faturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteFatura(Long id) {
		faturaDao.deleteById(id); // facturaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Fatura fetchFaturaByIdWithClienteWhithItemFaturaWithProduto(Long id) {
		return faturaDao.fetchByIdWithClienteWhithItemFaturaWithProduto(id);
	}
}
