/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author dmoreno
 */
public class FachadaModelo {
    
    private static FachadaModelo instancia = new FachadaModelo();

    public static FachadaModelo getInstancia(){
        return instancia;
    }  
    
    public void agregarFrecuencia(FrecuenciaDeVuelo fv){
        Desde aca se llama a la logicaFrecuenciaVuelo y se ingresa la frecuencia.
    }
}