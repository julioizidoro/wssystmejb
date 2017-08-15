/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.wssysfin.Dao;

import br.com.financemate.wssysfin.model.Formapagamento;
import javax.ejb.Stateless;

/**
 *
 * @author Anderson
 */
@Stateless
public class FormaPagamentoDao extends AbstractDao<Formapagamento>{
    
    public FormaPagamentoDao() {
        super(Formapagamento.class);
    }
    
}
