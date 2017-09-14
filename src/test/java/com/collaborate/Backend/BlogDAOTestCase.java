package com.collaborate.Backend;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.collaborate.DAO.BlogDAO;
import com.collaborate.Model.Blog;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class BlogDAOTestCase {

	static BlogDAO blogDAO;
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext annotationConfigAppContext=new AnnotationConfigApplicationContext();
		annotationConfigAppContext.scan("com.collaborate");
		annotationConfigAppContext.refresh();
		blogDAO=(BlogDAO)annotationConfigAppContext.getBean("blogDAO");
	}
	
@Test
	
	public void createBlogTest()
	{
		
	Blog blog=new Blog();	
	
	blog.setBlogId(1000);
	blog.setBlogName("Sai Mahesh");
	blog.setBlogContent("Sai Is Thop");
	blog.setUsername("sai.mahesh246@gmail.com");
	blog.setStatus("NA");
	blog.setCreateDate(new java.util.Date());
	blog.setLikes(0);
	
	assertTrue("problem in blog Creation",blogDAO.createBlog(blog));
	}
	
	
}
