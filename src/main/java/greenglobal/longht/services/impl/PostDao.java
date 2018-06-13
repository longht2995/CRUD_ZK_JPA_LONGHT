package greenglobal.longht.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import greenglobal.longht.entity.Post;
import greenglobal.longht.entity.PostSearch;

@Repository
public class PostDao {
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	@Transactional
	public List<Post> listAll(){
		return em.createQuery("SELECT p FROM Post p Order by p.id DESC",Post.class).getResultList();
	}
	@Transactional
	public void addPost(Post p) {
		if(p.getId()!=0) {
			em.merge(p);
		}else {
			em.persist(p);
		}
		
	}
	public List<Post> findBy(PostSearch p){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> cq = cb.createQuery(Post.class);
		Root<Post> from = cq.from(Post.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(p.getKey()!=null) {
			Predicate condition = cb.like(from.<String>get("content"),"%"+p.getKey()+"%");
			predicates.add(condition);
		}
		
		if(p.getCategory()!=null) {
			System.out.println(p.getCategory().getId());
			Predicate condition = cb.equal(from.<String>get("category"), p.getCategory());
			predicates.add(condition);
		}
		if(p.getDatestart()!=null && p.getDateend()==null) {
			Predicate condition = cb.greaterThanOrEqualTo(from.<Date>get("date"), p.getDatestart());
			predicates.add(condition);
		}else if(p.getDatestart()==null && p.getDateend()!=null) {
			Predicate condition = cb.lessThanOrEqualTo(from.<Date>get("date"), p.getDatestart());
			predicates.add(condition);
		}else if(p.getDatestart()!=null && p.getDateend()!=null) {
			Predicate condition = cb.between(from.<Date>get("date"), p.getDatestart(), p.getDateend());
			predicates.add(condition);
		}
		
		cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		cq.select(from);
		cq.orderBy(cb.desc(from.get("id")));
		return em.createQuery(cq).getResultList();
	}
	@Transactional
	public void delete(Post post) {
		em.remove(post);
	}
}
