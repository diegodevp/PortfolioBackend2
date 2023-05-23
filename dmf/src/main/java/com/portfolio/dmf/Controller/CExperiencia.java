package com.portfolio.dmf.Controller;

import com.portfolio.dmf.Dto.dtoExperiencia;
import com.portfolio.dmf.Entity.Experiencia;
import com.portfolio.dmf.Security.Controller.Mensaje;
import com.portfolio.dmf.Service.SExperiencia;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/explab")
@CrossOrigin(origins = {"https://portfolio-web---diego-franco.web.app/","http://localhost:4200"})
public class CExperiencia {
        @Autowired
        SExperiencia sExperiencia;

        @GetMapping("/lista")
        public ResponseEntity<List<Experiencia>> list()
        {
            List<Experiencia> list = sExperiencia.list();
            return new ResponseEntity(list, HttpStatus.OK);
        }

        @GetMapping("/detail/{id}")
        public ResponseEntity<Experiencia> getById(@PathVariable("id") int id)
        {
            if(!sExperiencia.existsById(id))
                return new ResponseEntity(new Mensaje("Aun no existe"), HttpStatus.NOT_FOUND);
            Experiencia experiencia = sExperiencia.getOne(id).get();
            return new ResponseEntity(experiencia, HttpStatus.OK);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> delete(@PathVariable("id") int id)
        {
            if (!sExperiencia.existsById(id))
            {
                return new ResponseEntity(new Mensaje("Aun no existe"), HttpStatus.NOT_FOUND);
            }
            sExperiencia.delete(id);
            return new ResponseEntity(new Mensaje("Se ha eliminado"), HttpStatus.OK);
        }

        @PostMapping("/create")
        public ResponseEntity<?> create(@RequestBody dtoExperiencia dtoexp)
        {
            if(StringUtils.isBlank(dtoexp.getNombreE()))
                return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
            if(sExperiencia.existsByNombreE(dtoexp.getNombreE()))
                return new ResponseEntity(new Mensaje("La experiencia ya existe"), HttpStatus.BAD_REQUEST);

            Experiencia experiencia = new Experiencia(dtoexp.getNombreE(), dtoexp.getDescripcionE());
            sExperiencia.save(experiencia);
            return new ResponseEntity(new Mensaje("Experiencia ha sido agregada"), HttpStatus.OK);
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperiencia dtoexp){
            //Validacion ID existente
            if(!sExperiencia.existsById(id))
                return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
            //Comparacion de la experiencia con su nombre
            if(sExperiencia.existsByNombreE(dtoexp.getNombreE()) && sExperiencia.getByNombreE(dtoexp.getNombreE()).get().getId() != id)
                return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
            //Campo obligatorio
            if(StringUtils.isBlank(dtoexp.getNombreE()))
                return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

            Experiencia experiencia = sExperiencia.getOne(id).get();
            experiencia.setNombreE(dtoexp.getNombreE());
            experiencia.setDescripcionE((dtoexp.getDescripcionE()));
            sExperiencia.save(experiencia);
            return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
        }
    }

