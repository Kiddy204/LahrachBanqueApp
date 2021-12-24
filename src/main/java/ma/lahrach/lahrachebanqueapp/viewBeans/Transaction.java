/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package ma.lahrach.lahrachebanqueapp.viewBeans;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import ma.lahrach.lahrachebanqueapp.entities.CompteBancaire;
import ma.lahrach.lahrachebanqueapp.session.GestionnaireCompte;

/**
 *
 * @author lial
 */
@Named(value = "transaction")
@ViewScoped
public class Transaction implements Serializable {

    /**
     * Creates a new instance of Transaction
     */
    public Transaction() {
    }
    
    @EJB
    GestionnaireCompte gc;
    
    public CompteBancaire getCompteBancaire(Long id){
        return gc.getCompteById(id);
    }
    
}
