package com.portfolio.dmf.Interface;

import com.portfolio.dmf.Entity.Persona;

import java.util.List;

public interface IPersonaService {
    //GET Persona list
    public List<Persona> getPersona();

    //Save persona
    public void savePersona(Persona persona);

    //Delete Persona
    public void deletePersona(Long id);

    //Find Persona
    public Persona findPersona(Long id);
}

