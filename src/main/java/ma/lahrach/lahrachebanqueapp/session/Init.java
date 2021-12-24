/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package ma.lahrach.lahrachebanqueapp.session;

import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import ma.lahrach.lahrachebanqueapp.entities.CompteBancaire;

/**
 *
 * @author lial
 */
@Startup
@Singleton
public class Init {
    @EJB
    private GestionnaireCompte compteBancaireManager;
    
    @PostConstruct
    public void InitComptes(){
        if(compteBancaireManager.nbCompte() == 0){
            compteBancaireManager.creerCompte(new CompteBancaire("John Lennon", 0));
            compteBancaireManager.creerCompte(new CompteBancaire("Paul McCartney", 250045987));
            compteBancaireManager.creerCompte(new CompteBancaire("Ringo Starr ", 2002545));
            compteBancaireManager.creerCompte(new CompteBancaire("Georges Harrisson", 1110200));
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
