package datos;

import entidades.Confederacion;
import entidades.SeleccionFutbol;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Jairo F
 */
public interface ICrudConfederacion {
    void registrarConfederacion(Confederacion c)throws IOException;
    ArrayList<Confederacion> leerConfederaciones() throws IOException;
    Confederacion buscarConfederacionPorId(int id) throws IOException;
}
