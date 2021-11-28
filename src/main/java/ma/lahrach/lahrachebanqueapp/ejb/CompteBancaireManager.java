/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ma.lahrach.lahrachebanqueapp.ejb;

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
public class CompteBancaireManager {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    public void creerCompte(CompteBancaire c) {
        em.merge(c);
    };
    public List<CompteBancaire> getAllComptes() {
        String s = "select c from Employe as c";
        TypedQuery<CompteBancaire> query = em.createQuery(s, CompteBancaire.class);
        List<CompteBancaire> liste = query.getResultList();
        return liste;
    };
    
        public long nbCompte(){
         String s = "SELECT count(cp) FROM CompteBancaire cp";
        Query query = em.createQuery("SELECT count(c) FROM CompteBancaire c");
        long count = (long)query.getSingleResult();
        return count;
        }


}
