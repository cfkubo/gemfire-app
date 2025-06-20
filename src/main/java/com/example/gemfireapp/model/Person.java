package com.example.gemfireapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.io.Serializable;

/**
 * Domain class representing a Person to be stored in a GemFire Region.
 *
 * The @Region annotation maps this class to a GemFire Region named "People".
 * The @Id annotation marks the 'id' field as the cache key.
 */
@Region("People") // Specifies the GemFire Region name for this entity
public class Person implements Serializable {

    @Id // Marks this field as the cache key
    private String id;
    private String name;
    private int age;

    // Default constructor for serialization/deserialization
    public Person() {
    }

    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", age=" + age +
               '}';
    }
}
