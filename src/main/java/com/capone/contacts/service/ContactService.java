package com.capone.contacts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capone.contacts.entity.ContactEntity;
import com.capone.contacts.entity.PhoneEntity;
import com.capone.contacts.error.ContactNotFoundException;
import com.capone.contacts.model.Address;
import com.capone.contacts.model.ContactDto;
import com.capone.contacts.model.Name;
import com.capone.contacts.model.Phone;
import com.capone.contacts.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	ContactRepository contactsRepository;

	public List<ContactEntity> getContacts() {
		return contactsRepository.findAll();
	}

	public ContactEntity createContact(ContactDto contactDto) {
		ContactEntity contactEntity = mapDtoToEntity(contactDto);
		ContactEntity savedContactEntity = contactsRepository.save(contactEntity);
		return savedContactEntity;
	}

	public ContactDto updateContact(ContactDto contactDto) {
		Optional<ContactEntity> optionalEntity = contactsRepository.findById(contactDto.getId());
		ContactEntity contactEntity = optionalEntity.get();
		if (contactEntity == null) {
			throw new ContactNotFoundException(contactDto.getId());
		}
		ContactEntity mappedContactEntity = mapDtoToEntity(contactDto);
		mappedContactEntity.setId(contactEntity.getId());
		try {
			return mapEntityToDto(contactsRepository.save(mappedContactEntity));
		} catch (Exception e) {
			throw new ContactNotFoundException(contactDto.getId());
		}
	}

	public ContactDto getContactById(Long id) {
		Optional<ContactEntity> optionalEntity = contactsRepository.findById(id);
		ContactEntity contactEntity = optionalEntity.get();
		try {
			return mapEntityToDto(contactEntity);
		} catch (Exception e) {
			throw new ContactNotFoundException(id);
		}
	}

	public void deleteContact(Long id) {
		try {
			contactsRepository.deleteById(id);
		} catch (Exception e) {
			throw new ContactNotFoundException(id);
		}
	}

	private ContactEntity mapDtoToEntity(ContactDto contactDto) {
		ContactEntity contactEntity = new ContactEntity();
		contactEntity.setFirstName(contactDto.getName().getFirst());
		// map the remaining

		List<PhoneEntity> phoneList = new ArrayList<>();

		for (Phone phone : contactDto.getPhone()) {
			PhoneEntity phoneEntity = new PhoneEntity();
			phoneEntity.setNumber(phone.getNumber());
			phoneEntity.setType(phone.getType());
			phoneList.add(phoneEntity);
		}
		contactEntity.setPhone(phoneList);
		return contactEntity;
	}

	private ContactDto mapEntityToDto(ContactEntity contactEntity) {
		ContactDto contactDto = new ContactDto();
		Name name = new Name();
		Address address = new Address();
		// map the remaining
		List<Phone> phoneList = new ArrayList<Phone>();

		for (PhoneEntity phoneEntity : contactEntity.getPhone()) {
			Phone phone = new Phone();
			phone.setNumber(1234567890);
			phone.setType(phoneEntity.getType());
			phoneList.add(phone);
		}
		contactDto.setPhone(phoneList);
		return contactDto;
	}

}