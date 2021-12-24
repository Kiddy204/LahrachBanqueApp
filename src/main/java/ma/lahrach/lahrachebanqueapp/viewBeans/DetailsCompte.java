/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package ma.lahrach.lahrachebanqueapp.viewBeans;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ma.lahrach.lahrachebanqueapp.entities.CompteBancaire;
import ma.lahrach.lahrachebanqueapp.session.GestionnaireCompte;

/**
 *
 * @author lial
 */
@Named(value = "detailsCompte")
@ViewScoped
public class DetailsCompte implements Serializable {

    /**
     * Creates a new instance of DetailsCompte
     */

    private int idCompte;
    
    private int depot;
    
    private int debit;
    
    private int transfert;
    
    private CompteBancaire destinataire;
    
    private CompteBancaire compte;
    
    @EJB
    GestionnaireCompte gc;
    
    
    public DetailsCompte() {
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public int getDepot() {
        return depot;
    }

    public void setDepot(int depot) {
        this.depot = depot;
    }

    public int getDebit() {
        return debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public CompteBancaire getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(CompteBancaire destinataire) {
        this.destinataire = destinataire;
    }

    public int getTransfert() {
        return transfert;
    }

    public void setTransfert(int transfert) {
        this.transfert = transfert;
    }
    
    
       
    
    public void loadCompte(){
       System.out.println("Load Compte");

        this.compte= gc.getCompteById(Long.valueOf(idCompte));
    }
   
    
    public CompteBancaire getCompteById(Long id){
       System.out.println("Get compte by id");

        return gc.getCompteById(id);
    }
    
    public String update(){
        compte= gc.update(compte);
        return "listeComptes.xhtml";

    }
    
    public String crediter(){
        int solde =compte.getSolde();
        solde += depot;
        compte.setSolde(solde);
        compte= gc.update(compte);
        return "listeComptes.xhtml";
    }
    
    public String debiter(){
        
        int solde =compte.getSolde();
        if(solde > debit){
            solde -= debit;
            compte.setSolde(solde);
            compte= gc.update(compte);
        }
       
        return "listeComptes.xhtml";
    }

        
    public String transferer(){
        System.out.println(destinataire.getNom());
        CompteBancaire dest = gc.getCompteById(destinataire.getId());
        gc.tranferer(compte, dest, transfert);
        return "listeComptes.xhtml;faces-redirect=true";
    }
    public String create(){
        gc.save(compte);
        return "listeComptes.xhtml;faces-redirect=true";

    }
    public Converter<CompteBancaire> getCompteBancaireConverter(){
        return new Converter<CompteBancaire>(){
            
            @Override
            public CompteBancaire getAsObject(FacesContext context, UIComponent component, String value){
                System.out.println("COnvereter toObject : ");
                System.out.println(value);
                return gc.getCompteByName(value);
         
            }
            @Override
            public String getAsString(FacesContext context, UIComponent component, CompteBancaire value){
                return value.getNom();
            }
        };
    }
}
