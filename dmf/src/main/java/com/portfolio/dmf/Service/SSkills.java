package com.portfolio.dmf.Service;

import com.portfolio.dmf.Entity.Skills;
import com.portfolio.dmf.Repository.RSkills;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class SSkills {
    @Autowired
    RSkills RSkills;

    public List<Skills> list(){
        return RSkills.findAll();
    }

    public Optional<Skills> getOne(int id){
        return RSkills.findById(id);
    }

    public Optional<Skills> getByNombre(String nombre){
        return RSkills.findByNombre(nombre);
    }

    public void save(Skills skill){
        RSkills.save(skill);
    }

    public void delete(int id){
        RSkills.deleteById(id);
    }

    public boolean existsById(int id){
        return RSkills.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return RSkills.existsByNombre(nombre);
    }
}

