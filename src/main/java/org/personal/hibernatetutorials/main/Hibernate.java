package org.personal.hibernatetutorials.main;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.personal.hibernatetutorials.entity.Student;
import org.personal.hibernatetutorials.entity.Subject;

public class Hibernate {

	private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
	private final static Logger LOGGER = Logger.getLogger(Hibernate.class);
	private final static String TAB = "\t";

	private static Session openSession() {
		final Session session = SESSION_FACTORY.openSession();
		session.beginTransaction();
		return session;
	}

	private static void closeSession(Session session) {
		session.getTransaction().commit();
		session.close();
	}

	public static void insert() {
		final Session session = openSession();
		Student student = new Student("Nischal", "Shakya");
		Subject subjectOne = new Subject("Computer Science");
		Subject subjectTwo = new Subject("Math");
		student.getSubject().add(subjectOne);
		student.getSubject().add(subjectTwo);
		session.save(student);
		closeSession(session);
	}

	public static void display() {
		final Session session = openSession();
		@SuppressWarnings("unchecked")
		List<Student> studentList = session.createQuery("from Student").getResultList();
		studentList.stream().forEach((listOfStudent) -> {
			listOfStudent.getSubject().stream().forEach((listOfSubject) -> {
				LOGGER.info(listOfStudent.getId() + TAB + listOfStudent.getFname() + TAB + listOfStudent.getLname()
						+ TAB + listOfSubject.getId() + TAB + listOfSubject.getSubjectname());
			});
		});

	}

	public static void main(String args[]) {
		insert();
		display();

	}

}
