package com.example.contactform.repository;

import com.example.contactform.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
