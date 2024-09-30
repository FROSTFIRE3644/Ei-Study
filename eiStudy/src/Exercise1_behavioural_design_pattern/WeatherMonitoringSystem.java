package Exercise1_behavioural_design_pattern;

import java.util.*;

//Subject Interface
interface WeatherStation {
 void registerObserver(DisplayUnit observer);
 void removeObserver(DisplayUnit observer);
 void notifyObservers();
}

//Concrete Subject
class ConcreteWeatherStation implements WeatherStation {
 private List<DisplayUnit> observers;
 private float temperature;

 public ConcreteWeatherStation() {
     observers = new ArrayList<>();
 }

 @Override
 public void registerObserver(DisplayUnit observer) {
     observers.add(observer);
 }

 @Override
 public void removeObserver(DisplayUnit observer) {
     observers.remove(observer);
 }

 @Override
 public void notifyObservers() {
     for (DisplayUnit observer : observers) {
         observer.update(temperature);
     }
 }

 public void setTemperature(float temperature) {
     this.temperature = temperature;
     notifyObservers();
 }
}

//Observer Interface
interface DisplayUnit {
 void update(float temperature);
}

//Concrete Observer 1
class PhoneDisplay implements DisplayUnit {
 @Override
 public void update(float temperature) {
     System.out.println("Phone Display: Temperature updated to " + temperature + "°C");
 }
}

//Concrete Observer 2
class LCDPanelDisplay implements DisplayUnit {
 @Override
 public void update(float temperature) {
     System.out.println("LCD Panel: Temperature updated to " + temperature + "°C");
 }
}

//Main Class to run Observer Pattern Example
public class WeatherMonitoringSystem {
 public static void main(String[] args) {
     ConcreteWeatherStation station = new ConcreteWeatherStation();

     DisplayUnit phoneDisplay = new PhoneDisplay();
     DisplayUnit lcdDisplay = new LCDPanelDisplay();

     station.registerObserver(phoneDisplay);
     station.registerObserver(lcdDisplay);

     station.setTemperature(25.5f);  // All observers get notified

     station.removeObserver(phoneDisplay);
     station.setTemperature(30.0f);  // Only LCD Panel gets notified
 }
}
