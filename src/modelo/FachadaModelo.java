/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import vistas.DiaSemanaEnum;

/**
 *
 * @author dmoreno
 */
public class FachadaModelo {
    
    private static FachadaModelo instancia = new FachadaModelo();

    public static FachadaModelo getInstancia(){
        return instancia;
    }  
    
    public boolean agregarFrecuencia(String num, String origen, String destino, String hrPartida, String duracionEst, Compania c, ArrayList <DiaSemanaEnum> diasSem){
        
        Aeropuerto Aorigen = LogicaAeropuerto.getInstancia().buscarAeropuertoNombre(origen);
        Aeropuerto Adestino = LogicaAeropuerto.getInstancia().buscarAeropuertoNombre(destino);
        Estado eOrigen = new Estado(Aorigen);
        Estado eDestino = new Estado(Adestino); 
        FrecuenciaDeVuelo fv = new FrecuenciaDeVuelo(num, eOrigen, eDestino, hrPartida, duracionEst, c, diasSem);
        return LogicaFrecuenciaVuelo.getInstancia().GuardarFrecuencia(fv);
    }
    
    public Aeropuerto BuscarAeropuertoPorNomber(String aerop){
        return LogicaAeropuerto.getInstancia().buscarAeropuertoNombre(aerop);
    }
}
