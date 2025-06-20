package com.example.gemfireapp.repository;

import com.example.gemfireapp.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data GemFire Repository for the Person entity.
 * Provides basic CRUD (Create, Read, Update, Delete) operations.
 *
 * Extends CrudRepository, where 'Person' is the entity type and 'String' is the type of its ID.
 */
@Repository // Marks this interface as a Spring Data Repository
public interface PersonRepository extends CrudRepository<Person, String> {
    // Spring Data will automatically implement basic CRUD methods like save, findById, findAll, deleteById.
    // You can add custom query methods here if needed, following Spring Data conventions.
    // e.g., List<Person> findByName(String name);
}
