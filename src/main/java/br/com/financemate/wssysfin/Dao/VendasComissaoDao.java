/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.wssysfin.Dao;

import br.com.financemate.wssysfin.model.Vendascomissao;
import javax.ejb.Stateless;

@Stateless
public class VendasComissaoDao extends AbstractDao<Vendascomissao>{
    
    public VendasComissaoDao() {
        super(Vendascomissao.class);
    }
    
}
