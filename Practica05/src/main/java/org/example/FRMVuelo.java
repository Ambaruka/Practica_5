package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FRMVuelo extends JFrame {
    private JLabel JLTitulo;
    private JTextField JTFAereolinea;
    private JTextField JTFOrigen;
    private JTextField JTFDestino;
    private JTextField JTFFolio;
    private JTextField JTFCosto;
    private JButton JBEliminar;
    private JButton JBRegistrar;
    private JButton JBBuscar;
    private JButton JBAnterior;
    private JButton JBSiguiente;
    private JLabel JLAereolinea;
    private JLabel JLOrigen;
    private JLabel JLDestino;
    private JLabel JLFolio;
    private JLabel JLCosto;
    private JLabel JLMensajes;
    private JLabel JLContador;
    private JPanel Oskar;
    private JButton JBLimpiar;

    int array=0,posision=0;
    Vuelo[] vuelos=new Vuelo[20];

    public FRMVuelo() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(Oskar);
        setSize(700,500);


        JTFAereolinea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                validaLetras(e);
                enter(e,JTFAereolinea,JTFOrigen);
            }
        });
        JTFOrigen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                validaLetras(e);
                enter(e,JTFOrigen,JTFDestino);
            }
        });
        JTFDestino.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                validaLetras(e);
                enter(e,JTFDestino,JTFFolio);
            }
        });
        JTFFolio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                validaNumeros(e);
                enter(e,JTFFolio,JTFCosto);
            }
        });
        JTFCosto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                validaDecimales(e,JTFCosto);
            }
        });
        JBRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean permiso= validaDatos();
                if (permiso==true) {
                    vuelos[array] = new Vuelo(JTFAereolinea.getText(), JTFOrigen.getText(), JTFDestino.getText(), Integer.parseInt(JTFFolio.getText()), Float.parseFloat(JTFCosto.getText()));
                    JLMensajes.setText("El registro se hizo con exito");
                    array++;
                    limpiar();
                }

            }
        });

        JBBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                posision=buscar(vuelos);
                if (posision<=21){
                    imprecion(vuelos);
                }
            }
        });


        JBEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=posision;i<array;i++){
                    vuelos[i]=vuelos[i+1];
                }
                vuelos[array]=null;
                array--;
                JLMensajes.setText("El vuelo se ha eliminado de forma correcta");
                limpiar();
            }

        });
        JBAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=posision-1;
                if (vuelos[a]!=null) {
                    posision--;
                    imprecion(vuelos);

                }else {
                    JLMensajes.setText("Ha llegado alinicio de los registros");
                }limpiar();
            }
        });
        JBSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=posision+1;
                if (vuelos[a]!=null) {
                    posision++;
                    imprecion(vuelos);

                }else {
                    JLMensajes.setText("No se han registrado más vuelos");
                }limpiar();
            }
        });
        JBLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar();
            }
        });
    }

    private void imprecion(Vuelo[] vuelos) {
        String datos=("Aereolinea: " + vuelos[posision].aerolínea+
                "\nOrigen: " + vuelos[posision].origen +
                "\nDestino: " + vuelos[posision].destino +
                "\nFolio: "+ vuelos[posision].folio +
                "\nCosto: $" + vuelos[posision].costo);

        JLMensajes.setText("<html>"+datos.replace("\n","<br>")+"</html>");
    }

    private void validaLetras(KeyEvent e){
        if (!(Character.isAlphabetic(e.getKeyChar()))){
            e.consume();
        }
    }

    private void validaNumeros(KeyEvent e){
        if (!(Character.isDigit(e.getKeyChar()))){
            e.consume();
        }
    }

    private void validaDecimales(KeyEvent e,JTextField pepe){
        if (!Character.isDigit(e.getKeyChar())&&e.getKeyChar()!='.'){
            e.consume();
        }
        if (e.getKeyChar() == '.' && pepe.getText().contains(".")) {
            e.consume();
        }
    }

    private int buscar(Vuelo[] vuelos){
        posision=21;
        for (int i=0;i<array;i++){
            if (JTFAereolinea.getText().equals(vuelos[i].aerolínea)){
                posision=i;
            }
        }
        if (posision==21){JLMensajes.setText("Registro inexistente");}
        return posision;
    }

    private void enter(KeyEvent e,JTextField javier,JComponent oskar){
        char letra = e.getKeyChar();
        if (letra == KeyEvent.VK_ENTER) {
            oskar.requestFocus();//pasar el focus al siguiente input
        }
    }

    private void limpiar(){
        JTFAereolinea.setText("");
        JTFOrigen.setText("");
        JTFDestino.setText("");
        JTFFolio.setText("");
        JTFCosto.setText("");
    }

    private boolean validaDatos(){
        int b= Integer.parseInt(JTFFolio.getText());
        float c=Float.parseFloat(JTFCosto.getText());
        boolean permiso;
        if (b>1000&&c>699){
            permiso= true;
        }else {permiso =false;
                if (b<1000){
                    JLMensajes.setText("<html>"+"El folio no puede ser menor a 1000 <br>Ingrese correctamente el dato"+"</html>");
                }else {JLMensajes.setText("<html>"+"El costo no puede ser menor a $699 <br>Ingrese correctamente el dato"+"</html>");}
        }
        return permiso;
    }

}
