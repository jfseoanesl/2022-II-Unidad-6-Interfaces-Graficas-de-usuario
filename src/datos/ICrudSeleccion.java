package datos;

import entidades.Confederacion;
import entidades.SeleccionFutbol;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Jairo F
 */
public interface ICrudSeleccion {
        
    void registrarSeleccion(SeleccionFutbol s)throws IOException;
    ArrayList<SeleccionFutbol> leerSelecciones() throws IOException;
    SeleccionFutbol buscarSeleccionPorId(int id) throws IOException;
    ArrayList<SeleccionFutbol> buscarSeleccionesByConfederacion(Confederacion c) throws IOException;
}
