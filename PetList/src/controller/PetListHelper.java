package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Pets;

/**
 * @author stephaniesink - sisink CIS175 - Spring 2022 Feb 8, 2023
 */
public class PetListHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PetList");

	public void insertPet(Pets li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}

	public List<Pets> showAllPets() {
		EntityManager em = emfactory.createEntityManager();
		@SuppressWarnings("unchecked")
		List<Pets> allPets = em.createQuery("SELECT i from Pets i").getResultList();
		return allPets;

	}

	public void deletePet(Pets toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pets> typedQuery = em.createQuery("select li from Pets li where li.animal = :selectedAnimal and li.breed = :selectedBreed", Pets.class);

		typedQuery.setParameter("selectedAnimal", toDelete.getAnimal());
		typedQuery.setParameter("selectedBreed", toDelete.getBreed());

		typedQuery.setMaxResults(1);

		Pets result = typedQuery.getSingleResult();

		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public Pets searchForPetById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		Pets found = em.find(Pets.class, idToEdit);
		em.close();
		return found;
	}
	
	public void updatePet(Pets toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public List<Pets> searchForPetByAnimal(String animalType) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pets> typedQuery = em.createQuery("select li from Pets li where li.animal = :selectedAnimal", Pets.class);
		
		typedQuery.setParameter("selectedAnimal", animalType);
		
		List<Pets> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<Pets> searchForPetByBreed(String breedName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pets> typedQuery = em.createQuery("select li from Pets li where li.breed = :selectedBreed", Pets.class);
		
		typedQuery.setParameter("selectedBreed", breedName);
		
		List<Pets> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public void cleanUp() {
		emfactory.close();
	}

}
