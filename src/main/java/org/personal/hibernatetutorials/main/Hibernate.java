package org.personal.hibernatetutorials.main;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.personal.hibernatetutorials.entity.Address;
import org.personal.hibernatetutorials.entity.Student;

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
		Address addressOne = new Address("kathmandu", "mustang");

		Student studentInsert = new Student();
		studentInsert.setFname("Nischal");
		studentInsert.setLname("Shakya");
		studentInsert.setAddress(addressOne);
		addressOne.getStudent().add(studentInsert);
		session.save(addressOne);
		closeSession(session);
	}

	public static void update() {
		final Session session = openSession();
		Student studentUpdate = session.get(Student.class, 1);
		if (studentUpdate != null) {
			studentUpdate.setFname("rashik");
			studentUpdate.setLname("shakya");
			studentUpdate.getAddress().setPermanentAddress("usa");
			studentUpdate.getAddress().setTemporaryAddress("florida");
			session.update(studentUpdate);
		}
		closeSession(session);
	}

	public static void delete() {
		final Session session = openSession();
		Student studentDelete = session.get(Student.class, 1);
		if (studentDelete != null) {
			session.delete(studentDelete);
		}
		closeSession(session);
	}

	public static void displayFromStudent() {
		final Session session = openSession();
		@SuppressWarnings("unchecked")
		List<Student> studentList = session.createQuery("from Student").getResultList();
		studentList.stream().forEach((listOfStudent) -> {
			LOGGER.info(listOfStudent.getId() + TAB + listOfStudent.getFname() + TAB + listOfStudent.getLname() + TAB
					+ listOfStudent.getAddress().getPermanentAddress() + TAB
					+ listOfStudent.getAddress().getTemporaryAddress());
		});

	}

	@SuppressWarnings("unchecked")
	public static void displayFromAddress() {
		final Session session = openSession();
		List<Address> addressList = session.createQuery("from Address").getResultList();
		addressList.stream().forEach((listOfAddress) -> {
			listOfAddress.getStudent().stream().forEach((listOfStudent) -> {
				LOGGER.info(listOfAddress.getId() + TAB + listOfAddress.getPermanentAddress() + TAB
						+ listOfAddress.getTemporaryAddress() + TAB + listOfStudent.getId() + TAB
						+ listOfStudent.getFname() + TAB + listOfStudent.getLname());
			});
		});
	}

	public static void main(String args[]) {
		insert();
		displayFromStudent();
		displayFromAddress();
		update();
		displayFromAddress();
		delete();
		displayFromStudent();
	}

}
