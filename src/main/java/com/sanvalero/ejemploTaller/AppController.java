package com.sanvalero.ejemploTaller;

import com.sanvalero.ejemploTaller.domain.Coche;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Creado por @author: Javier
 * el 28/10/2020
 */
public class AppController implements Initializable{

    public TextField tfMatricula;
    public TextField tfMarca;
    public TextField tfModelo;
    public ComboBox<String> cbTipo;
    public ListView lvLista;
    public ObservableList<Coche> listaCoches;


    private CocheDAO cocheDAO;

    public AppController(){
        cocheDAO = new CocheDAO();
        cocheDAO.conectar();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> items = FXCollections.observableArrayList("Monovolumen", "Turismo", "SUV", "Motocicleta");
        cbTipo.setItems(items);

    }

    @FXML
    public void nuevoCoche(Event event){
        tfMatricula.setText("");
        tfMarca.setText("");
        tfModelo.setText("");
        cbTipo.setValue(null);

    }

    @FXML
    public void guardarCoche(Event event) throws SQLException {
        String matricula = tfMatricula.getText();
        if(matricula.equals("")){
            //TODO Error de que falta indicar la matricula como campo obligatorio
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setContentText("Falta ingresar la matrícula");
            alert.show();
        }else {
            String marca = tfMarca.getText();
            String modelo = tfModelo.getText();
            String tipo = cbTipo.getSelectionModel().getSelectedItem();
            Coche coche = new Coche(matricula, marca, modelo, tipo);
            cocheDAO.guardarCoche(coche);
        }
    }

    @FXML
    public void modificarCoche(Event event) throws SQLException {
        String matricula = tfMatricula.getText();
        if(matricula.equals("")){
            //TODO Error de que falta indicar la matricula como campo obligatorio
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setContentText("Falta ingresar la matrícula para poder modificar");
            alert.show();
        }else {
            String marca = tfMarca.getText();
            String modelo = tfModelo.getText();
            String tipo = cbTipo.getSelectionModel().getSelectedItem();
            Coche coche = new Coche(matricula, marca, modelo, tipo);
            cocheDAO.modificarCoche(coche);
        }
    }

    @FXML
    public void eliminarCoche(Event event) throws SQLException {
        String matricula = JOptionPane.showInputDialog("Introduce la mátricula");
        if(matricula.equals("")){
            //TODO Error de que falta indicar la matricula como campo obligatorio
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setContentText("Falta ingresar la matrícula");
            alert.show();
        } else {
            Coche coche = new Coche(matricula);
            cocheDAO.eliminarCoche(coche);
        }
    }


}