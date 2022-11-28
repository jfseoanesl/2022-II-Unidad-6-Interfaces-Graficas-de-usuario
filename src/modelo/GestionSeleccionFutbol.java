package modelo;

import datos.*;
import entidades.SeleccionFutbol;
import java.io.IOException;
import java.util.ArrayList;
import datos.IArchivoSeleccion;
import entidades.Confederacion;

/**
 *
 * @author Jairo F
 */
public class GestionSeleccionFutbol {
    
    private IArchivoSeleccion archivo;

    public GestionSeleccionFutbol() {
        this.archivo = new ArchivoTextoSeleccion();
        //this.archivo = new ArchivoObjetoSeleccion();
    }
    
    
    
    public void registrarSeleccion(SeleccionFutbol s)throws IOException{
        this.archivo.registrarSeleccion(s);
    }
    
    public ArrayList<SeleccionFutbol> leerSelecciones() throws IOException{
        return this.archivo.leerSelecciones();
    }
    
    public SeleccionFutbol buscarSeleccionPorId(int id) throws IOException{
        return this.archivo.buscarSeleccionPorId(id);
    }
    
    public ArrayList<SeleccionFutbol> buscarSeleccionesByConfederacion(Confederacion c) throws IOException{
        return this.archivo.buscarSeleccionesByConfederacion(c);
    }
    
}
