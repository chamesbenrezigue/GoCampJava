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
    private float Price ;
    
      public Material() {
    }

    public Material(int id, String Name, String Description, float Price) {
        this.id = id;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
    }

    public Material(String Name, String Description, float Price) {
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
    }

   

    public int getId() {
        return id;
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

    public void setPrice(float Price) {
        this.Price = Price;
    }

  
 

    @Override
    public String toString() {
        return "Material{" + "id=" + id + ", Name=" + Name + ", Description=" + Description + ", Price=" + Price + "}\n";
    }
    
}
