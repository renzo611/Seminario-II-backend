package com.seminario.backend.controller;

import com.seminario.backend.dto.ContactRequestDto;
import com.seminario.backend.dto.ContactResponseDto;
import com.seminario.backend.dto.GeneralResponseDto;
import com.seminario.backend.exception.GeneralException;
import com.seminario.backend.services.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController()
@RequiredArgsConstructor()
@RequestMapping("/contact")
public class ContactController {
    private final IContactService iContactService;

    @PostMapping("/create")
    public ResponseEntity<GeneralResponseDto> createNewContact(@RequestBody() ContactRequestDto contactRequestDto) throws GeneralException {
        return new ResponseEntity<>(this.iContactService.createContact(contactRequestDto), CREATED);
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<ContactResponseDto>> getContactsByUser(@PathVariable Integer userId) throws GeneralException {
        return new ResponseEntity<>(this.iContactService.getContactsByUser(userId), OK);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<GeneralResponseDto> updateContact(@PathVariable Integer contactId, @RequestBody ContactRequestDto contactRequestDto) throws GeneralException {
        return new ResponseEntity<>(this.iContactService.updateContact(contactId, contactRequestDto), OK);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<GeneralResponseDto> deleteContact(@PathVariable Integer contactId) throws GeneralException {
        return new ResponseEntity<>(this.iContactService.deleteContact(contactId), OK);
    }
}
