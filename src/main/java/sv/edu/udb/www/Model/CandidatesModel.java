/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.www.Entities.Candidates;
import sv.edu.udb.www.Entities.CandidatesForCities;
import sv.edu.udb.www.Entities.ElectoralProcess;
import sv.edu.udb.www.Entities.PresidencialCandidates;

/**
 *
 * @author Diego Lemus
 */
@Stateless
public class CandidatesModel {

    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;

    public List<Candidates> listCandidates() {
        Query query = em.createQuery("SELECT c FROM Candidates c");
        return query.getResultList();
    }

    public List<Candidates> listCandidatesForPoiliticGroup(int id) {
        Query query = em.createNamedQuery("Candidates.findByPoliticGroup");
        query.setParameter("id", id);

        List<Candidates> candidates = query.getResultList();

        return candidates;
    }

    public Candidates getCandidatesWithProcess(int id) {
        try {
            Candidates candidate = em.find(Candidates.class, id);

            if (candidate != null) {
                Collection<CandidatesForCities> candidatesForCities = candidate.getCandidatesForCitiesCollection();
                Collection<PresidencialCandidates> procesosPresidencialesCollection = candidate.getPresidencialCandidatesCollection();

                // Obtenemos el proceso por ciudad actual
                List<CandidatesForCities> list;
                if (candidatesForCities instanceof List) {
                    list = (List) candidatesForCities;
                } else {
                    list = new ArrayList(candidatesForCities);
                }

                if (list.size() > 0) {
                    if (!list.get(list.size() - 1).getElectoralProcessId().end()) {
                        candidate.setElectoralProcessActive(list.get(list.size() - 1));
                    }
                }

                // ////////////////////////////////////////////////////////////////////////
                // Obtenemos el proceso presidencial actual
                List<PresidencialCandidates> procesosPresidenciales;

                if (procesosPresidencialesCollection instanceof List) {
                    procesosPresidenciales = (List) procesosPresidencialesCollection;
                } else {
                    procesosPresidenciales = new ArrayList(procesosPresidencialesCollection);
                }

                if (procesosPresidenciales.size() > 0) {
                    if (!procesosPresidenciales.get(procesosPresidenciales.size() - 1).getElectoralProcessId().end()) {
                        candidate.setPresidencialCandidatesActive(procesosPresidenciales.get(procesosPresidenciales.size() - 1));
                    }
                }

                return candidate;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Candidates> listCandidatesForPoiliticGroupWithProcess(int id) {
        Query query = em.createNamedQuery("Candidates.findByPoliticGroup");
        query.setParameter("id", id);

        List<Candidates> candidates = query.getResultList();

        for (Candidates candidate : candidates) {

            Collection<CandidatesForCities> candidatesForCities = candidate.getCandidatesForCitiesCollection();
            Collection<PresidencialCandidates> procesosPresidencialesCollection = candidate.getPresidencialCandidatesCollection();

            // Candidatos por ciudades //////////////////////////////////////////////
            List<CandidatesForCities> list;
            if (candidatesForCities instanceof List) {
                list = (List) candidatesForCities;
            } else {
                list = new ArrayList(candidatesForCities);
            }

            if (list.size() > 0) {
                if (!list.get(list.size() - 1).getElectoralProcessId().end()) {
                    candidate.setElectoralProcessActive(list.get(list.size() - 1));
                }
            }
            // ////////////////////////////////////////////////////////////////////////

            // Candidados presidenciales //////////////////////////////////////////////
            List<PresidencialCandidates> procesosPresidenciales;

            if (procesosPresidencialesCollection instanceof List) {
                procesosPresidenciales = (List) procesosPresidencialesCollection;
            } else {
                procesosPresidenciales = new ArrayList(procesosPresidencialesCollection);
            }

            if (procesosPresidenciales.size() > 0) {
                if (!procesosPresidenciales.get(procesosPresidenciales.size() - 1).getElectoralProcessId().end()) {
                    candidate.setPresidencialCandidatesActive(procesosPresidenciales.get(procesosPresidenciales.size() - 1));
                }
            }
            // ////////////////////////////////////////////////////////////////////////

        }

        return candidates;
    }

    public boolean insertCandidates(Candidates candidates) {
        try {
            em.persist(candidates);
            em.flush();
            System.out.println("Id: " + candidates.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Candidates getCandidates(int id) {
        try {
            Candidates enti = em.find(Candidates.class, id);
            if (enti != null) {
                return enti;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean editCandidates(Candidates candidates) {
        try {
            Candidates enti = em.find(Candidates.class, candidates.getId());
            if (enti != null) {
                enti = candidates;
                em.merge(enti);
                em.flush();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCandidates(int id) {
        try {
            Candidates enti = em.find(Candidates.class, id);
            if (enti != null) {
                if(enti.getCandidatesForCitiesCollection().isEmpty()){
                    if(enti.getPresidencialCandidatesCollection().isEmpty()){
                        em.remove(enti);
                        em.flush();
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
