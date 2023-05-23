package com.portfolio.dmf.Controller;

import com.portfolio.dmf.Dto.dtoSkills;
import com.portfolio.dmf.Entity.Skills;
import com.portfolio.dmf.Security.Controller.Mensaje;
import com.portfolio.dmf.Service.SSkills;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://portfolio-web---diego-franco.web.app/","http://localhost:4200"})
@RequestMapping("/skill")
public class CSkills {

    @Autowired
    SSkills SSkills;

    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list()
    {
        List<Skills> list = SSkills.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") int id)
    {
        if (!SSkills.existsById(id))
        {
            return new ResponseEntity(new Mensaje("No existe aun"), HttpStatus.NOT_FOUND);
        }
        Skills hYs = SSkills.getOne(id).get();
        return new ResponseEntity(hYs, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id)
    {
        if (!SSkills.existsById(id))
        {
            return new ResponseEntity(new Mensaje("No existe aun"), HttpStatus.NOT_FOUND);
        }
        SSkills.delete(id);
        return new ResponseEntity(new Mensaje("Skill ha sido eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoSkills dtoskills)
    {
        if (StringUtils.isBlank(dtoskills.getNombre()))
        {
            return new ResponseEntity(new Mensaje("Nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (SSkills.existsByNombre(dtoskills.getNombre()))
        {
            return new ResponseEntity(new Mensaje("Skill ya existente"), HttpStatus.BAD_REQUEST);
        }
        Skills hYs = new Skills(dtoskills.getNombre(), dtoskills.getPorcentaje());
        SSkills.save(hYs);
        return new ResponseEntity(new Mensaje("Skill ha sido agregada"), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoSkills dtohys)
    {
        //Validacion ID existente
        if (!SSkills.existsById(id))
        {
            return new ResponseEntity(new Mensaje("ID no existe aun"), HttpStatus.BAD_REQUEST);
        }
        //Compara nombre de skills
        if (SSkills.existsByNombre(dtohys.getNombre()) && SSkills.getByNombre(dtohys.getNombre()).get()
                .getId() != id)
        {
            return new ResponseEntity(new Mensaje("Skill ya existente"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtohys.getNombre()))
        {
            return new ResponseEntity(new Mensaje("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        }
        Skills hYs = SSkills.getOne(id).get();
        hYs.setNombre(dtohys.getNombre());
        hYs.setPorcentaje(dtohys.getPorcentaje());
        SSkills.save(hYs);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
    }
}
