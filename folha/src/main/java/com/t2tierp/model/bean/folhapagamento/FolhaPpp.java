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
package com.t2tierp.model.bean.folhapagamento;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.t2tierp.model.bean.cadastros.Colaborador;


@Entity
@Table(name = "FOLHA_PPP")
public class FolhaPpp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "OBSERVACAO")
    private String observacao;
    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Colaborador colaborador;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "folhaPpp", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<FolhaPppCat> listaFolhaPppCat;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "folhaPpp", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<FolhaPppAtividade> listaFolhaPppAtividade;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "folhaPpp", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<FolhaPppFatorRisco> listaFolhaPppFatorRisco;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "folhaPpp", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<FolhaPppExameMedico> listaFolhaPppExameMedico;

    public FolhaPpp() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Set<FolhaPppCat> getListaFolhaPppCat() {
		return listaFolhaPppCat;
	}

	public void setListaFolhaPppCat(Set<FolhaPppCat> listaFolhaPppCat) {
		this.listaFolhaPppCat = listaFolhaPppCat;
	}

	public Set<FolhaPppAtividade> getListaFolhaPppAtividade() {
		return listaFolhaPppAtividade;
	}

	public void setListaFolhaPppAtividade(Set<FolhaPppAtividade> listaFolhaPppAtividade) {
		this.listaFolhaPppAtividade = listaFolhaPppAtividade;
	}

	public Set<FolhaPppFatorRisco> getListaFolhaPppFatorRisco() {
		return listaFolhaPppFatorRisco;
	}

	public void setListaFolhaPppFatorRisco(Set<FolhaPppFatorRisco> listaFolhaPppFatorRisco) {
		this.listaFolhaPppFatorRisco = listaFolhaPppFatorRisco;
	}

	public Set<FolhaPppExameMedico> getListaFolhaPppExameMedico() {
		return listaFolhaPppExameMedico;
	}

	public void setListaFolhaPppExameMedico(Set<FolhaPppExameMedico> listaFolhaPppExameMedico) {
		this.listaFolhaPppExameMedico = listaFolhaPppExameMedico;
	}

	@Override
    public String toString() {
        return "com.t2tierp.model.bean.folhapagamento.FolhaPpp[id=" + id + "]";
    }

}
