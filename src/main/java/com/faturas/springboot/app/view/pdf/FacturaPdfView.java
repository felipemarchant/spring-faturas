package com.faturas.springboot.app.view.pdf;

import java.awt.Color;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faturas.springboot.app.models.entity.Fatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.faturas.springboot.app.models.entity.ItemFatura;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("fatura/ver")
public class FacturaPdfView extends AbstractPdfView {

	@Autowired
    private MessageSource messageSource;
	
	@Autowired
    private LocaleResolver localeResolver;
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Fatura fatura = (Fatura) model.get("fatura");
		
		Locale locale = localeResolver.resolveLocale(request);
		
		MessageSourceAccessor mensajes =  getMessageSourceAccessor();
		
		PdfPTable tabla = new PdfPTable(1);
		tabla.setSpacingAfter(20);
		
		PdfPCell cell = null;
		
		cell = new PdfPCell(new Phrase(messageSource.getMessage("text.fatura.ver.dados.cliente", null, locale)));
		cell.setBackgroundColor(new Color(184, 218, 255));
		cell.setPadding(8f);
		tabla.addCell(cell);
		
		
		tabla.addCell(fatura.getCliente().getNome() + " " + fatura.getCliente().getSobrenome());
		tabla.addCell(fatura.getCliente().getEmail());
		
		PdfPTable tabla2 = new PdfPTable(1);
		tabla2.setSpacingAfter(20);
		
		
		cell = new PdfPCell(new Phrase(messageSource.getMessage("text.fatura.ver.dados.fatura", null, locale)));
		cell.setBackgroundColor(new Color(195, 230, 203));
		cell.setPadding(8f);
		
		tabla2.addCell(cell);
		tabla2.addCell(mensajes.getMessage("text.cliente.fatura.folio") + ": " +  fatura.getId());
		tabla2.addCell(mensajes.getMessage("text.cliente.fatura.descricao") + ": " + fatura.getDescricao());
		tabla2.addCell(mensajes.getMessage("text.cliente.fatura.fecha") + ": " + fatura.getCreateAt());
		
		document.add(tabla);
		document.add(tabla2);
		
		PdfPTable tabla3 = new PdfPTable(4);
		tabla3.setWidths(new float [] {3.5f, 1, 1, 1});
		tabla3.addCell(mensajes.getMessage("text.fatura.form.item.nome"));
		tabla3.addCell(mensajes.getMessage("text.fatura.form.item.preco"));
		tabla3.addCell(mensajes.getMessage("text.fatura.form.item.resultado"));
		tabla3.addCell(mensajes.getMessage("text.fatura.form.item.total"));
		
		for(ItemFatura item: fatura.getItens()) {
			tabla3.addCell(item.getProduto().getNome());
			tabla3.addCell(item.getProduto().getPreco().toString());
			
			cell = new PdfPCell(new Phrase(item.getResultado().toString()));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			tabla3.addCell(cell);
			tabla3.addCell(item.calcularImporte().toString());
		}
		
	    cell = new PdfPCell(new Phrase(mensajes.getMessage("text.fatura.form.total") + ": "));
	    cell.setColspan(3);
	    cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	    tabla3.addCell(cell);
	    tabla3.addCell(fatura.getTotal().toString());
	    
	    document.add(tabla3);
		
	}

}
