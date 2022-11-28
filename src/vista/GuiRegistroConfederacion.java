package vista;

import entidades.Confederacion;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import javax.swing.*;
import modelo.GestionConfederacion;

/**
 *
 * @author Jairo F
 */
public class GuiRegistroConfederacion extends JDialog implements ActionListener {

    private Container contenedor;

    private JLabel lbNombre, lbPaises, lbBandera;
    private JTextField txtNombre, txtPaises;
    private JTextArea txtRegistrados;

    private JButton btnGuardar, btnCancelar, btnAbrir;

    private JFileChooser jf;

    private JPanel datos, botones, panelBandera;

    private GestionConfederacion modelo;

    private File banderaLeida;

    public GuiRegistroConfederacion(JFrame owner, boolean modal) {
        super(owner, modal);
        this.modelo = new GestionConfederacion();
        this.setTitle("Registro de confederaciones");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.inicializarGui();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void inicializarGui() {
        this.contenedor = this.getContentPane();
        this.contenedor.setLayout(new BorderLayout());
        this.crearPanelDatos();
        this.crearPanelBotones();
        this.crearPanelBandera();
        this.cargarConfederaciones();

    }

    public void crearPanelDatos() {

        this.datos = new JPanel();
        this.datos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.datos.setLayout(new BorderLayout());

        this.lbNombre = new JLabel("Nombre: ");
        this.lbPaises = new JLabel("Paises: ");

        this.txtNombre = new JTextField(15);
        this.txtPaises = new JTextField(15);

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(this.lbNombre);
        panel.add(this.txtNombre);
        panel.add(this.lbPaises);
        panel.add(this.txtPaises);

        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createTitledBorder("Registradas: "));

        this.txtRegistrados = new JTextArea(5, 30);
        panel2.add(this.txtRegistrados);

        this.datos.add(panel, BorderLayout.NORTH);
        this.datos.add(panel2, BorderLayout.SOUTH);

        this.contenedor.add(this.datos, BorderLayout.CENTER);

    }

    public void crearPanelBotones() {
        this.botones = new JPanel(new FlowLayout());
        this.botones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.btnGuardar = new JButton("Guardar");
        this.btnGuardar.addActionListener(this);
        this.btnCancelar = new JButton("Cancelar");

        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
        panel.add(this.btnGuardar);
        panel.add(this.btnCancelar);

        this.botones.add(panel);

        this.contenedor.add(this.botones, BorderLayout.EAST);

    }

    public void crearPanelBandera() {

        this.panelBandera = new JPanel();
        ImageIcon image = new ImageIcon("./src/imagenes/not-image.jpeg");

        this.lbBandera = new JLabel(image);

        this.btnAbrir = new JButton("Abrir");
        this.btnAbrir.addActionListener(this);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(this.lbBandera, BorderLayout.NORTH);
        panel.add(this.btnAbrir, BorderLayout.SOUTH);
        this.panelBandera.add(panel);
        this.contenedor.add(this.panelBandera, BorderLayout.WEST);

    }

    public void cargarConfederaciones() {
        try {
            ArrayList<Confederacion> lista = this.modelo.leerConfederaciones();
            for (Confederacion c : lista) {
                this.txtRegistrados.setText(this.txtRegistrados.getText() + " " + c.getNombre());
            }

        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(this, ioe, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void guardar() throws IOException {

        String nombre = this.txtNombre.getText();
        String paises = this.txtPaises.getText();
        int id = this.modelo.leerConfederaciones().size() + 1;
        Confederacion confe = new Confederacion(id, nombre, paises);
        this.modelo.registrarConfederacion(confe);

        String name = this.banderaLeida.getName();
        String ext = name.substring(name.indexOf("."), name.length());
        File banderaDestino = new File("./src/imagenes/" + id +  ext);

        Files.copy(this.banderaLeida.toPath(), banderaDestino.toPath());

    }

    public void leerLogo() {
        this.jf = new JFileChooser();
        int open = this.jf.showOpenDialog(null);
        if (open == JFileChooser.APPROVE_OPTION) {
            this.banderaLeida = this.jf.getSelectedFile();
            ImageIcon img = new ImageIcon(this.banderaLeida.getAbsolutePath());
            this.lbBandera.setIcon(img);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnAbrir) {
            this.leerLogo();
        }

        if (e.getSource() == this.btnGuardar) {
            try {
                this.guardar();
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        }
    }

}
