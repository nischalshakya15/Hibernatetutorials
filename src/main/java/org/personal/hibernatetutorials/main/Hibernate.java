package org.personal.hibernatetutorials.main;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.personal.hibernatetutorials.entity.Student;

public class Hibernate {

	private static final SessionFactory sf = new Configuration().configure().buildSessionFactory();
	private final static Logger logger = Logger.getLogger(Hibernate.class);

	public static void insert() {
		Session session = openSession();
		session.save(new Student("nischal", "shakya", "dallu"));
		session.save(new Student("ram", "maharjan", "khusibu"));
		session.save(new Student("shyam", "shrestha", "lagan"));
		session.save(new Student("sita", "rai", "chamati"));
		closeSession(session);
	}

	public static void update() {
		Session session = openSession();
		Student studentUpdate = session.get(Student.class, 1);
		System.out.println("student info to be updated");
		System.out.println(studentUpdate.toString());
		if (studentUpdate != null) {
			studentUpdate.setFname("rashik");
			studentUpdate.setLname("shakya");
			studentUpdate.setAddress("bansathali");
			session.update(studentUpdate);
		}
		closeSession(session);
	}

	private static void closeSession(Session session) {
		session.getTransaction().commit();
		session.close();
	}

	private static Session openSession() {
		Session session = sf.openSession();
		session.beginTransaction();
		return session;
	}

	public static void delete() {
		Session session = openSession();
		Student studentDelete = session.get(Student.class, 4);
		System.out.println("student info to be deleted");
		System.out.println(studentDelete.toString());
		if (studentDelete != null) {
			session.delete(studentDelete);
		}
		closeSession(session);
	}

	@SuppressWarnings("rawtypes")
	public static void display() {
		Session session = openSession();
		Query query = session.createQuery("from Student");
		@SuppressWarnings("unchecked")
		List<Student> studentList = query.getResultList();
		logger.info("ID\t" + "FirstName\t" + "LastName\t" + "Address");
		studentList.stream().forEach((listOfStudent) -> {
			logger.info(listOfStudent.getId() + "\t" + listOfStudent.getFname() + "\t\t" + listOfStudent.getLname() + "\t" + listOfStudent.getAddress());
		});

	}

	public static void main(String args[]) {
		insert();
		display();
		update();
		display();
		delete();
		display();
	}

}
