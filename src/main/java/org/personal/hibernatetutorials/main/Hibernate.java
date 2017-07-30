package org.personal.hibernatetutorials.main;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.personal.hibernatetutorials.entity.DateAndTime;

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
		Date now = new Date();
		DateAndTime date = new DateAndTime();
		date.setDate(now);
		date.setTime(now);
		date.setDateTime(now);
		session.save(date);
		closeSession(session);
	}
	
	@SuppressWarnings("unchecked")
	public static void display(){
		final Session session = openSession();
		List<DateAndTime> dateAndTimeList = session.createQuery("from DateAndTime").getResultList();
		dateAndTimeList.stream().forEach((listOfDateAndTime)->{
			LOGGER.info(listOfDateAndTime.getId() +TAB+ listOfDateAndTime.getDate() +TAB+ listOfDateAndTime.getTime() +TAB+ listOfDateAndTime.getDateTime());
		});
	}

	public static void main(String args[]) {
		insert();	
		display();
	}

}
