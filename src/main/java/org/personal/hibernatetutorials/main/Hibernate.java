package org.personal.hibernatetutorials.main;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.personal.hibernatetutorials.entity.Address;
import org.personal.hibernatetutorials.entity.Student;

public class Hibernate {

	private static final SessionFactory sf = new Configuration().configure().buildSessionFactory();
	private final static Logger logger = Logger.getLogger(Hibernate.class);

	private static void closeSession(Session session) {
		session.getTransaction().commit();
		session.close();
	}

	private static Session openSession() {
		Session session = sf.openSession();
		session.beginTransaction();
		return session;
	}

	public static void insert() {
		Session session = openSession();
		Student student1 = new Student();
		student1.setFname("nischal");
		student1.setLname("shakya");
		Address address1 = new Address("kathmandu", "jumla");
		student1.setAddress(address1);
		session.save(student1);
		closeSession(session);
	}

	public static void update() {
		Session session = openSession();
		Student studentUpdate = session.get(Student.class, 1);		
		if (studentUpdate != null) {
			studentUpdate.setFname("rashik");
			studentUpdate.setLname("shakya");
			studentUpdate.getAddress().setPermanentAddress("jumla");
			studentUpdate.getAddress().setTemporaryAddress("kathmandu");
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

	@SuppressWarnings("rawtypes")
	public static void display() {
		Session session = openSession();
		Query query = session.createQuery("from Student");
		@SuppressWarnings("unchecked")
		List<Student> studentList = query.getResultList();
		logger.info("ID\t" + "FirstName\t" + "LastName\t" + "PermanentAddress\t" + "TemporaryAddress");
		studentList.stream().forEach((listOfStudent) -> {
			logger.info(listOfStudent.getId() + "\t" + listOfStudent.getFname() + "\t\t" + listOfStudent.getLname()
					+ "\t\t" + listOfStudent.getAddress().getPermanentAddress() + "\t\t\t"
					+ listOfStudent.getAddress().getTemporaryAddress());
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
