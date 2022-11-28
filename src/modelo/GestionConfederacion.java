package modelo;

import datos.*;
import entidades.Confederacion;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Jairo F
 */
public class GestionConfederacion {
    private IArchivoConfederacion archivo;

    public GestionConfederacion() {
        //this.archivo = new ArchivoTextoConfederacion();
        this.archivo = new ArchivoObjetoConfederacion();
    }
    
    
    
    public void registrarConfederacion(Confederacion c)throws IOException{
        this.archivo.registrarConfederacion(c);
    }
    public ArrayList<Confederacion> leerConfederaciones() throws IOException{
       return this.archivo.leerConfederaciones();
    }
    
    public Confederacion buscarConfederacionPorId(int id) throws IOException{
     return this.archivo.buscarConfederacionPorId(id);
    }
}
