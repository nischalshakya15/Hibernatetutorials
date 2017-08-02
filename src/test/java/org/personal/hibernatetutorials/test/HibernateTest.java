package org.personal.hibernatetutorials.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
		Student student = findOne(session);

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

	@Test
	public void deleteTest() {
		Session session = openSession();
		Student student = findOne(session);
		if (student != null) {
			session.delete(student);
		}
		closeSession(session);
	}

	@Test
	public void displayTest() {
		Session session = openSession();
		@SuppressWarnings("unchecked")
		List<Student> listOfStudent = session.createQuery("from Student").getResultList();
		listOfStudent.stream().forEach((studentList) -> {
			assertEquals(1, studentList.getId());
			assertEquals("nischal", studentList.getFname());
			assertEquals("shakya", studentList.getLname());
			assertEquals("dallu", studentList.getAddress());
		});
	}

	private Student findOne(Session session) {
		return session.get(Student.class, 1);
	}

}
