package org.personal.hibernatetutorials.main;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.personal.hibernatetutorials.entity.Address;
import org.personal.hibernatetutorials.entity.Student;

public class Hibernate {

	private static final SessionFactory sf = new Configuration().configure().buildSessionFactory();
	private final static Logger logger = Logger.getLogger(Hibernate.class);
	private final static String tab = "\t";

	private static Session openSession() {
		Session session = sf.openSession();
		session.beginTransaction();
		return session;
	}
	
	private static void closeSession(Session session) {
		session.getTransaction().commit();
		session.close();
	}

	public static void insert() {
		Session session = openSession();
		Address addressOne = new Address("kathmandu", "mustang");
		Address addressTwo = new Address("pokhara", "butwal");
		Student studentInsert = new Student();
		studentInsert.setFname("Nischal");
		studentInsert.setLname("Shakya");
		studentInsert.getListofAddress().add(addressOne);
		studentInsert.getListofAddress().add(addressTwo);
		session.save(studentInsert);
		closeSession(session);
	}

	public static void update() {
		Session session = openSession();
		Student studentUpdate = session.get(Student.class, 1);
		if (studentUpdate != null) {
			studentUpdate.setFname("rashik");
			studentUpdate.setLname("shakya");
			Address studentAddress = studentUpdate.getListofAddress().get(0);
			studentAddress.setPermanentAddress("patan");
			studentAddress.setTemporaryAddress("kathmandu");
			studentUpdate.getListofAddress().add(studentAddress);
			session.update(studentUpdate);
		}
		closeSession(session);
	}

	public static void delete() {
		Session session = openSession();
		Student studentDelete = session.get(Student.class, 1);
		if (studentDelete != null) {
			session.delete(studentDelete);
		}
		closeSession(session);
	}

	public static void display() {
		Session session = openSession();
		@SuppressWarnings("unchecked")
		List<Student> studentList = session.createQuery("from Student").getResultList();
		studentList.stream().forEach((listOfStudent) -> {
			listOfStudent.getListofAddress().stream().forEach(listOfAddress -> {
				logger.info(listOfStudent.getId() + tab + listOfStudent.getFname() + tab + listOfStudent.getLname()
						+ tab + listOfAddress.getPermanentAddress() + tab + listOfAddress.getTemporaryAddress());
			});
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
