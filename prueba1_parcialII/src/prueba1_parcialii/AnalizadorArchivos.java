/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba1_parcialii;

import java.io.File;
import java.util.List;

/**
 *
 * @author alira
 */
public class AnalizadorArchivos {
    
    public void analizarDirectorio(File directorio, ContadorArchivos contador) {
        File[] archivos = directorio.listFiles();

        if (archivos == null) {
            return;
        }

        for (File archivo : archivos) {
            if (archivo.isDirectory()) {
                analizarDirectorio(archivo, contador);
            } else {
                String nombre = archivo.getName().toLowerCase();

                if (nombre.endsWith(".txt")) {
                    contador.incrementarTxt();
                } else if (nombre.endsWith(".java")) {
                    contador.incrementarJava();
                } else if (nombre.endsWith(".pdf")) {
                    contador.incrementarPdf();
                } else {
                    contador.incrementarOtros();
                }
            }
        }
    }

    public void buscarArchivos(File directorio, String texto, List<String> resultados) {
        File[] archivos = directorio.listFiles();

        if (archivos == null) {
            return;
        }

        for (File archivo : archivos) {
            if (archivo.isDirectory()) {
                buscarArchivos(archivo, texto, resultados);
            } else {
                if (archivo.getName().toLowerCase().contains(texto.toLowerCase())) {
                    resultados.add(archivo.getAbsolutePath());
                }
            }
        }
    }
    
}
