package com.stores.procedure.procedimientos_almacenados.controller;

import com.stores.procedure.procedimientos_almacenados.entity.Person;
import com.stores.procedure.procedimientos_almacenados.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sp")
public class PersonaController {

    private final PersonService personService;

    public PersonaController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public ResponseEntity<?> getPersons() {
        List<Person> p = personService.listadoPersonas();
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<?> buscarPersona(@PathVariable Integer id){
        Person p = personService.findPersona(id);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping("/persons")
    public ResponseEntity<?> insertarPersona(@RequestBody Person person){
            personService.insertarPersona(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/persons")
    public ResponseEntity<?> actulizarPersona(@RequestBody Person person){
        personService.actualizarPersona(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<?> eliminarPersona(@PathVariable Integer id){
        personService.eliminarPersona(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
