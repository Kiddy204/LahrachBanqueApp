/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ma.lahrach.lahrachebanqueapp.ejb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import ma.lahrach.lahrachebanqueapp.entities.CompteBancaire;

/**
 *s
 * @author lial
 */

@Stateless
public class CompteBancaireManager {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    void creerCompte(CompteBancaire c) {
        em.merge(c);
    };
    List<CompteBancaire> getAllComptes() {
        String s = "select c from Employe as c";
        TypedQuery<CompteBancaire> query = em.createQuery(s, CompteBancaire.class);
        List<CompteBancaire> liste = query.getResultList();
        return liste;
    };
      public void persist(CompteBancaire compte) {
        em.persist(compte);
    }

}
