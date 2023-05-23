package com.portfolio.dmf.Controller;

import com.portfolio.dmf.Dto.dtoPersona;
import com.portfolio.dmf.Entity.Persona;
import com.portfolio.dmf.Security.Controller.Mensaje;
import com.portfolio.dmf.Service.ImpPersonaService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/personas")
@CrossOrigin(origins = {"https://portfolio-web---diego-franco.web.app/","http://localhost:4200"})
public class PersonaController {
        @Autowired
        ImpPersonaService personaService;

        @GetMapping("/lista")
        public ResponseEntity<List<Persona>> list(){
            List<Persona> list = personaService.list();
            return new ResponseEntity(list, HttpStatus.OK);
        }

        @GetMapping("/detail/{id}")
        public ResponseEntity<Persona> getById(@PathVariable("id")int id){
            if(!personaService.existsById(id))
            {
                return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
            }
            Persona persona = personaService.getOne(id).get();
            return new ResponseEntity(persona, HttpStatus.OK);
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopersona){
            if(!personaService.existsById(id)){
                return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
            }
            if(personaService.existsByNombre(dtopersona.getNombre()) && personaService.getByNombre(dtopersona.getNombre()).get().getId() != id){
                return new ResponseEntity(new Mensaje("Nombre ya existente"), HttpStatus.BAD_REQUEST);
            }
            if(StringUtils.isBlank(dtopersona.getNombre())){
                return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
            }
            Persona persona = personaService.getOne(id).get();
            persona.setNombre(dtopersona.getNombre());
            persona.setApellido(dtopersona.getApellido());
            persona.setDescripcion(dtopersona.getDescripcion());
            personaService.save(persona);
            return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
        }

    }

