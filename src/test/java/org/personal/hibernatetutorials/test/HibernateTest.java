package org.personal.hibernatetutorials.test;

import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.personal.hibernatetutorials.entity.Student;

public class HibernateTest {

	private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	private Session openSession() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		return session;
	}

	private void closeSession(Session session) {
		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void insertTest() {
		Session session = openSession();
		Student student = new Student("nischal", "shakya", "dallu");
		session.save(student);
		closeSession(session);
	}

	@Test
	public void updateTest() {
		Session session = openSession();
		Student student = session.get(Student.class, 1);
		
		assertEquals(1, student.getId());
		assertEquals("nischal", student.getFname());
		assertEquals("shakya", student.getLname());
		assertEquals("dallu", student.getAddress());

		student.setFname("rashik");
		student.setLname("maharjan");
		student.setAddress("kathmandu");
		session.update(student);
		closeSession(session);

	}

}
