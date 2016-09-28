/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.wssysfin.Dao;

import br.com.financemate.wssysfin.model.Vendas;
import javax.ejb.Stateless;

@Stateless
public class VendaDao extends AbstractDao<Vendas>{
    
    public VendaDao() {
        super(Vendas.class);
    }
    
}
