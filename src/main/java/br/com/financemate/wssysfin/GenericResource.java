/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.wssysfin;

import br.com.financemate.wssysfin.Dao.VendaDao;
import br.com.financemate.wssysfin.Dao.VendasComissaoDao;
import br.com.financemate.wssysfin.Dao.VendasDao;
import br.com.financemate.wssysfin.model.Vendas;
import br.com.financemate.wssysfin.model.Vendascomissao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Kamilla Rodrigues
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    @EJB
    private VendaDao vendaDao;
    @EJB
    private VendasComissaoDao vendasComissaoDao;

    /**
     * Retrieves representation of an instance of
     * br.com.financemate.wssysfin.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("retornoVenda")
    @Produces(MediaType.APPLICATION_JSON)
    public VendasSystmBean getVenda() {
        VendasSystmBean venda = new VendasSystmBean();
        Vendas vendas = vendaDao.find("select v from Vendas v where v.idvendas=" + 4629);
        venda = parseVendas(vendas);
        venda.setFornecedor("Travelmate Matriz");
        return venda;
    }

    private VendasSystmBean parseVendas(Vendas vendas) {
        VendasSystmBean vendasSystmBean = new VendasSystmBean();
        vendasSystmBean.setDataVenda(vendas.getDataVenda());
        vendasSystmBean.setIdUnidade(vendas.getUnidadenegocio().getIdunidadeNegocio());
        vendasSystmBean.setIdProduto(vendas.getProdutos().getIdprodutos());
        vendasSystmBean.setIdVenda(vendas.getIdvendas());
        vendasSystmBean.setIdUsuario(vendas.getUsuario().getIdusuario());
        vendasSystmBean.setIdCliente(vendas.getCliente().getIdcliente());
        vendasSystmBean.setConsultor(vendas.getUsuario().getNome());
        vendasSystmBean.setNomeCliente(vendas.getCliente().getNome());
        vendasSystmBean.setNomeUnidade(vendas.getUnidadenegocio().getNomeFantasia());
        vendasSystmBean = parseVendasComissao(vendas, vendasSystmBean);
        return vendasSystmBean;
    }

    private VendasSystmBean parseVendasComissao(Vendas vendas, VendasSystmBean vendasSystmBean) {
        Vendascomissao vendascomissao = new Vendascomissao();
        List<Vendascomissao> listavendascomissao = vendasComissaoDao.list("select vc from Vendascomissao vc where vc.vendas.idvendas=" + vendas.getIdvendas());
        for (int i = 0; i < listavendascomissao.size(); i++) {
            vendascomissao = listavendascomissao.get(i);
        }
        if (vendascomissao.getIdvendascomissao() == null) {
            vendasSystmBean.setLiquidoFranquia(0.0f);
            vendasSystmBean.setValorBruto(0.0f);
        } else {
            vendasSystmBean.setLiquidoFranquia(vendascomissao.getLiquidofranquia());
            vendasSystmBean.setValorBruto(vendascomissao.getValorbruto());
        }
        return vendasSystmBean;
    }

    @GET
    @Path("listaVenda")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VendasSystmBean> getListaVenda(@QueryParam("unidade") Integer idUnidade, @QueryParam("dataInicial") String dataInicial,
                            @QueryParam("dataFinal") String dataFinal) {
        List<Vendas> listaVendas;
        VendasSystmBean vendasSystmBean;
        List<VendasSystmBean> listaVendasSystmBean = new ArrayList<VendasSystmBean>();
        String sql = "select v from Vendas v where v.idvendas>0 ";
        if (idUnidade > 0) {
            sql = sql + " and v.unidadenegocio.idunidadeNegocio=" + idUnidade;
        }
        
        if ((dataInicial != null && dataFinal != null) && (!dataInicial.equalsIgnoreCase("null") && !dataFinal.equalsIgnoreCase("null"))) {
            sql = sql + " and v.dataVenda>='" + dataInicial + "' and v.dataVenda<='" + dataFinal + "'";
        }
        listaVendas = vendaDao.list(sql);
        for (int i = 0; i < listaVendas.size(); i++) {
            vendasSystmBean = new VendasSystmBean();
            vendasSystmBean = parseVendas(listaVendas.get(i));
            vendasSystmBean.setFornecedor("Travelmate Matriz");
            listaVendasSystmBean.add(vendasSystmBean);
        }
        return listaVendasSystmBean;
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param idvenda representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void salvarImportacao(int idvenda) {
        VendasDao vendasDao = new VendasDao();
        vendasDao.salvandoVendaImportada(idvenda);
    }
}
