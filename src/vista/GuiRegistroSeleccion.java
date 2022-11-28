package vista;

import entidades.Confederacion;
import entidades.SeleccionFutbol;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import modelo.GestionConfederacion;
import modelo.GestionSeleccionFutbol;

/**
 *
 * @author Jairo F
 */
public class GuiRegistroSeleccion extends JDialog implements ActionListener {

    private Container contenedor;
    private JPanel panelDatos, panelBotones;
    private JLabel lbNombre, lbConfederacion, lbRanking, lbRendimiento, lbClasificada;
    private JTextField txtNombre;
    private JFormattedTextField txtRanking, txtRendimiento;
    private JComboBox cmbConfederacion;
    private JRadioButton opcSi, opcNo;
    private JButton btnGuardar, btnCancelar;
    private GestionConfederacion modeloConfe;
    private GestionSeleccionFutbol modeloSele;

    public GuiRegistroSeleccion(Frame owner, boolean modal) {
        super(owner, modal);
        this.modeloConfe = new GestionConfederacion();
        this.modeloSele = new GestionSeleccionFutbol();
        this.setTitle("Registro de selecciones - Version 1");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.inicializarComponenetes();
        //this.setSize(500, 350);
        this.pack();
        this.setLocationRelativeTo(null);
       
        this.setVisible(true);

        
        
        
    }

    public void inicializarComponenetes() {

        this.contenedor = this.getContentPane();
        this.contenedor.setLayout(new BorderLayout());
        this.crearPanelDatos();
        this.crearPanelBotones();
        
        this.cargarConfederaciones();

    }

    public void crearPanelDatos() {
        this.panelDatos = new JPanel(new GridLayout(5, 2, 5, 5));
        this.panelDatos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.lbNombre = new JLabel("Nombre: ");
        this.lbRanking = new JLabel("Ranking: ");
        this.lbConfederacion = new JLabel("Confederacion:");
        this.lbClasificada = new JLabel("Esta clasificada?: ");
        this.lbRendimiento = new JLabel("% Rendimiento: ");

        this.txtNombre = new JTextField(15);
        this.txtRanking = new JFormattedTextField(0);

        try {
            MaskFormatter mascara = new MaskFormatter("##.##");
            this.txtRendimiento = new JFormattedTextField(mascara);
            this.txtRendimiento.setText("00.00");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

        this.cmbConfederacion = new JComboBox();
//        this.cmbConfederacion.addItem("Conmebol");
//        this.cmbConfederacion.addItem("Uefa");

        this.opcSi = new JRadioButton("Si");
        this.opcSi.setSelected(true);
        this.opcNo = new JRadioButton("No");

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(opcSi);
        grupo.add(opcNo);
        JPanel panelOpc = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelOpc.add(this.opcSi);
        panelOpc.add(this.opcNo);

        this.panelDatos.add(this.lbNombre);
        this.panelDatos.add(this.txtNombre);

        this.panelDatos.add(this.lbRanking);
        this.panelDatos.add(this.txtRanking);

        this.panelDatos.add(this.lbConfederacion);
        this.panelDatos.add(this.cmbConfederacion);

        this.panelDatos.add(this.lbRendimiento);
        this.panelDatos.add(this.txtRendimiento);

        this.panelDatos.add(this.lbClasificada);
        this.panelDatos.add(panelOpc);

        this.contenedor.add(this.panelDatos, BorderLayout.CENTER);

    }

    public void cargarConfederaciones() {
        try {
            ArrayList<Confederacion> lista = this.modeloConfe.leerConfederaciones();
            for(Confederacion c: lista){
                this.cmbConfederacion.addItem(c.getNombre());
            }
        } catch (IOException io) {
            JOptionPane.showMessageDialog(this, io, "Error de archivo", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void crearPanelBotones() {

        this.panelBotones = new JPanel();
        this.panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.btnGuardar = new JButton("Guardar");
        this.btnGuardar.addActionListener(this);
        this.btnCancelar = new JButton("Cancelar");
        this.btnCancelar.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
        panel.add(this.btnGuardar);
        panel.add(this.btnCancelar);

        this.panelBotones.add(panel);
        this.contenedor.add(this.panelBotones, BorderLayout.EAST);

    }

    private SeleccionFutbol leerDatos() throws IOException {

        if (this.txtNombre.getText().isEmpty()) {
            throw new IllegalArgumentException("EL nombre de la seleccion no puede ser vacio");
        }

        String nombre = this.txtNombre.getText();
        int ranking = (int) this.txtRanking.getValue();
        double rendimiento = Double.valueOf(this.txtRendimiento.getText());
        boolean clasificado = this.opcSi.isSelected() ? true : false;
        String confe = this.cmbConfederacion.getSelectedItem().toString();
        Confederacion confederacion = this.modeloConfe.buscarConfederacionPorNombre(confe);
        int id = this.modeloSele.leerSelecciones().size() + 1;

        return new SeleccionFutbol(id, nombre, ranking, clasificado, rendimiento, confederacion);
    }

    public void guardar() {

        try {
            SeleccionFutbol seleccion = this.leerDatos();
            this.modeloSele.registrarSeleccion(seleccion);
            JOptionPane.showMessageDialog(this, "Datos guardados con exito", "COnfirmacion", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error de archivo", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ie) {
            JOptionPane.showMessageDialog(this, ie, "Error de validacion", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.btnGuardar) {

            this.guardar();

        } else if (e.getSource() == this.btnCancelar) {

        }

    }

}
