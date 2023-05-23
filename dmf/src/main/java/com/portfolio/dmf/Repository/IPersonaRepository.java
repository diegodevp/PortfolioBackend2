package com.portfolio.dmf.Repository;

import com.portfolio.dmf.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonaRepository
        extends JpaRepository<Persona, Integer>
    {
        public Optional<Persona> findByNombre(String nombre);
        public boolean existsByNombre(String nombre);
    }

