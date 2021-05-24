package com.faturas.springboot.app.controllers;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import com.faturas.springboot.app.models.entity.Fatura;
import com.faturas.springboot.app.models.entity.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faturas.springboot.app.models.entity.Cliente;
import com.faturas.springboot.app.models.entity.ItemFatura;
import com.faturas.springboot.app.models.service.IClienteService;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/fatura")
@SessionAttributes("fatura")
public class FaturaController {

	@Autowired
	private IClienteService clienteService;

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash, Locale locale) {

		Fatura fatura = clienteService.fetchFaturaByIdWithClienteWhithItemFaturaWithProduto(id); // clienteService.findFacturaById(id);

		if (fatura == null) {
			flash.addFlashAttribute("error", messageSource.getMessage("text.fatura.flash.db.error", null, locale));
			return "redirect:/listar";
		}

		model.addAttribute("fatura", fatura);
		model.addAttribute("titulo", String.format(messageSource.getMessage("text.fatura.ver.titulo", null, locale), fatura.getDescricao()));
		return "fatura/ver";
	}

	@GetMapping("/form/{clienteId}")
	public String crear(@PathVariable(value = "clienteId") Long clienteId, Map<String, Object> model,
			RedirectAttributes flash, Locale locale) {

		Cliente cliente = clienteService.findOne(clienteId);

		if (cliente == null) {
			flash.addFlashAttribute("error", messageSource.getMessage("text.cliente.flash.db.error", null, locale));
			return "redirect:/listar";
		}

		Fatura fatura = new Fatura();
		fatura.setCliente(cliente);

		model.put("fatura", fatura);
		model.put("titulo", messageSource.getMessage("text.fatura.form.titulo", null, locale));

		return "fatura/form";
	}

	@GetMapping(value = "/carregar-produtos/{term}", produces = { "application/json" })
	public @ResponseBody List<Produto> cargarProductos(@PathVariable String term) {
		return clienteService.findByNome(term);
	}

	@PostMapping("/form")
	public String guardar(@Valid Fatura fatura, BindingResult result, Model model,
						  @RequestParam(name = "item_id[]", required = false) Long[] itemId,
						  @RequestParam(name = "resultado[]", required = false) Integer[] resultado, RedirectAttributes flash,
						  SessionStatus status, Locale locale) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", messageSource.getMessage("text.fatura.form.titulo", null, locale));
			return "fatura/form";
		}

		if (itemId == null || itemId.length == 0) {
			model.addAttribute("titulo", messageSource.getMessage("text.fatura.form.titulo", null, locale));
			model.addAttribute("error", messageSource.getMessage("text.fatura.flash.lineas.error", null, locale));
			return "fatura/form";
		}

		for (int i = 0; i < itemId.length; i++) {
			Produto produto = clienteService.findProdutoById(itemId[i]);

			ItemFatura linea = new ItemFatura();
			linea.setResultado(resultado[i]);
			linea.setProduto(produto);
			fatura.addItemFactura(linea);

			log.info("ID: " + itemId[i].toString() + ", resultado: " + resultado[i].toString());
		}

		clienteService.saveFatura(fatura);
		status.setComplete();

		flash.addFlashAttribute("success", messageSource.getMessage("text.fatura.flash.criar.success", null, locale));

		return "redirect:/ver/" + fatura.getCliente().getId();
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash, Locale locale) {

		Fatura fatura = clienteService.findFaturaById(id);

		if (fatura != null) {
			clienteService.deleteFatura(id);
			flash.addFlashAttribute("success", messageSource.getMessage("text.fatura.flash.eliminar.success", null, locale));
			return "redirect:/ver/" + fatura.getCliente().getId();
		}
		flash.addFlashAttribute("error", messageSource.getMessage("text.fatura.flash.db.error", null, locale));

		return "redirect:/listar";
	}

}
