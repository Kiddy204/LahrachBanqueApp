/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.lahrach.lahrachebanqueapp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.Table;

/**
 *
 * @author lial
 */
@Entity
@Table(name = "COMPTEBANCAIRE")
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT c FROM CompteBancaire c"),
    @NamedQuery(name = "CompteBancaire.findByCompteBancaireId", query = "SELECT c FROM CompteBancaire c WHERE c.id = :compteBancaireId"),
    @NamedQuery(name = "CompteBancaire.findByName", query = "SELECT c FROM CompteBancaire c WHERE c.nom = :compteNom"),

    @NamedQuery(name = "CompteBancaire.getAllNames", query = "SELECT c.nom FROM CompteBancaire c"),

    

})
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private int solde;
    public CompteBancaire(){
    }

    public CompteBancaire(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
    }
  

    public void deposer(int montant) {  
      solde += montant;  
    }  

    public void retirer(int montant) {  
      if (montant < solde) {  
        solde -= montant;  
      } else {
        solde = 0;
      }  
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

  

    

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ma.lahrach.lahrachebanqueapp.entities.CompteBancaire[ id=" + id + " ]";
    }

}
