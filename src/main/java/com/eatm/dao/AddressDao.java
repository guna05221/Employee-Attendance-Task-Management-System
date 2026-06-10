package com.eatm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eatm.entity.Address;

@Repository
public class AddressDao {
	@Autowired
	EntityManager entitymanager;
	
	@Autowired
	EntityTransaction entitytransaction;
	
	// add
	public Address saveAddress(Address address) {

		entitytransaction.begin();

		entitymanager.persist(address);

		entitytransaction.commit();

		return address;
	}

	// update
	public Address updateAddress(Address address) {

		entitytransaction.begin();

		entitymanager.merge(address);

		entitytransaction.commit();

		return address;
	}

	// delete
	public Address deleteAddress(Address address) {

		entitytransaction.begin();

		entitymanager.remove(address);

		entitytransaction.commit();

		return address;
	}

	// find Address by id
	public Address findAddressById(int addressId) {

		return entitymanager.find(Address.class, addressId);
	}

	// find all Address
	public List<Address> findAllAddress() {

		Query query = entitymanager.createQuery("SELECT e FROM Address e");

		return query.getResultList();
	}
}
