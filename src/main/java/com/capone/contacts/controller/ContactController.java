package com.capone.contacts.controller;


import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capone.contacts.entity.ContactEntity;
import com.capone.contacts.error.BadInputException;
import com.capone.contacts.model.ContactDto;
import com.capone.contacts.service.ContactService;
import com.capone.contacts.validator.PhoneTypeValidator;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/customer", consumes = "application/json", produces = "application/json")
public class ContactController {

    @Autowired
    ContactService contactService;
    

    @ApiResponses({ @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Internal Server Error") })
    @ApiOperation("List all contacts")
    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ResponseEntity<List<ContactEntity>> getContacts() {
        List<ContactEntity> contactList = contactService.getContacts();
        return new ResponseEntity<>(contactList, HttpStatus.OK);
    }

    @ApiResponses({ @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Internal Server Error") })
    @ApiOperation("Create a new contact")
    @RequestMapping(value = "/contacts", method = RequestMethod.POST)
    public ResponseEntity<String> createContact(@RequestBody ContactDto contactDto) {
    	if(contactDto == null || PhoneTypeValidator.isValid(contactDto.getPhone())) {
    		throw new BadInputException("Contact can not be null");
    	}
    	ContactEntity contactResp = contactService.createContact(contactDto);
    	if(contactResp == null) {
    		return new ResponseEntity<>("Save contact failed", HttpStatus.INTERNAL_SERVER_ERROR);
    	}
        return new ResponseEntity<>("Successfully created the contact", HttpStatus.CREATED);
    }

    @ApiResponses({ @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Internal Server Error") })
    @ApiOperation("Update a contact")
    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ContactDto> updateContact(@RequestBody ContactDto contactDto, @PathParam("id") Long id) {
    	contactDto.setId(id);
    	ContactDto updatedContactDto = contactService.updateContact(contactDto);
        return new ResponseEntity<>(updatedContactDto, HttpStatus.OK);
    }

    @ApiResponses({ @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Internal Server Error") })
    @ApiOperation("Get a specific contact")
    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.GET)
    public ResponseEntity<ContactDto> getContact(@PathParam("id") Long id) {
    	ContactDto contactDto = contactService.getContactById(id);
        return new ResponseEntity<>(contactDto, HttpStatus.OK);
    }

    @ApiResponses({ @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Internal Server Error") })
    @ApiOperation("Delete a contact")
    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteContact(@PathParam("id") Long id) {
    	
    	if(id == null) {
    		throw new BadInputException("Id can not be null");
    	}
    	contactService.deleteContact(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}

