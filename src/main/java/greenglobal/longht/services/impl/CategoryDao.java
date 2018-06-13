package greenglobal.longht.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import greenglobal.longht.entity.Category;

@Repository
public class CategoryDao {
	@PersistenceContext
	private EntityManager em;
	@Transactional
	public List<Category> listAll(){
		return em.createQuery("FROM Category",Category.class).getResultList();
	}
	
}
