package com.mianlodev.peluqueriacanina.persistencia;

import com.mianlodev.peluqueriacanina.logica.Duenio;
import com.mianlodev.peluqueriacanina.logica.Mascota;
import java.util.List;

public class ControladoraPersistencia {
    
    DuenioJpaController duenioJpa = new DuenioJpaController();
    MascotaJpaController mascotaJpa = new MascotaJpaController();

    public void guardar(Duenio duenio, Mascota masco) {
        
        duenioJpa.create(duenio);
        
        mascotaJpa.create(masco);
        
    }

    public List<Mascota> traerMascotas() {
        
        
        return mascotaJpa.findMascotaEntities();
    }
    
}
