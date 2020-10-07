import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import java.io.*;

public class DataBase
{
public  ArrayList<Vehicle> vehicles;
 
 public DataBase()
 {
     vehicles = new ArrayList<Vehicle>();
 }
 public void addToBase(Vehicle vehicle)
 {
     vehicles.add(vehicle);
 }

 public String  showBase()
 {
     String output = "";
     int counter = 1;
     for(Vehicle vehicle : vehicles)
     {
         output += counter+" Vehicle\n"+"Brand: "+vehicle.brand+"\nModel: "+vehicle.model+"\nPower: "+vehicle.power+"\nColour: "+vehicle.colour+"\nRegistration: "+vehicle.registration+ "\n======\n";
         counter++;
     }
     return output;
 }

 public boolean xmlExists()
 {
     String getPath = System.getProperty("user.home");
     try{
         File save = new File(getPath+"/lab2/vehicles.xml");
         if(save.createNewFile())
         {
             System.out.println("Created the file");
             return true;
         }else{
             System.out.println("File already exists");
             return true;
         }
     }catch (IOException e)
     {
         System.out.println("Something went wrong");
         return false;
     }
 }


 public void  xmlRead()
 {
     String brand;
     String model;
     String registration;
     String power;
     String colour;
     try{
         File inputFile = new File("vehicles.xml");
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         NodeList nList = doc.getElementsByTagName("vehicle");
         
         for(int temp = 0; temp<nList.getLength();temp++)
         {
             Node nNode = nList.item(temp);
            
             if (nNode.getNodeType() == Node.ELEMENT_NODE)
             {
                 Element eElement = (Element) nNode;
           brand = eElement.getElementsByTagName("brand").item(0).getTextContent();
                 model = eElement.getElementsByTagName("model").item(0).getTextContent();
                 registration = eElement.getElementsByTagName("registration").item(0).getTextContent();
                 colour = eElement.getElementsByTagName("colour").item(0).getTextContent();
                 power = eElement.getElementsByTagName("power").item(0).getTextContent();
                 Vehicle vehicle = new Vehicle(brand,model,registration,power,colour);
                 addToBase(vehicle);
             }
         }
     }catch (Exception e){
         e.printStackTrace();
     }
 }

 public void xmlWrite(String brand, String model, String colour, String power, String registration)
 {
     try{
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc =dBuilder.newDocument();

         Element eRoot = doc.createElement("vehicles");
         doc.appendChild(eRoot);
    
for(Vehicle vehicle: vehicles)
{

 Element eVehicle = doc.createElement("vehicle");
      Attr aId = doc.createAttribute("id");


    eRoot.appendChild(eVehicle);    
     aId.setValue(Integer.toString(vehicles.size()+1));
    eVehicle.setAttributeNode(aId);
   
    Element eBrand = doc.createElement("brand");
  eBrand.appendChild(doc.createTextNode(vehicle.brand));
         eVehicle.appendChild(eBrand);
        
         Element eModel = doc.createElement("model");
       eModel.appendChild(doc.createTextNode(vehicle.model));
          eVehicle.appendChild(eModel);

          Element eColour = doc.createElement("colour");
          eColour.appendChild(doc.createTextNode(vehicle.colour));
              eVehicle.appendChild(eColour);

              Element ePower = doc.createElement("power");
              ePower.appendChild(doc.createTextNode(vehicle.power));
              eVehicle.appendChild(ePower);


              Element eReg = doc.createElement("registration");
              eReg.appendChild(doc.createTextNode(vehicle.registration));
              eVehicle.appendChild(eReg);
}

              TransformerFactory tFactory = TransformerFactory.newInstance();
              Transformer transformer = tFactory.newTransformer();
              DOMSource source = new DOMSource(doc);
              StreamResult result = new StreamResult(new File("vehicles.xml"));
              transformer.transform(source, result);
     }catch(Exception e)
     {
        e.printStackTrace();
     }
 }


}

