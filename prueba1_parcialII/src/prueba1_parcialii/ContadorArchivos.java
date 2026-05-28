/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba1_parcialii;

/**
 *
 * @author alira
 */
public class ContadorArchivos {

private int txt;
private int archivosJava;
private int pdf;
private int otros;

public int getTxt() {
    return txt;
}

public int getJava() {
    return archivosJava;
}

public int getPdf() {
    return pdf;
}

public int getOtros() {
    return otros;
}

public void incrementarTxt() {
    txt++;
}

public void incrementarJava() {
    archivosJava++;
}

public void incrementarPdf() {
    pdf++;
}

public void incrementarOtros() {
    otros++;
}

}
    

