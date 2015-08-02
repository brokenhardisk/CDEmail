package com.bhd.cd.utils;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bhd.cd.Email;
import com.bhd.cd.SendMail;


/**Utility Class for Hibernate actions
 * @author bhd
 *
 */
public class HibernateUtils {

/**Retrieve Emails from the db based on the range of IDs provided
 * @param myThread
 * @return
 */
public static List<Email> retrievePendingMails(SendMail myThread){
	Session session = SessionUtil.getSessionFactory().openSession();
	Transaction tx = null;
	try {
		tx = session.beginTransaction();
		String hql = "from Email where id BETWEEN :startId AND :endId";
		Query query = session.createQuery(hql);
		query.setParameter("startId",myThread.getThreadStart());
		query.setParameter("endId",myThread.getThreadEnd());
		List<Email> result = query.list();
		if(!result.isEmpty()){
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

/**Retrieve the count of rows of Emails to be sent
 * @return
 */
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
