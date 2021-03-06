package com.sanvalero.ejemploTaller;

import com.sanvalero.ejemploTaller.domain.Coche;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
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
    public ListView<Coche> lvLista;
    public Label lbEstado;
    public ObservableList<Coche> listaCoches;

    private CocheDAO cocheDAO;

    public AppController(){
        cocheDAO = new CocheDAO();
        cocheDAO.conectar();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> items = FXCollections.observableArrayList("Monovolumen", "Turismo", "SUV", "Moto", "Furgoneta");
        cbTipo.setItems(items);
        cargarDatos();
    }

    @FXML
    public void cargarDatos(){
        listaCoches = FXCollections.observableArrayList(cocheDAO.obtenerCoches());
        lvLista.setItems(listaCoches);
    }

    @FXML
    public void nuevoCoche(Event event){
        tfMatricula.setText("");
        tfMarca.setText("");
        tfModelo.setText("");
        cbTipo.setValue(null);
        lbEstado.setText("");
    }

    @FXML
    public void conectarBBDD(){
        cocheDAO = new CocheDAO();
        cocheDAO.conectar();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Conexión Base de Datos");
        alert.setContentText("Base de Datos Conectada");
        alert.show();
    }

    @FXML
    public void desconectarBBDD (){
        cocheDAO.desconectar();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Desconexión Base de Datos");
        alert.setContentText("Base de Datos Desconectada");
        alert.show();
    }

    @FXML
    public void guardarCoche(Event event) {
        String matricula = tfMatricula.getText();
        if(matricula.equals("")){
            //TODO Error de que falta indicar la matricula como campo obligatorio
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setContentText("Debes ingresar la matrícula del vehículo");
            alert.show();
        }else {
            String marca = tfMarca.getText();
            String modelo = tfModelo.getText();
            String tipo = cbTipo.getSelectionModel().getSelectedItem();
            Coche coche = new Coche(matricula, marca, modelo, tipo);
            cocheDAO.guardarCoche(coche);
            lbEstado.setText("Coche guardado correctamente");
        }

        cargarDatos();
    }

    @FXML
    public void modificarCoche(Event event) {
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
            lbEstado.setText("El coche se ha modificado correctamente");

        }
        cargarDatos();
    }

    @FXML
    public void eliminarCoche(Event event) {
        if(tfMatricula.getText() == null){
            //TODO Error de que falta indicar la matricula como campo obligatorio
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setContentText("Vehículo no seleccionado");
            alert.show();
        } else {
            Coche coche = new Coche(tfMatricula.getText());
            cocheDAO.eliminarCoche(coche);
            lbEstado.setText("El coche se ha eliminado correctamente");
        }
        cargarDatos();
    }

    public void obtenerCoche(Event event){
        tfMatricula.setText(lvLista.getSelectionModel().selectedItemProperty().getValue().getMatricula());
        tfMarca.setText(lvLista.getSelectionModel().selectedItemProperty().get().getMarca());
        tfModelo.setText(lvLista.getSelectionModel().selectedItemProperty().getValue().getModelo());
        cbTipo.setValue(String.valueOf(lvLista.getSelectionModel().selectedItemProperty().getValue().getTipo()));
    }



}
