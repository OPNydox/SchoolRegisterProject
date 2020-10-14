package com.example.school.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.school.database.entities.Presence;

public interface PresenceRepository extends CrudRepository<Presence, Long>{

}
