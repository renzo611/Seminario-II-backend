package com.seminario.backend.services;

import com.seminario.backend.dto.ContactRequestDto;
import com.seminario.backend.dto.ContactResponseDto;
import com.seminario.backend.dto.GeneralResponseDto;
import com.seminario.backend.exception.GeneralException;

import java.util.List;

public interface IContactService {
    GeneralResponseDto createContact(ContactRequestDto contactRequestDto) throws GeneralException;
    List<ContactResponseDto> getContactsByUser(Integer userId) throws GeneralException;
    GeneralResponseDto updateContact(Integer contactId, ContactRequestDto contactRequestDto) throws GeneralException;
    GeneralResponseDto deleteContact(Integer contactId) throws GeneralException;
}
