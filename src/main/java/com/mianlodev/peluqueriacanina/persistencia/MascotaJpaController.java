package com.mianlodev.peluqueriacanina.persistencia;

import com.mianlodev.peluqueriacanina.logica.Mascota;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;

public class MascotaJpaController implements Serializable {
    
    public MascotaJpaController(EntityManagerFactory emf){
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public MascotaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PeluCaninaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mascota mascota) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(mascota);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mascota mascota) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(mascota);
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
            Mascota mascota;
            try {
                mascota = em.getReference(Mascota.class, id);
                mascota.getNumCliente();
            } catch (Exception e) {
                throw new IllegalArgumentException("La mascota con id " + id + " no existe.", e);
            }
            em.remove(mascota);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Mascota findMascota(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mascota.class, id);
        } finally {
            em.close();
        }
    }

    public List<Mascota> findMascotaEntities() {
        return findMascotaEntities(true, -1, -1);
    }

    public List<Mascota> findMascotaEntities(int maxResults, int firstResult) {
        return findMascotaEntities(false, maxResults, firstResult);
    }

    private List<Mascota> findMascotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Mascota> cq = em.getCriteriaBuilder().createQuery(Mascota.class);
            cq.select(cq.from(Mascota.class));
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

    public int getMascotaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            cq.select(em.getCriteriaBuilder().count(cq.from(Mascota.class)));
            return em.createQuery(cq).getSingleResult().intValue();
        } finally {
            em.close();
        }
    }
}
