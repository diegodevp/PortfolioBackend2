package com.portfolio.dmf.Controller;

import com.portfolio.dmf.Dto.dtoEducacion;
import com.portfolio.dmf.Entity.Educacion;
import com.portfolio.dmf.Security.Controller.Mensaje;
import com.portfolio.dmf.Service.Seducacion;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CEducacion {
    @Autowired
    Seducacion sEducacion;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion>
    getById(@PathVariable("id")int id)
    {
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        Educacion educacion = sEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        sEducacion.delete(id);
        return new ResponseEntity(new Mensaje("Educacion ha sido eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoeducacion)
    {
        if(StringUtils.isBlank(dtoeducacion.getNombreE()))
        {
            return new ResponseEntity(new Mensaje("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(sEducacion.existsByNombreE(dtoeducacion.getNombreE()))
        {
            return new ResponseEntity(new Mensaje("Nombre ya existente"), HttpStatus.BAD_REQUEST);
        }
        Educacion educacion = new Educacion(
                dtoeducacion.getNombreE(), dtoeducacion.getDescripcionE());
        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Educacion ha sido creada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoeducacion)
    {
        if(!sEducacion.existsById(id))
        {
            return new ResponseEntity(new Mensaje("ID no existente"), HttpStatus.NOT_FOUND);
        }
        if(sEducacion.existsByNombreE(dtoeducacion.getNombreE()) && sEducacion.getByNmbreE(dtoeducacion.getNombreE()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Nombre ya existente"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoeducacion.getNombreE())){
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        Educacion educacion = sEducacion.getOne(id).get();
        educacion.setNombreE(dtoeducacion.getNombreE());
        educacion.setDescripcionE(dtoeducacion.getDescripcionE());
        sEducacion.save(educacion);

        return new ResponseEntity(new Mensaje("Educacion ha sido actualizada"), HttpStatus.OK);
    }
}
