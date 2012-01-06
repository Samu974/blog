package edu.ecm.blog.service;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ecm.blog.domain.Post;

@Service
public class PostServiceImpl implements PostService {

	@Inject
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.ecm.blog.service.PostService#save(edu.ecm.blog.domain.Post)
	 */
	@Override
	@Transactional
	public void save(Post post) {
		Session session = sessionFactory.getCurrentSession();
		session.save(post);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.ecm.blog.service.PostService#delete(java.lang.Long)
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("delete from Post where id = :id")
				.setLong("id", id).executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.ecm.blog.service.PostService#find(int, int)
	 */
	@Override
	@Transactional
	public List find(int pageIndex, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Post.class);
		criteria.setFirstResult(pageIndex * pageSize);
		criteria.setMaxResults(pageSize);
		List crit = criteria.list();
		return crit;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.ecm.blog.service.PostService#count()
	 */
	@Override
	@Transactional
	public int count() {
		Session session = sessionFactory.getCurrentSession();
		Long a = (Long) session.createQuery("Select count(*) from Post")
				.uniqueResult();
		return a.intValue();
	}

	@Override
	@Transactional
	public Post findBySlug(String slug) {
		Session session = sessionFactory.getCurrentSession();
		return (Post) session.createQuery("from Post where slug =:slug")
				.setString("slug", slug).uniqueResult();
		
	}
	
	@Override
	@Transactional
	public Post findById(Long id){
		Session session = sessionFactory.getCurrentSession();
		return (Post) session.createQuery("from Post where id =:id")
				.setLong("id", id).uniqueResult();
	}
	
}