/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.wssysfin.Dao;

import br.com.financemate.wssysfin.model.Parcelamentopagamento;
import javax.ejb.Stateless;

/**
 *
 * @author Anderson
 */
@Stateless
public class ParcelamentoPagamentoDao extends AbstractDao<Parcelamentopagamento>{
    
    public ParcelamentoPagamentoDao() {
        super(Parcelamentopagamento.class);
    }
    
}
