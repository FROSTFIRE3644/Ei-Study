package Exercise1_creational_design_patterns;
//Singleton Logger Class
class Logger {
 // Static variable to hold the single instance
 private static Logger instance;

 // Private constructor to prevent instantiation
 private Logger() {
     // Empty constructor
 }

 // Static method to get the single instance of the logger
 public static Logger getInstance() {
     if (instance == null) {
         instance = new Logger();
     }
     return instance;
 }

 // Method to log messages
 public void log(String message) {
     System.out.println("Log: " + message);
 }
}

//Main Class to Test Singleton Pattern
public class SingletonPattern {
 public static void main(String[] args) {
     // Get the single instance of the logger
     Logger logger1 = Logger.getInstance();
     logger1.log("This is the first log message.");  // Output: Log: This is the first log message.

     // Try to get another instance of the logger
     Logger logger2 = Logger.getInstance();
     logger2.log("This is the second log message.");  // Output: Log: This is the second log message.

     // Check if both instances are the same
     if (logger1 == logger2) {
         System.out.println("Both logger instances are the same.");  // Output: Both logger instances are the same.
     }
 }
}
