package com.capone.contacts.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capone.contacts.entity.ContactEntity;

@Repository
@Transactional
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    
}