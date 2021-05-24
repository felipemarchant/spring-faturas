package com.faturas.springboot.app.view.xlsx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faturas.springboot.app.models.entity.Fatura;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.faturas.springboot.app.models.entity.ItemFatura;

@Component("fatura/ver.xlsx")
public class FacturaXlsxView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"factura_view.xlsx\"");
		Fatura fatura = (Fatura) model.get("fatura");
		Sheet sheet = workbook.createSheet("Fatura Spring");
		
		MessageSourceAccessor mensajes =  getMessageSourceAccessor();
		
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue(mensajes.getMessage("text.fatura.ver.dados.cliente"));
		
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue(fatura.getCliente().getNome() + " " + fatura.getCliente().getSobrenome());
		
		row = sheet.createRow(2);
		cell= row.createCell(0);
		cell.setCellValue(fatura.getCliente().getEmail());
		
		sheet.createRow(4).createCell(0).setCellValue(mensajes.getMessage("text.fatura.ver.dados.fatura"));
		sheet.createRow(5).createCell(0).setCellValue(mensajes.getMessage("text.cliente.fatura.folio") + ": " +  fatura.getId());
		sheet.createRow(6).createCell(0).setCellValue(mensajes.getMessage("text.cliente.fatura.descricao") + ": " + fatura.getDescricao());
		sheet.createRow(7).createCell(0).setCellValue(mensajes.getMessage("text.cliente.fatura.fecha") + ": " + fatura.getCreateAt());
		
		CellStyle theaderStyle = workbook.createCellStyle();
		theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
		theaderStyle.setBorderTop(BorderStyle.MEDIUM);
		theaderStyle.setBorderRight(BorderStyle.MEDIUM);
		theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
		theaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
		theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CellStyle tbodyStyle = workbook.createCellStyle();
		tbodyStyle.setBorderBottom(BorderStyle.THIN);
		tbodyStyle.setBorderTop(BorderStyle.THIN);
		tbodyStyle.setBorderRight(BorderStyle.THIN);
		tbodyStyle.setBorderLeft(BorderStyle.THIN);
		
		Row header = sheet.createRow(9);
		header.createCell(0).setCellValue(mensajes.getMessage("text.fatura.form.item.nome"));
		header.createCell(1).setCellValue(mensajes.getMessage("text.fatura.form.item.preco"));
		header.createCell(2).setCellValue(mensajes.getMessage("text.fatura.form.item.resultado"));
		header.createCell(3).setCellValue(mensajes.getMessage("text.fatura.form.item.total"));
		
		header.getCell(0).setCellStyle(theaderStyle);
		header.getCell(1).setCellStyle(theaderStyle);
		header.getCell(2).setCellStyle(theaderStyle);
		header.getCell(3).setCellStyle(theaderStyle);
		
		int rownum = 10;
		
		for(ItemFatura item: fatura.getItens()) {
			Row fila = sheet.createRow(rownum ++);
			cell = fila.createCell(0);
			cell.setCellValue(item.getProduto().getNome());
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(1);
			cell.setCellValue(item.getProduto().getPreco());
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(2);
			cell.setCellValue(item.getResultado());
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(3);
			cell.setCellValue(item.calcularImporte());
			cell.setCellStyle(tbodyStyle);
		}
		
		Row filatotal = sheet.createRow(rownum);
		cell = filatotal.createCell(2);
		cell.setCellValue(mensajes.getMessage("text.fatura.form.total") + ": ");
		cell.setCellStyle(tbodyStyle);
		
		cell= filatotal.createCell(3);
		cell.setCellValue(fatura.getTotal());
		cell.setCellStyle(tbodyStyle);
	
	}

}
