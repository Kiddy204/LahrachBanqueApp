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
@Named(value = "formCompte")
@ViewScoped
public class formCompte implements Serializable {


    private CompteBancaire compte;
    
    private int idCompte;
    
    
        
    @EJB
    GestionnaireCompte gc;
    
    public formCompte() {
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }
  
    public void loadCompte(){
        this.compte= gc.getCompteById(Long.valueOf(idCompte));
    }
   
    public String create(){
        gc.creerCompte(compte);
        return "listeComptes.xhtml";
    }
}
