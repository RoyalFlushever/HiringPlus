package com.tdt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdt.model.Contact;

@Repository
public interface ContactDao extends JpaRepository<Contact, Long>{

}
