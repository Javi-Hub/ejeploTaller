package com.sanvalero.ejemploTaller;

import com.sanvalero.ejemploTaller.domain.Coche;
import javafx.scene.control.Alert;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Creado por @author: Javier
 * el 28/10/2020
 */
public class CocheDAO {

    private Connection conexion;
    private final String USUARIO = "javier";
    private final String PASSWORD = "javiertaller";
    public void conectar(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/taller?serverTimeZone=UTC",
                    USUARIO, PASSWORD);

        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();

        } catch (SQLException sqle){
            sqle.printStackTrace();
        }

    }
    public void desconectar(){
        try {
            conexion.close();
            conexion = null;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void guardarCoche(Coche coche) throws SQLException {
        String sql = "INSERT INTO coches (matricula, marca, modelo, tipo) VALUES (?, ?, ?, ?);";
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, coche.getMatricula());
            sentencia.setString(2, coche.getMarca());
            sentencia.setString(3, coche.getModelo());
            sentencia.setString(4, coche.getTipo());
            sentencia.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guardar Coche");
            alert.setContentText("El coche se ha guardado correctamente");
            alert.show();

        } catch (SQLException sqle){
            sqle.printStackTrace();
        } finally {
            if(sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public void eliminarCoche(Coche coche) throws SQLException {
        String sql = "DELETE FROM coches WHERE matricula = ?;";
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, coche.getMatricula());
            sentencia.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Eliminar Coche");
            alert.setContentText("El coche se ha eliminado correctamente");
            alert.show();
        } catch (SQLException sqle){
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                }catch (SQLException sqle){
                    sqle.printStackTrace();
                }
            }
        }
    }

    public void modificarCoche(Coche coche) throws SQLException {
    String sql = "UPDATE coches SET matricula = ?, marca = ?, modelo = ?, tipo = ? WHERE matricula = ?";
    PreparedStatement sentencia = null;
       try {
           sentencia = conexion.prepareStatement(sql);
           sentencia.setString(1, coche.getMatricula());
           sentencia.setString(2, coche.getMarca());
           sentencia.setString(3, coche.getModelo());
           sentencia.setString(4, coche.getTipo());
           sentencia.setString(5, JOptionPane.showInputDialog("Introduce la matrícula del vehículo que deseas modificar"));
           sentencia.executeUpdate();
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Modificar Coche");
           alert.setContentText("El coche se ha modificado correctamente");
           alert.show();
       } catch (SQLException sqle){
           sqle.printStackTrace();
       }finally {
           if (sentencia != null){
               try {
                   sentencia.close();
               }catch (SQLException sqle){
                   sqle.printStackTrace();
               }
           }
       }

    }

    public void obtenerCoches(){
        String sql = "SELECT * FROM coches;";
        try {
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            List<Coche> lista = new ArrayList<Coche>();
            while(resultado.next()){

                /*System.out.print(resultado.getString(2) + " - ");
                System.out.print(resultado.getString(3) + " - ");
                System.out.print(resultado.getString(4) + " - ");
                System.out.println(resultado.getString(5));*/

            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

}
