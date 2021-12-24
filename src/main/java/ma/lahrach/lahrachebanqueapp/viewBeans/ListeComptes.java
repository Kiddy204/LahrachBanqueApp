/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package ma.lahrach.lahrachebanqueapp.viewBeans;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import ma.lahrach.lahrachebanqueapp.entities.CompteBancaire;
import ma.lahrach.lahrachebanqueapp.session.GestionnaireCompte;

/**
 *
 * @author lial
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }
    
    private int CompteID;
    
    @EJB
    GestionnaireCompte gc;
    
    public List<CompteBancaire> getAllComptes(){
        return gc.getAllComptes();
    }
    
    
    public String afficher(int id) {
        return "Compte?idCompte="+ id +"&amp;faces-redirect=true";
    }
    
    public String supprimer(Long id){
        System.out.println("WBEL");
        gc.supprimer(gc.getCompteById(id));
        return "listeComptes.xhtml&amp;faces-redirect=true";
    }
    
}
