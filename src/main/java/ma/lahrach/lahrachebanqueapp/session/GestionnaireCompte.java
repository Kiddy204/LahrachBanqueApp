/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ma.lahrach.lahrachebanqueapp.session;

import java.util.List;
import javax.annotation.sql.DataSourceDefinition;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import ma.lahrach.lahrachebanqueapp.entities.CompteBancaire;

/**
 *s
 * @author lial
 */
@DataSourceDefinition (
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/banque",
    serverName="localhost",
    portNumber=3306,
    user="root",              // nom et
    password="rootroot", // mot de passe que vous avez donnés lors de la création de la base de données
    databaseName="banque",
    properties = {
      "useSSL=false",
      "allowPublicKeyRetrieval=true"
    }
)
@Stateless
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    };
    public List<CompteBancaire> getAllComptes() {
        String s = "select c from CompteBancaire as c";
        TypedQuery<CompteBancaire> query = em.createQuery(s, CompteBancaire.class);
        List<CompteBancaire> liste = query.getResultList();
        return liste;
    };
        
    public List<String> getAllNames(){
        Query query = em.createNamedQuery("CompteBancaire.getAllNames");
        return query.getResultList();
    }
    
    public CompteBancaire getCompteById(Long id){
        return em.find(CompteBancaire.class, id);
    }
    
    public CompteBancaire getCompteByName(String name){
        TypedQuery <CompteBancaire> query = em.createQuery("SELECT c FROM CompteBancaire c WHERE c.nom = :compteNom", CompteBancaire.class);
        query.setParameter("compteNom", name);
        return query.getResultList().get(0);
    }

    public long nbCompte(){
         String s = "SELECT count(cp) FROM CompteBancaire cp";
        Query query = em.createQuery("SELECT count(c) FROM CompteBancaire c");
        long count = (long)query.getSingleResult();
        return count;
    }
    
    public CompteBancaire update(CompteBancaire compte){
        return em.merge(compte);
    }
    
    public CompteBancaire tranferer(CompteBancaire src, CompteBancaire dest, int montant ){
        try{
            int solde= src.getSolde();
            if(solde< montant){
                throw new Exception("Solde Insuffisant");
            }
            src.setSolde(solde- montant);
            dest.setSolde(dest.getSolde()+montant);
            src = em.merge(src);
            dest = em.merge(dest);
            return src;
   
        }catch( Exception e){
            System.err.println("Solde Insuffisant");
        }
        return null;   
    }
    
    public void save(CompteBancaire compte){
        em.persist(compte);
    }

    public void supprimer(CompteBancaire compte){
           em.remove(em.merge(compte));
    }
}
