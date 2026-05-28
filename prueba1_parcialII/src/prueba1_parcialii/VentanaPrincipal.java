/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba1_parcialii;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author alira
 */
public class VentanaPrincipal extends JFrame{
    
    private JTextField txtRuta;
    private JTextField txtBusqueda;
    private JTextArea txtResultados;

    private AnalizadorArchivos analizador;

    public VentanaPrincipal() {
        analizador = new AnalizadorArchivos();
        inicializarUI();
    }

    private void inicializarUI() {
        setTitle("Parcial II - Analizador");
        setSize(700, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelEntradas = new JPanel(new GridLayout(2, 1, 8, 8));

        JPanel filaRuta = new JPanel(new BorderLayout(6, 0));
        filaRuta.add(new JLabel("Directorio raíz:"), BorderLayout.WEST);
        txtRuta = new JTextField();
        filaRuta.add(txtRuta, BorderLayout.CENTER);
        JButton btnExaminar = new JButton("Examinar");
        btnExaminar.addActionListener(e -> examinarDirectorio());
        filaRuta.add(btnExaminar, BorderLayout.EAST);

        JPanel filaBusqueda = new JPanel(new BorderLayout(6, 0));
        filaBusqueda.add(new JLabel("Buscar archivo:  "), BorderLayout.WEST);
        txtBusqueda = new JTextField();
        filaBusqueda.add(txtBusqueda, BorderLayout.CENTER);

        panelEntradas.add(filaRuta);
        panelEntradas.add(filaBusqueda);

        txtResultados = new JTextArea();
        txtResultados.setEditable(false);
        txtResultados.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtResultados.setLineWrap(true);
        txtResultados.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtResultados);
        scroll.setBorder(BorderFactory.createTitledBorder("Resultados"));

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnAnalizar = new JButton("Analizar");
        btnAnalizar.addActionListener(e -> ejecutarAnalisis());
        panelBoton.add(btnAnalizar);

        panelPrincipal.add(panelEntradas, BorderLayout.NORTH);
        panelPrincipal.add(scroll, BorderLayout.CENTER);
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private void examinarDirectorio() {
        JFileChooser selector = new JFileChooser();
        selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        selector.setDialogTitle("Seleccionar directorio");

        int resultado = selector.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            txtRuta.setText(selector.getSelectedFile().getAbsolutePath());
        }
    }

    private void ejecutarAnalisis() {
        String ruta = txtRuta.getText().trim();

        if (ruta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una ruta.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        File directorio = new File(ruta);

        if (!directorio.exists()) {
            JOptionPane.showMessageDialog(this, "La ruta no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!directorio.isDirectory()) {
            JOptionPane.showMessageDialog(this, "La ruta no es un directorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ContadorArchivos contador = new ContadorArchivos();
        analizador.analizarDirectorio(directorio, contador);

        StringBuilder sb = new StringBuilder();
        sb.append("=== CONTEO DE ARCHIVOS ===\n\n");
        sb.append("TXT: " + contador.getTxt() + " archivos\n");
        sb.append("JAVA: " + contador.getJava() + " archivos\n");
        sb.append("PDF: " + contador.getPdf() + " archivos\n");
        sb.append("OTROS: " + contador.getOtros() + " archivos\n");

        String textoBusqueda = txtBusqueda.getText().trim();

        if (!textoBusqueda.isEmpty()) {
            sb.append("\n=== BÚSQUEDA: \"" + textoBusqueda + "\" ===\n\n");

            List<String> resultados = new ArrayList<>();
            analizador.buscarArchivos(directorio, textoBusqueda, resultados);

            if (resultados.isEmpty()) {
                sb.append("No se encontraron archivos que coincidan con los criterios.");
            } else {
                for (String r : resultados) {
                    sb.append(r + "\n");
                }
            }
        }

        txtResultados.setText(sb.toString());
    }
    
}
