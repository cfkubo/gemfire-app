package com.example.gemfireapp.controller;

import com.example.gemfireapp.model.Person;
import com.example.gemfireapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * REST Controller for managing Person objects in GemFire.
 * Exposes API endpoints for creating (putting) and retrieving (getting) Person data.
 */
@RestController // Marks this class as a REST Controller
@RequestMapping("/persons") // Base path for all endpoints in this controller
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired // Injects the PersonRepository
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Handles PUT requests to store a Person object in GemFire.
     *
     * @param person The Person object to store (JSON body).
     * @return The stored Person object with HTTP status 200 OK.
     */
    @PutMapping // Maps HTTP PUT requests to this method
    public ResponseEntity<Person> putPerson(@RequestBody Person person) {
        // Saves the person object to the "People" Region in GemFire
        Person savedPerson = personRepository.save(person);
        System.out.println("Saved Person: " + savedPerson); // Log for verification
        return ResponseEntity.ok(savedPerson);
    }

    /**
     * Handles GET requests to retrieve a Person object by its ID from GemFire.
     *
     * @param id The ID of the person to retrieve (path variable).
     * @return The retrieved Person object with HTTP status 200 OK, or 404 Not Found if not found.
     */
    @GetMapping("/{id}") // Maps HTTP GET requests with an ID path variable to this method
    public ResponseEntity<Person> getPerson(@PathVariable String id) {
        // Retrieves the person object from the "People" Region by ID
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            System.out.println("Retrieved Person: " + person.get()); // Log for verification
            return ResponseEntity.ok(person.get());
        } else {
            System.out.println("Person with ID " + id + " not found."); // Log for verification
            return ResponseEntity.notFound().build(); // Return 404 Not Found
        }
    }
}
