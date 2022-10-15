/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Products;

/**
 * FXML Controller class
 *
 * @author Davis
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<Products> tblProduct;
    @FXML
    private TableColumn<Products, Integer> columnId;
    @FXML
    private TableColumn<Products, String> columnName;
    @FXML
    private TableColumn<Products, Integer> columnStock;
    @FXML
    private TableColumn<Products, Double> columnPrice;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtStock;
    @FXML
    private TextField txtPrice;
    
    //arraylist utiles para la tabla
    private ArrayList<Products> productsArray = new ArrayList();
    private ObservableList<Products> products;
    
    private Products productSelected;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        uploadDataProduct();
    }    

    
    public void uploadDataProduct(){
        //configuración tabla
        tblProduct.setItems(products);
        
        //configuración columna
        columnId.setCellValueFactory(new PropertyValueFactory("id"));
        columnName.setCellValueFactory(new PropertyValueFactory("name"));
        columnStock.setCellValueFactory(new PropertyValueFactory("stock"));
        columnPrice.setCellValueFactory(new PropertyValueFactory("price"));
    }
    
    @FXML
    private void closeProgram(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void saveProduct(ActionEvent event) {
        System.out.println("SAVE");
        
        
        String id = txtId.getText();
        String name = txtName.getText();
        String stock = txtStock.getText();
        String price = txtPrice.getText();
        
        if(id.equals("") ||  name.equals("") || stock.equals("") ||  price.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("CAMPOS FALTANTES");
            alert.setHeaderText("DEBES LLENAR TODOS LOS CAMPOS");
            alert.show();
            
        }else{
            Products product = new Products(Integer.parseInt(id), name, Double.parseDouble(price), Integer.parseInt(stock));
            productsArray.add(product);
            
            products = FXCollections.observableList(productsArray);
            
            uploadDataProduct();
            
            txtId.setText("");
            txtName.setText("");
            txtStock.setText("");
            txtPrice.setText("");
            
            
        }
        
    }

    @FXML
    private void updateProduct(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String stock = txtStock.getText();
        String price = txtPrice.getText();
        
        if(id.equals("") ||  name.equals("") || stock.equals("") ||  price.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("CAMPOS FALTANTES");
            alert.setHeaderText("DEBES LLENAR TODOS LOS CAMPOS");
            alert.show();
            
        }else{
            products.forEach((product)->{
                if(product.getId()==productSelected.getId()){
                    product.setName(name);
                    product.setPrice(Double.parseDouble(price));
                    product.setStock(Integer.parseInt(stock));
                    tblProduct.refresh();
                    txtId.setEditable(true);
                    
                    txtId.setText("");
                    txtName.setText("");
                    txtStock.setText("");
                    txtPrice.setText("");
                    
                }else{
                    //no lo actualice
                }
            });
            
        }
    }

    @FXML
    private void deleteProduct(ActionEvent event) {
        System.out.println("delete");
    }

    @FXML
    private void selectProduct(MouseEvent event) {
        try{
            productSelected = tblProduct.getSelectionModel().getSelectedItem();
            
            txtId.setText(String.valueOf(productSelected.getId()));
            txtName.setText(productSelected.getName());
            txtStock.setText(String.valueOf(productSelected.getStock()));
            txtPrice.setText(String.valueOf(productSelected.getPrice()));
            
            
            txtId.setEditable(false);
        }catch(Exception e){

        }
    }
    
}
