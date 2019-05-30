package com.capone.contacts.controller.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.capone.contacts.controller.ContactController;
import com.capone.contacts.entity.ContactEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ContactsControllerTest {

    @Autowired
    ContactController contactsController;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetContacts() {
        ResponseEntity<List<ContactEntity>> contact = contactsController.getContacts();
        System.out.println(contact);
    }

    

}