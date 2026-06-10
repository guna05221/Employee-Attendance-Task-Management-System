package com.eatm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eatm.entity.Task;

@Repository
public class TaskDao {

	@Autowired
	EntityManager entitymanager;
	
	@Autowired
	EntityTransaction entitytransaction;
	
	// add
	public Task saveTask(Task task) {
		entitymanager.persist(task);
		entitytransaction.begin();
		entitytransaction.commit();
		return task; 
	}
	
	// update
	public Task updateTask(Task task) {
		entitymanager.merge(task);
		entitytransaction.begin();
		entitytransaction.commit();
		return task; 
	}
	
	// delete
	public Task deleteTask(Task task) {
		entitymanager.remove(task);
		entitytransaction.begin();
		entitytransaction.commit();
		return task; 
	}
	
	// find Task by id
	public Task findTaskById(int taskId) {
		return entitymanager.find(Task.class, taskId);
	}

	// find all Task 
	public List<Task> findAllTaske(){
		Query query = entitymanager.createQuery("SELECT e FROM Task e"); 
		return query.getResultList();
	}
	
}
