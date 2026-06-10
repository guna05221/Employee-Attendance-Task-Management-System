package com.eatm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eatm.entity.Attendance;

@Repository
public class AttendanceDao {

	@Autowired
	EntityManager entitymanager;
	
	@Autowired
	EntityTransaction entitytransaction;
	
	// add
	public Attendance saveAttendance(Attendance attendance) {

		entitytransaction.begin();

		entitymanager.persist(attendance);

		entitytransaction.commit();

		return attendance;
	}

	// update
	public Attendance updateAttendance(Attendance attendance) {

		entitytransaction.begin();

		entitymanager.merge(attendance);

		entitytransaction.commit();

		return attendance;
	}

	// delete
	public Attendance deleteAttendance(Attendance attendance) {

		entitytransaction.begin();

		entitymanager.remove(attendance);

		entitytransaction.commit();

		return attendance;
	}

	// find Attendance by id
	public Attendance findAttendanceById(int attendanceId) {

		return entitymanager.find(Attendance.class, attendanceId);
	}

	// find all Attendance
	public List<Attendance> findAllAttendance() {

		Query query = entitymanager.createQuery("SELECT e FROM Attendance e");

		return query.getResultList();
	}

}
