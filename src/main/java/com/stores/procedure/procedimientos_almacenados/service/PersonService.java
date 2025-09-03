package com.stores.procedure.procedimientos_almacenados.service;

import com.stores.procedure.procedimientos_almacenados.entity.Person;
import com.stores.procedure.procedimientos_almacenados.exception.PersonNotFoundException;
import com.stores.procedure.procedimientos_almacenados.repository.PersonRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;


    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public List<Person> listadoPersonas() {
        try {
            return personRepository.verPersonas2();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al acceder a la BD" + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public Person findPersona(Integer id) {
        try {

            Person person = personRepository.buscarPersona(id);

            if(person == null){
                throw new PersonNotFoundException("Persona no encontrada con ID: " + id);
            }

            return person;

        } catch (DataAccessException e) {
            throw new RuntimeException("Error al acceder a la BD" + e.getMessage(), e);
        }
    }

    @Transactional
    public void insertarPersona(Person person){
        try {
            personRepository.insertarPersona(person.getName(),person.getLastName());
        }catch (Exception e){
            throw new RuntimeException("No se pudo guardar la persona");
        }
    }

    @Transactional
    public void actualizarPersona(Person person){
        try{
            Person p = personRepository.buscarPersona(person.getId());
            if(p == null){
                throw new PersonNotFoundException("No se encontro dastos de la persona");
            }
            personRepository.actualizarPersona(person.getId(), person.getName(), person.getLastName());
        } catch (Exception e) {
            throw new RuntimeException("No se pudo actualizar la persona");
        }
    }

    public void eliminarPersona(Integer id){
        try{
            personRepository.eliminarPersona(id);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo eliminar la persona");
        }
    }


}
