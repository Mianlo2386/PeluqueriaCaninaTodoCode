package com.mianlodev.peluqueriacanina.logica;

import com.mianlodev.peluqueriacanina.persistencia.ControladoraPersistencia;
import java.util.List;


public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    

    public void guardar(String nombreMasco, String raza, String color, String observaciones, String nombreDuenio, String celularDuenio, String alergico, String atencionEspecial) {
        Duenio duenio = new Duenio();
        duenio.setNombre(nombreDuenio);
        duenio.setCelDuenio(celularDuenio);
        
        Mascota masco = new Mascota();
        masco.setNombre(nombreMasco);
        masco.setRaza(raza);
        masco.setColor(color);
        masco.setAlergico(alergico);
        masco.setAtencionEspecial(atencionEspecial);
        masco.setObservaciones(observaciones);
        masco.setDuenio(duenio);
        
        controlPersis.guardar(duenio, masco);
    }

    public List<Mascota> traerMascotas() {
        
        
        return controlPersis.traerMascotas();
    }
    
}
