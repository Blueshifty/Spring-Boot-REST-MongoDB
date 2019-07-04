package com.example.withMongo.controllers;

import com.example.withMongo.exception.ResourceNotFoundException;
import com.example.withMongo.models.Pets;
import com.example.withMongo.repositories.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.bson.types.ObjectId;
import javax.validation.Valid;
import java.util.List;


@RestController
public class PetsController {
  
  @Autowired
  private PetsRepository repository;
  
  //@RequestMapping(value = "/", method = RequestMethod.GET)
  @GetMapping("/pets")
  public List<Pets> getAllPets(){
    return repository.findAll();
  }

  //@RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @GetMapping("/pets/{id}")
  public Pets getPetById(@PathVariable("id") ObjectId id){
    return repository.findBy_id(id);
  }
  
  //@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @PutMapping("/pets/{id}")
  public void modifyPetById(@PathVariable("id") ObjectId id, @Valid @RequestBody Pets pets){
    pets.set_id(id);
    repository.save(pets);
  }

  //@RequestMapping(value = "/", method = RequestMethod.POST)
  @PostMapping("/pets")
  public Pets createPet(@Valid @RequestBody Pets pets) {
    pets.set_id(ObjectId.get());    
    repository.save(pets);
    return pets;
  }
  
  //@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @DeleteMapping("/pets/{id}")
  public void deletePet(@PathVariable ObjectId id) {
    repository.delete(repository.findBy_id(id));
  }

}