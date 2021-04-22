/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.GUI;

import GoCampJavaFX.com.esprit.Service.ServiceUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Rezigue
 */
public class ChartsController implements Initializable {
            ServiceUser su = new ServiceUser();

    
      @FXML
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       CategoryAxis xAxis = new CategoryAxis();
       xAxis.setLabel("sexe");
       NumberAxis yAxis = new NumberAxis();
       yAxis.setLabel("Quantité");
       
       BarChart barChart =new BarChart(xAxis,yAxis);
       
       XYChart.Series data = new XYChart.Series();
       data.setName("Sexe");
       
                try {
                    //provide data
                    data.getData().add(new XYChart.Data("Men",su.countMen()));
                } catch (SQLException ex) {
                    Logger.getLogger(ChartsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    data.getData().add(new XYChart.Data("Women",su.countWomen()));
                } catch (SQLException ex) {
                    Logger.getLogger(ChartsController.class.getName()).log(Level.SEVERE, null, ex);
                }
       
       barChart.getData().add(data);

       borderPane.setCenter(barChart);
       
    }   
  
    
}
