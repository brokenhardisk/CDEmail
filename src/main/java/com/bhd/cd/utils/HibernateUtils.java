package com.bhd.cd.utils;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bhd.cd.Email;
import com.bhd.cd.SendMail;


public class HibernateUtils {

public static int updateEmail(List<Email> emailList){
		
	Session session = SessionUtil.getSessionFactory().openSession();
	Transaction tx = null;
		try {
			tx = session.beginTransaction();
			int one=1;
			for(Email currEmail:emailList){
				String hql = "update Email set mailSent = :mailSent where id = :id";
				Query query = session.createQuery(hql);
				query.setParameter("mailSent",one);
				query.setParameter("id",currEmail.getId());
				query.executeUpdate();
			}
			tx.commit();
			return 1;
		} catch (Exception e){
			if(tx != null){
				tx.rollback();
			}
			e.printStackTrace();
			return -1;
		} finally {
			session.close();
		}
	}

public static List<Email> retrievePendingMails(SendMail myThread){
	Session session = SessionUtil.getSessionFactory().openSession();
	Transaction tx = null;
	try {
		tx = session.beginTransaction();
		String hql = "from Email where id BETWEEN :startId AND :endId";
		//int zero = 0,one=1;
		Query query = session.createQuery(hql);
		//query.setParameter("mailSent",zero);
		query.setParameter("startId",myThread.getThreadStart());
		query.setParameter("endId",myThread.getThreadEnd());
		//query.setMaxResults(3);
		List<Email> result = query.list();
		if(!result.isEmpty()){
			/*hql = "update Email set mailSent = :mailSent where id BETWEEN :startId AND :endId";
			query = session.createQuery(hql);
			query.setParameter("mailSent",one);
			query.setParameter("startId",myThread.getThreadStart());
			query.setParameter("endId",myThread.getThreadEnd());
			query.executeUpdate();*/
			tx.commit();
			return result;
		}
		return null;
		
	} catch (Exception e){
		if(tx != null){
			tx.rollback();
		}
		e.printStackTrace();
		return null;
	} finally {
		session.close();
	}
	
}

public static int retrieveRowCount(){
	Session session = SessionUtil.getSessionFactory().openSession();
	Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "from Email";
			Query query = session.createQuery(hql);
			int count = (query.list()).size();
			tx.commit();
			return count;
		} catch (Exception e){
			if(tx != null){
				tx.rollback();
			}
			e.printStackTrace();
			return -1;
		} finally {
			session.close();
		}
}
}
