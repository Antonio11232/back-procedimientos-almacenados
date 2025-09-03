package com.stores.procedure.procedimientos_almacenados.repository;

import com.stores.procedure.procedimientos_almacenados.entity.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Query(value = "CALL verPersonas()", nativeQuery = true)
    List<Person> verPersonas();

    @Procedure(value = "verPersonas")
    List<Person> verPersonas2();

    @Query(value = "CALL buscarPersona(:id)", nativeQuery = true)
    Person buscarPersona(Integer id);

    /*@Query(value = "CALL insertarPersona(:nombre, :apellido)", nativeQuery = true)
    void insertarPersona(@Param("nombre") String nombre,@Param("apellido") String apellido);*/


    //@Query(value = "CALL insertarPersona(:p_name, :p_last_name)", nativeQuery = true)
    @Procedure(value = "insertarPersona")
    void insertarPersona(@Param("p_name") String name, @Param("p_last_name") String lastName);


    @Modifying
    @Query(value = "CALL actualizarPersona(:id, :name, :lastName)", nativeQuery = true)
    void actualizarPersona(Integer id, String name, String lastName);

//   @Procedure(value = "actualizarPersona")
//   void actualizarPersona(Integer p_id, String p_name, String p_last_name);


    //CALL eliminarPersona(6);
    @Procedure(value = "eliminarPersona")
    void eliminarPersona(Integer identificador);


}
