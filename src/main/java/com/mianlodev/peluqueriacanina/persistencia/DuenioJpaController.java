package com.mianlodev.peluqueriacanina.persistencia;

import com.mianlodev.peluqueriacanina.logica.Duenio;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;

public class DuenioJpaController implements Serializable {
    
    public DuenioJpaController(EntityManagerFactory emf){
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public DuenioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PeluCaninaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Duenio duenio) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(duenio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Duenio duenio) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(duenio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Duenio duenio;
            try {
                duenio = em.getReference(Duenio.class, id);
                duenio.getId_duenio();
            } catch (Exception e) {
                throw new IllegalArgumentException("El dueño con id " + id + " no existe.", e);
            }
            em.remove(duenio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Duenio findDuenio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Duenio.class, id);
        } finally {
            em.close();
        }
    }

    public List<Duenio> findDuenioEntities() {
        return findDuenioEntities(true, -1, -1);
    }

    public List<Duenio> findDuenioEntities(int maxResults, int firstResult) {
        return findDuenioEntities(false, maxResults, firstResult);
    }

    private List<Duenio> findDuenioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Duenio> cq = em.getCriteriaBuilder().createQuery(Duenio.class);
            cq.select(cq.from(Duenio.class));
            var q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getDuenioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            cq.select(em.getCriteriaBuilder().count(cq.from(Duenio.class)));
            return em.createQuery(cq).getSingleResult().intValue();
        } finally {
            em.close();
        }
    }
}
