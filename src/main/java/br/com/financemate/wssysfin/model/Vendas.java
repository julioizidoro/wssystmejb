/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.wssysfin.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author Kamilla Rodrigues
 */

@Entity
@Table(name = "vendas")
public class Vendas implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvendas")
    private Integer idvendas;
    @Column(name = "dataVenda")
    @Temporal(TemporalType.DATE)
    private Date dataVenda;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Float valor;
    @Size(max = 20)
    @Column(name = "situacao")
    private String situacao;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Obstm")
    private String obstm;
    @Size(max = 1)
    @Column(name = "vendasMatriz")
    private String vendasMatriz;
    @Column(name = "vendaimportada")
    private Integer vendaimportada;
    @Size(max = 200)
    @Column(name = "obsCancelar")
    private String obsCancelar;
    @Size(max = 1)
    @Column(name = "statuscobranca")
    private String statuscobranca;
    @Column(name = "datacancelamento")
    @Temporal(TemporalType.DATE)
    private Date datacancelamento;
    @Column(name = "usuariocancelamento")
    private Integer usuariocancelamento;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "produtos_idprodutos", referencedColumnName = "idprodutos")
    @ManyToOne(optional = false)
    private Produtos produtos;
    @JoinColumn(name = "unidadeNegocio_idunidadeNegocio", referencedColumnName = "idunidadeNegocio")
    @ManyToOne(optional = false)
    private Unidadenegocio unidadenegocio;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario; 
    @Column(name = "valorcambio")
    private Float valorcambio;
    @Column(name = "restricaoparcelamento")
    private boolean restricaoparcelamento;
    @Column(name = "ponto")
    private int ponto;
    @Transient 
    private String botao;

    public Integer getIdvendas() {
        return idvendas;
    }

    public void setIdvendas(Integer idvendas) {
        this.idvendas = idvendas;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getObstm() {
        return obstm;
    }

    public void setObstm(String obstm) {
        this.obstm = obstm;
    }

    public String getVendasMatriz() {
        return vendasMatriz;
    }

    public void setVendasMatriz(String vendasMatriz) {
        this.vendasMatriz = vendasMatriz;
    }

    public Integer getVendaimportada() {
        return vendaimportada;
    }

    public void setVendaimportada(Integer vendaimportada) {
        this.vendaimportada = vendaimportada;
    }

    public String getObsCancelar() {
        return obsCancelar;
    }

    public void setObsCancelar(String obsCancelar) {
        this.obsCancelar = obsCancelar;
    }

    public String getStatuscobranca() {
        return statuscobranca;
    }

    public void setStatuscobranca(String statuscobranca) {
        this.statuscobranca = statuscobranca;
    }

    public Date getDatacancelamento() {
        return datacancelamento;
    }

    public void setDatacancelamento(Date datacancelamento) {
        this.datacancelamento = datacancelamento;
    }

    public Integer getUsuariocancelamento() {
        return usuariocancelamento;
    }

    public void setUsuariocancelamento(Integer usuariocancelamento) {
        this.usuariocancelamento = usuariocancelamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    public Unidadenegocio getUnidadenegocio() {
        return unidadenegocio;
    }

    public void setUnidadenegocio(Unidadenegocio unidadenegocio) {
        this.unidadenegocio = unidadenegocio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Float getValorcambio() {
        return valorcambio;
    }

    public void setValorcambio(Float valorcambio) {
        this.valorcambio = valorcambio;
    }

    public boolean isRestricaoparcelamento() {
        return restricaoparcelamento;
    }

    public void setRestricaoparcelamento(boolean restricaoparcelamento) {
        this.restricaoparcelamento = restricaoparcelamento;
    }

    public int getPonto() {
        return ponto;
    }

    public void setPonto(int ponto) {
        this.ponto = ponto;
    }

    public String getBotao() {
        return botao;
    }

    public void setBotao(String botao) {
        this.botao = botao;
    }
    
    
    
    
    
}
