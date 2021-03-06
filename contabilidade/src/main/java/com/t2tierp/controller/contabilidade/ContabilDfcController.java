/*
* The MIT License
* 
* Copyright: Copyright (C) 2014 T2Ti.COM
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
* 
* The author may be contacted at: t2ti.com@gmail.com
*
* @author Claudio de Barros (T2Ti.com)
* @version 2.0
*/
package com.t2tierp.controller.contabilidade;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.t2tierp.controller.AbstractController;
import com.t2tierp.model.bean.cadastros.Empresa;
import com.t2tierp.model.bean.contabilidade.ViewDfcVO;
import com.t2tierp.util.FacesContextUtil;

@ManagedBean
@ViewScoped
public class ContabilDfcController extends AbstractController<ViewDfcVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String periodo;
    
    @Override
    public Class<ViewDfcVO> getClazz() {
        return ViewDfcVO.class;
    }

    @Override
    public String getFuncaoBase() {
        return "CONTABIL_DFC";
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void geraDFC() {
		try {
			//gera o relatorio
			File file = File.createTempFile("dfc", ".pdf");
			file.deleteOnExit();
			
	        List<ViewDfcVO> listaDfc = dao.getBeans(ViewDfcVO.class, "periodo", periodo);
	        Empresa empresa = FacesContextUtil.getEmpresaUsuario();
	        ImageIcon logoEmpresa = new ImageIcon(this.getClass().getResource("/images/t2ti.jpg"));
	        Map parametros = new HashMap();
	        parametros.put("LOGO_EMPRESA", logoEmpresa.getImage());
	        parametros.put("NOME_FANTASIA", empresa.getNomeFantasia());
	        parametros.put("RAZAO_SOCIAL", empresa.getRazaoSocial());
	        parametros.put("NOME_SOFTWARE_HOUSE", "T2Ti.com");

			JRBeanCollectionDataSource jrbean = new JRBeanCollectionDataSource(listaDfc);
			JasperPrint jp = JasperFillManager.fillReport(this.getClass().getResourceAsStream("/relatorios/Dfc.jasper"), parametros, jrbean);
			
			OutputStream outPdf = new FileOutputStream(file);
			outPdf.write(JasperExportManager.exportReportToPdf(jp));
			outPdf.close();

			FacesContextUtil.downloadArquivo(file, "dfc.pdf");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContextUtil.adicionaMensagem(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro ao gerar o relatório!", e.getMessage());
		}
	}    
    
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}