package com.sat.tmf.movietkt.dao;

import org.hibernate.Session;
import org.hibernate.query.Query; // ✅ Hibernate Query
import org.springframework.stereotype.Repository;

import com.sat.tmf.movietkt.entities.Payment;

@Repository
public class PaymentDao extends GenericDao<Payment, Integer> {

    public PaymentDao() {
        super(Payment.class);
    }

    /**
     * Find Payment by Transaction ID
     */
    public Payment findByTxnId(String txnId) {
        Session session = getSession(); // get Hibernate session
        Query<Payment> query = session.createQuery(
                "from Payment where txnId = :txn", Payment.class);
        query.setParameter("txn", txnId);
        return query.uniqueResult(); // ✅ Hibernate's method
    }

    /**
     * Find Payment by associated Booking ID
     */
    public Payment findByBookingId(Integer bookingId) {
        Session session = getSession(); // Hibernate session
        Query<Payment> query = session.createQuery(
                "from Payment where booking.id = :bid", Payment.class);
        query.setParameter("bid", bookingId);
        return query.uniqueResult(); // ✅ Hibernate method, no error
    }
}
