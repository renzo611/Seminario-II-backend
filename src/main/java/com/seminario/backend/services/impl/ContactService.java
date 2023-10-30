package com.seminario.backend.services.impl;

import com.seminario.backend.dto.ContactRequestDto;
import com.seminario.backend.dto.ContactResponseDto;
import com.seminario.backend.dto.GeneralResponseDto;
import com.seminario.backend.exception.GeneralException;
import com.seminario.backend.models.Contact;
import com.seminario.backend.models.Users;
import com.seminario.backend.repository.ContactRepository;
import com.seminario.backend.repository.UserRepository;
import com.seminario.backend.services.IContactService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service()
@RequiredArgsConstructor()
public class ContactService implements IContactService {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    @Override
    public GeneralResponseDto createContact(ContactRequestDto contactRequestDto) throws GeneralException {
        Users user = this.userRepository.findById(contactRequestDto.getUserId()).orElseThrow(() -> new GeneralException("The user don't exist",403));
        boolean existContact = this.contactRepository.existsByEmail(contactRequestDto.getEmail());

        if(existContact){
            throw new GeneralException("The contact exist", 404);
        }

        Contact contact = this.contactRepository.save(new Contact(
                contactRequestDto.getName(),
                contactRequestDto.getEmail(),
                contactRequestDto.getPhone(),
                user
        ));

        if(contact == null || contact.getId() <= 0 ){
            throw new GeneralException("Error saving new contact", 500);
        }

        return new GeneralResponseDto(201, "Contact created");
    }

    @Override
    public List<ContactResponseDto> getContactsByUser(Integer userId) throws GeneralException {
        Users user = this.userRepository.findById(userId).orElseThrow(() -> new GeneralException("The user don't exist", 403));

        Set<Contact> contacts = user.getContacts();

        return contacts.stream().map( contact ->
                new ContactResponseDto(
                    contact.getId(),
                    contact.getName(),
                    contact.getEmail(),
                    contact.getPhone()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public GeneralResponseDto updateContact(Integer contactId, ContactRequestDto contactRequestDto) throws GeneralException {
        Contact contact = this.contactRepository.findById(contactId).orElseThrow(() -> new GeneralException("The contact doesn't exist", 403));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        modelMapper.map(contactRequestDto, contact);

        this.contactRepository.save(contact);
        return new GeneralResponseDto(200, "Contact updated");
    }

    @Override
    public GeneralResponseDto deleteContact(Integer contactId) throws GeneralException {
        Contact contact = this.contactRepository.findById(contactId).orElseThrow(() -> new GeneralException("The contact doesn't exist", 403));

        contact.setDeleatAt(true);
        this.contactRepository.save(contact);

        return new GeneralResponseDto(200 ,"Contact deleted");
    }
}
