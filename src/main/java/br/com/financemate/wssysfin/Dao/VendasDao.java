/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.wssysfin.Dao;

import br.com.financemate.wssysfin.VendasSystmBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kamilla Rodrigues
 */
public class VendasDao extends  Dao{
    
    
    private final String SELECT = "SELECT * FROM vendas WHERE vendaimportada=0";
    private final String SELECT_ID = "SELECT * FROM vendas WHERE idvendas = ?";
    private final String SELECT_IDCOMISSAO = "SELECT * FROM vendascomissao WHERE vendas_idvendas = ?";
    private final String SELECT_IDCONSULTO = "SELECT * FROM usuario WHERE idusuario = ?";
    private final String SELECT_IDCLIENTE = "SELECT * FROM cliente WHERE idcliente = ?";
    private final String SELECT_FORMAPAGAMENTO = "SELECT * FROM formapagamento WHERE vendas_idvendas = ?";
    private final String SELECT_VALORPAGAMENTO =  "SELECT SUM(valorParcelamento) as somaTotal FROM parcelamentopagamento WHERE formapagamento_idformaPagamento = ?";
   

    public VendasSystmBean buscarPorIdVendas(Integer id) {
        VendasSystmBean vendasSystmBean = null;
        try { 
            PreparedStatement ps = getConnection().prepareStatement(SELECT_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {  
                vendasSystmBean = parseVendas(rs);
                vendasSystmBean =  buscarPorIdVendasComissao(vendasSystmBean);
                vendasSystmBean = buscarPorIdUsuario(vendasSystmBean);
                vendasSystmBean = buscarPorIdCliente(vendasSystmBean);
            }

            ps.close();
            return vendasSystmBean;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao executar o select de vendas systm: " + e);
        }
        return null;
    }
    
    
    public VendasSystmBean buscarPorIdVendasComissao(VendasSystmBean venda) {
        try { 
            PreparedStatement ps = getConnection().prepareStatement(SELECT_IDCOMISSAO);
            ps.setInt(1, venda.getIdVenda());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                venda = parseVendasComissao(rs, venda);
            }

            ps.close();
            return venda;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao executar o select de vendas systm: " + e);
        }
        return null;
    }
    
    
    public VendasSystmBean buscarPorIdUsuario(VendasSystmBean venda) {
        try { 
            PreparedStatement ps = getConnection().prepareStatement(SELECT_IDCONSULTO);
            ps.setInt(1, venda.getIdUsuario());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {  
                venda = parseUsuario(rs, venda);
            }

            ps.close();
            return venda;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao executar o select de vendas systm: " + e);
        }
        return null;
    }
    
    
    public VendasSystmBean buscarPorIdCliente(VendasSystmBean venda) {
        try { 
            PreparedStatement ps = getConnection().prepareStatement(SELECT_IDCLIENTE);
            ps.setInt(1, venda.getIdCliente());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                venda = parseCliente(rs, venda);
            }

            ps.close();
            return venda;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao executar o select de vendas systm: " + e);
        }
        return null;
    }
    

    private VendasSystmBean parseVendas(ResultSet rs) throws SQLException {
        VendasSystmBean vendas = new VendasSystmBean();
        vendas.setDataVenda(rs.getDate("dataVenda"));
        vendas.setIdUnidade(rs.getInt("unidadeNegocio_idunidadeNegocio"));
        vendas.setIdProduto(rs.getInt("produtos_idprodutos"));
        vendas.setIdVenda(rs.getInt("idvendas"));
        vendas.setIdUsuario(rs.getInt("usuario_idusuario"));
        vendas.setIdCliente(rs.getInt("cliente_idcliente"));
        //vendas.setValorFormaPagamento(rs.getFloat("valorTotal"));
        return vendas;
    }
    
    
    private VendasSystmBean parseVendasComissao(ResultSet rs, VendasSystmBean venda) throws SQLException {
        venda.setLiquidoFranquia(rs.getFloat("liquidofranquia"));
        venda.setValorBruto(rs.getFloat("valorbruto"));
        //vendas.setValorFormaPagamento(rs.getFloat("valorTotal"));
        return venda;
    }
    
    
    private VendasSystmBean parseUsuario(ResultSet rs, VendasSystmBean venda) throws SQLException {
        venda.setConsultor(rs.getString("nome"));
        return venda;
    }
    
    private VendasSystmBean parseCliente(ResultSet rs, VendasSystmBean venda) throws SQLException {
        venda.setNomeCliente(rs.getString("nome"));
        return venda;
    }

    public List<VendasSystmBean> listarTodos() {
        List<VendasSystmBean> listaVendas = new ArrayList<VendasSystmBean>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VendasSystmBean vendasSystmBean = parseVendas(rs);
                vendasSystmBean =  buscarPorIdVendasComissao(vendasSystmBean);
                vendasSystmBean = buscarPorIdUsuario(vendasSystmBean);
                vendasSystmBean = buscarPorIdCliente(vendasSystmBean);
                listaVendas.add(vendasSystmBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao executar o select de Vendas systm: " + e);
        }
        return listaVendas;
    }
    
    public List<VendasSystmBean> listarFormaPagamento(VendasSystmBean venda) {
        List<VendasSystmBean> listaVendas = new ArrayList<VendasSystmBean>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(SELECT_FORMAPAGAMENTO);
            ps.setInt(1, venda.getIdVenda());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VendasSystmBean vendasSystmBean = parseVendas(rs);
                vendasSystmBean =  buscarPorIdVendasComissao(vendasSystmBean);
                vendasSystmBean = buscarPorIdUsuario(vendasSystmBean);
                vendasSystmBean = buscarPorIdCliente(vendasSystmBean);
                listaVendas.add(vendasSystmBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao executar o select de Vendas systm: " + e);
        }
        return listaVendas;
    }
    
   
    
    private int parseForntmaPagamento(ResultSet rs, VendasSystmBean venda) throws SQLException {
        int idForma = (rs.getInt("idformaPagamento"));
        return idForma;
    }
    
    
    public VendasSystmBean buscarParcelamentoPagamento(int idForma, VendasSystmBean venda) {
        try { 
            PreparedStatement ps = getConnection().prepareStatement(SELECT_VALORPAGAMENTO);
            ps.setInt(1, idForma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                venda = parseUsuario(rs, venda);
            }

            ps.close();
            return venda;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao executar o select de vendas systm: " + e);
        }
        return null;
    }
    
    
     public void salvandoVendaImportada(int idvenda) {
        final String UPDATE = "UPDATE vendas SET vendaimportada= 1 WHERE idvendas=" + idvenda;
        try { 
            PreparedStatement ps = getConnection().prepareStatement(UPDATE);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao executar o select de vendas systm: " + e);
        }
    }
    
    
    private int parsePagamento(ResultSet rs) throws SQLException {
        int idForma = (rs.getInt("somaTotal"));
        return idForma;
    }
}
