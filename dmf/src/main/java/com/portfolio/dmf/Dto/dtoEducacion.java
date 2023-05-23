package com.portfolio.dmf.Dto;

import jakarta.validation.constraints.NotBlank;

public class dtoEducacion {
    @NotBlank
    private String nombreEdu;
    @NotBlank
    private String descripcionEdu;

    public dtoEducacion()
    {
    }
    public dtoEducacion(String nombreEdu, String descripcionEdu)
    {
        this.nombreEdu = nombreEdu;
        this.descripcionEdu = descripcionEdu;
    }

    public String getNombreE()
    {
        return nombreEdu;
    }
    public void setNombreEdu(String nombreEdu)
    {
        this.nombreEdu = nombreEdu;
    }
    public String getDescripcionE()
    {
        return descripcionEdu;
    }
    public void setDescripcionE(String descripcionE)
    {
        this.descripcionEdu = descripcionE;
    }
}

