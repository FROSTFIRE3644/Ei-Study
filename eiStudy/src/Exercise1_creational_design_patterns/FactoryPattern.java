package Exercise1_creational_design_patterns;
//Vehicle Interface
interface Vehicle {
 void create();
}

//Concrete Classes for Vehicles
class Car implements Vehicle {
 @Override
 public void create() {
     System.out.println("Car created.");
 }
}

class Bike implements Vehicle {
 @Override
 public void create() {
     System.out.println("Bike created.");
 }
}

class Truck implements Vehicle {
 @Override
 public void create() {
     System.out.println("Truck created.");
 }
}

//Factory Class to Create Vehicle Objects
class VehicleFactory {
 // Factory method to create the vehicle based on type
 public Vehicle getVehicle(String vehicleType) {
     if (vehicleType == null) {
         return null;
     }
     if (vehicleType.equalsIgnoreCase("CAR")) {
         return new Car();
     } else if (vehicleType.equalsIgnoreCase("BIKE")) {
         return new Bike();
     } else if (vehicleType.equalsIgnoreCase("TRUCK")) {
         return new Truck();
     }
     return null;
 }
}

//Main Class to Test Factory Pattern
public class FactoryPattern {
 public static void main(String[] args) {
     VehicleFactory vehicleFactory = new VehicleFactory();

     // Get an object of Car and call its create method.
     Vehicle vehicle1 = vehicleFactory.getVehicle("CAR");
     vehicle1.create();  // Output: Car created.

     // Get an object of Bike and call its create method.
     Vehicle vehicle2 = vehicleFactory.getVehicle("BIKE");
     vehicle2.create();  // Output: Bike created.

     // Get an object of Truck and call its create method.
     Vehicle vehicle3 = vehicleFactory.getVehicle("TRUCK");
     vehicle3.create();  // Output: Truck created.
 }
}
