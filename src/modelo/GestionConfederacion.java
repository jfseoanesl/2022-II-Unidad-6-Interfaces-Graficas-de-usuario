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
    private ICrudConfederacion archivo;

    public GestionConfederacion() {
        //this.archivo = new CrudConfederacionTexto();
        this.archivo = new CrudConfederacionBinario();
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
    
    public Confederacion buscarConfederacionPorNombre(String name) throws IOException{
        ArrayList<Confederacion> lista = this.archivo.leerConfederaciones();
        for(Confederacion c: lista){
            if(c.getNombre().equalsIgnoreCase(name)){
                return c;
            }
        }
        return null;
    }
}
