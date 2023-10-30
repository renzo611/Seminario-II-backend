package com.seminario.backend.repository;

import com.seminario.backend.models.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface ContactRepository extends CrudRepository<Contact, Integer> {
    boolean existsByEmail(String email);
}
