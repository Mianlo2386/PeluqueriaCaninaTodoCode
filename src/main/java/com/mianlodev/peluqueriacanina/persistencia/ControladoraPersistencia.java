package com.mianlodev.peluqueriacanina.persistencia;

import com.mianlodev.peluqueriacanina.logica.Duenio;
import com.mianlodev.peluqueriacanina.logica.Mascota;

public class ControladoraPersistencia {
    
    DuenioJpaController duenioJpa = new DuenioJpaController();
    MascotaJpaController mascotaJpa = new MascotaJpaController();

    public void guardar(Duenio duenio, Mascota masco) {
        
        duenioJpa.create(duenio);
        
        mascotaJpa.create(masco);
        
    }
    
}
