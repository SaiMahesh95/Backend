package com.collaborate.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.collaborate.Model.Forum;

@Repository("forumDAO")
public class ForumDaoImpl implements ForumDao 
{
	
	@Autowired
	SessionFactory sessionFactory;
	
	public ForumDaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	@Transactional
	public boolean createForum(Forum forum)
	{
	try
	{
		sessionFactory.getCurrentSession().saveOrUpdate(forum);
		return true;
	}
	catch(Exception e)
	{
		System.out.println("Exception Arised"+e);
		return false;
	}
	
}
	@Transactional
	public Forum getForum(int forumId) {
		
		try
		{
			 return  (Forum)sessionFactory.getCurrentSession().createQuery("from Forum where forumId="+forumId);
	    }
		catch(Exception e)
		{
			 System.out.println("Exception Arised"+e);
			 return null;
		}
	}
	@Transactional
	public List<Forum> getForum() {
		
		 @SuppressWarnings("unchecked")
		List<Forum> listForum = (List<Forum>) sessionFactory.getCurrentSession().createQuery("from Forum");
		 return listForum;
	}
	@Transactional
	public boolean approveForum(Forum forum) {
		
		try
		{
			forum.setStatus("A");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised"+e);
			return false;
		}
	}
	@Transactional
	public boolean editForum(int forumId) {
		
		try
		{
			Forum forum =new Forum();
			@SuppressWarnings("rawtypes")
			Query query = (Query) sessionFactory.getCurrentSession().createQuery("update Forum set  forumName=?,forumContent=?,status=?,userid=?,createDate=?,forumId=? where forumId="+forumId);
			  
			  query.setParameter(0,"forum.getForumName()");
			  query.setParameter(1,"forum.getForumContent()");
			  query.setParameter(2,"forum.getStatus()");
			  query.setParameter(3,forum.getUserId());
			  query.setParameter(4,forum.getCreateDate());
			  query.setParameter(5,forum.getForumId());
			  query.executeUpdate();
			return true;
		}
		catch(Exception e)
		{
				System.out.println("Exception Arised"+e);
			 return false;
		}
	}
	@Transactional
	public boolean deleteForum(int forumId) {
		
		try
		{
			
		Query query =	(Query)sessionFactory.getCurrentSession().createQuery("delete Forum where forumId="+forumId);
		query.executeUpdate();	
		return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised"+e);
			return false;
		}
	}
	
	
}