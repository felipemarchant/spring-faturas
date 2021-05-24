package com.faturas.springboot.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
	
	private String url;
	private Page<T> page;
	
	private int totalPaginas;
	
	private int numElementosPorPagina;
	
	private int paginaAtual;
	
	private List<PageItem> paginas;
	
	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>();
		
		numElementosPorPagina = page.getSize();
		totalPaginas = page.getTotalPages();
		paginaAtual = page.getNumber() + 1;
		
		int inicio, fim;
		if(totalPaginas <= numElementosPorPagina) {
			inicio = 1;
			fim = totalPaginas;
		} else {
			if(paginaAtual <= numElementosPorPagina/2) {
				inicio = 1;
				fim = numElementosPorPagina;
			} else if(paginaAtual >= totalPaginas - numElementosPorPagina/2 ) {
				inicio = totalPaginas - numElementosPorPagina + 1;
				fim = numElementosPorPagina;
			} else {
				inicio = paginaAtual -numElementosPorPagina/2;
				fim = numElementosPorPagina;
			}
		}
		
		for(int i=0; i < fim; i++) {
			paginas.add(new PageItem(inicio + i, paginaAtual == inicio+i));
		}

	}

	public String getUrl() {
		return url;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getPaginaAtual() {
		return paginaAtual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean isHasNext() {
		return page.hasNext();
	}
	
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}

}
