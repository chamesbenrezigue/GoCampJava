/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.Entite;

/**
 *
 * @author chaima
 */
public class Material {
    private int id ;
    private String Name,Description ;
    private int Price ;
    private int quantity;
    private String image;
    
      public Material() {
    }

  

    public Material(int id, String Name, String Description, int Price, int quantity) {
        this.id = id;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        this.quantity = quantity;
    }
     public Material( String Name, String Description, int Price, int quantity) {
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        this.quantity = quantity;
    }

    public Material(int id, String Name, String Description, int Price, int quantity, String image) {
        this.id = id;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        this.quantity = quantity;
        this.image = image;
    }

    public Material(String Name, String Description, int Price, int quantity, String image) {
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        this.quantity = quantity;
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Material(String Name, String Description, int Price) {
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
    }

   

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    @Override
    public String toString() {
        return "Material{" + "id=" + id + ", Name=" + Name + ", Description=" + Description + ", Price=" + Price + ", quantity=" + quantity + ", image=" + image + '}';
    }

  

  
 

 
    
    
}
