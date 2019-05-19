/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import java.util.Calendar;
import modelo.Estado;
import modelo.EstadoEnum;
import modelo.FachadaModelo;
import modelo.FrecuenciaDeVuelo;
import vistas.DiaSemanaEnum;

/**
 *
 * @author majuetcheverry
 */
public class ControladoraAeropuerto {

    private static ControladoraAeropuerto instancia = new ControladoraAeropuerto();

    public static ControladoraAeropuerto getInstancia() {
        return instancia;
    }

    public ArrayList<FrecuenciaDeVuelo> frecuenciasPorAeropuertoOrigen(String nomAero) {
        ArrayList<FrecuenciaDeVuelo> f = FachadaModelo.getInstancia().getFrecuencias();
        ArrayList<FrecuenciaDeVuelo> frecuenciasFiltradas = new ArrayList<>();

        for (FrecuenciaDeVuelo frec : f) {
            if (frec.aeropuertoOrigen.estado.equals(EstadoEnum.Pendiente)
                    && frec.aeropuertoOrigen.aeropuerto.nombre.equals(nomAero)) {
                frecuenciasFiltradas.add(frec);
            }
        }
        return frecuenciasFiltradas;
    }

    public ArrayList<FrecuenciaDeVuelo> frecuenciasPorAeropuertoDestino(String nomAero) {
        ArrayList<FrecuenciaDeVuelo> f = FachadaModelo.getInstancia().getFrecuencias();
        ArrayList<FrecuenciaDeVuelo> frecuenciasFiltradasDestino = new ArrayList<>();

        for (FrecuenciaDeVuelo frec : f) {
            if (frec.aeropuertoDestino.estado.equals(EstadoEnum.Pendiente) && frec.aeropuertoOrigen.estado.equals(EstadoEnum.Aprobado)
                    && frec.aeropuertoDestino.aeropuerto.nombre.equals(nomAero)) {
                frecuenciasFiltradasDestino.add(frec);
            }
        }
        return frecuenciasFiltradasDestino;
    }

    public ArrayList<FrecuenciaDeVuelo> frecuenciasAprobadasOrigen(String nomAero) {
        ArrayList<FrecuenciaDeVuelo> f = FachadaModelo.getInstancia().getFrecuencias();
        ArrayList<FrecuenciaDeVuelo> frecuenciasFiltradasAprobadas = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_WEEK);
        DiaSemanaEnum hoy = getDiaSemana(dia);

        for (FrecuenciaDeVuelo frec : f) {
            if (frec.aeropuertoOrigen.estado.equals(EstadoEnum.Aprobado) && frec.aeropuertoDestino.estado.equals(EstadoEnum.Aprobado)
                    && frec.aeropuertoOrigen.aeropuerto.nombre.equals(nomAero) && frec.diasSemana.contains(hoy)) {
                frecuenciasFiltradasAprobadas.add(frec);
            }
        }
        return frecuenciasFiltradasAprobadas;
    }

    public DiaSemanaEnum getDiaSemana(int dia) {
        DiaSemanaEnum diaSemana = DiaSemanaEnum.D;

        switch (dia) {
            case Calendar.MONDAY:
                diaSemana = DiaSemanaEnum.L;
                break;
            case Calendar.TUESDAY:
                diaSemana = DiaSemanaEnum.M;
                break;
            case Calendar.WEDNESDAY:
                diaSemana = DiaSemanaEnum.X;
                break;
            case Calendar.THURSDAY:
                diaSemana = DiaSemanaEnum.J;
                break;
            case Calendar.FRIDAY:
                diaSemana = DiaSemanaEnum.V;
                break;
            case Calendar.SATURDAY:
                diaSemana = DiaSemanaEnum.S;
                break;
        }
        return diaSemana;
    }

    public ArrayList<FrecuenciaDeVuelo> frecuenciasAprobadasDestino(String aeropuerto) {
        ArrayList<FrecuenciaDeVuelo> f = FachadaModelo.getInstancia().getFrecuencias();
        ArrayList<FrecuenciaDeVuelo> frecuenciasFiltradasAprobadas = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_WEEK);
        DiaSemanaEnum hoy = getDiaSemana(dia);

        for (FrecuenciaDeVuelo frec : f) {
            if (frec.aeropuertoOrigen.estado.equals(EstadoEnum.Aprobado) && frec.aeropuertoDestino.estado.equals(EstadoEnum.Aprobado)
                    && frec.aeropuertoDestino.aeropuerto.nombre.equals(aeropuerto) && frec.diasSemana.contains(hoy)) {
                frecuenciasFiltradasAprobadas.add(frec);
            }
        }
        return frecuenciasFiltradasAprobadas;

    }

    public void aprobarEstadoFrecuenciaDestino(FrecuenciaDeVuelo f) {
        ArrayList<FrecuenciaDeVuelo> frecuencias = FachadaModelo.getInstancia().getFrecuencias();

        for (FrecuenciaDeVuelo frecuen : frecuencias) {
            if (frecuen.equals(f) && frecuen.aeropuertoDestino.estado.equals(EstadoEnum.Pendiente)) {
                frecuen.aeropuertoDestino.estado = EstadoEnum.Aprobado;
            }
        }
        FachadaModelo.getInstancia().actualizarFrecuencias(frecuencias);
    }

    public void aprobarEstadoFrecuenciaOrigen(FrecuenciaDeVuelo frec) {
        ArrayList<FrecuenciaDeVuelo> frecuencias = FachadaModelo.getInstancia().getFrecuencias();

        for (FrecuenciaDeVuelo frecuen : frecuencias) {
            if (frecuen.equals(frec) && frecuen.aeropuertoOrigen.estado.equals(EstadoEnum.Pendiente)) {
                frecuen.aeropuertoOrigen.estado = EstadoEnum.Aprobado;
            }
        }
        FachadaModelo.getInstancia().actualizarFrecuencias(frecuencias);
    }
    
    public void rechazarEstadoFrecuenciaOrigen (FrecuenciaDeVuelo frec){
    ArrayList<FrecuenciaDeVuelo> frecuencias = FachadaModelo.getInstancia().getFrecuencias();

        for (FrecuenciaDeVuelo frecuen : frecuencias) {
            if (frecuen.equals(frec) && frecuen.aeropuertoOrigen.estado.equals(EstadoEnum.Pendiente)) {
                frecuen.aeropuertoOrigen.estado = EstadoEnum.Rechazado;
            }
        }
        FachadaModelo.getInstancia().actualizarFrecuencias(frecuencias);
    
    }
    
    public void rechazarEstadoFrecuenciaDestino (FrecuenciaDeVuelo frec){
     ArrayList<FrecuenciaDeVuelo> frecuencias = FachadaModelo.getInstancia().getFrecuencias();

        for (FrecuenciaDeVuelo frecuen : frecuencias) {
            if (frecuen.equals(frec) && frecuen.aeropuertoOrigen.estado.equals(EstadoEnum.Aprobado)) {
                frecuen.aeropuertoDestino.estado = EstadoEnum.Rechazado;
            }
        }
        FachadaModelo.getInstancia().actualizarFrecuencias(frecuencias);
    }
    
}