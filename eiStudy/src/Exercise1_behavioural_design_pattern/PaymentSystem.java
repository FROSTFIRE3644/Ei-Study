package Exercise1_behavioural_design_pattern;
//Strategy Interface
interface PaymentStrategy {
 void pay(int amount);
}

//Concrete Strategy 1 - Credit Card
class CreditCardPayment implements PaymentStrategy {
 private String cardNumber;

 public CreditCardPayment(String cardNumber) {
     this.cardNumber = cardNumber;
 }

 @Override
 public void pay(int amount) {
     System.out.println("Paid " + amount + " using Credit Card ending with " + cardNumber.substring(cardNumber.length() - 4));
 }
}

//Concrete Strategy 2 - PayPal
class PayPalPayment implements PaymentStrategy {
 private String email;

 public PayPalPayment(String email) {
     this.email = email;
 }

 @Override
 public void pay(int amount) {
     System.out.println("Paid " + amount + " using PayPal (Email: " + email + ")");
 }
}

//Context Class
class PaymentProcessor {
 private PaymentStrategy paymentStrategy;

 public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
     this.paymentStrategy = paymentStrategy;
 }

 public void processPayment(int amount) {
     if (paymentStrategy == null) {
         throw new IllegalStateException("Payment strategy not set.");
     }
     paymentStrategy.pay(amount);
 }
}

//Main Class to run Strategy Pattern Example
public class PaymentSystem {
 public static void main(String[] args) {
     PaymentProcessor processor = new PaymentProcessor();

     // Set strategy to Credit Card and make a payment
     processor.setPaymentStrategy(new CreditCardPayment("1234567812345678"));
     processor.processPayment(1000);

     // Switch to PayPal and make another payment
     processor.setPaymentStrategy(new PayPalPayment("user@example.com"));
     processor.processPayment(500);
 }
}
