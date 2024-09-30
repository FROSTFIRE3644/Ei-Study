package Exercise1_structural_design_patterns;
//TextEditor Interface (Component)
interface TextEditor {
 String formatText();
}

//Concrete Component for plain text
class PlainText implements TextEditor {
 private String text;

 public PlainText(String text) {
     this.text = text;
 }

 @Override
 public String formatText() {
     return text;
 }
}

//Abstract Decorator
abstract class TextDecorator implements TextEditor {
 protected TextEditor textEditor;

 public TextDecorator(TextEditor textEditor) {
     this.textEditor = textEditor;
 }

 @Override
 public String formatText() {
     return textEditor.formatText();
 }
}

//Concrete Decorator for Bold
class BoldTextDecorator extends TextDecorator {
 public BoldTextDecorator(TextEditor textEditor) {
     super(textEditor);
 }

 @Override
 public String formatText() {
     return "<b>" + super.formatText() + "</b>";
 }
}

//Concrete Decorator for Italic
class ItalicTextDecorator extends TextDecorator {
 public ItalicTextDecorator(TextEditor textEditor) {
     super(textEditor);
 }

 @Override
 public String formatText() {
     return "<i>" + super.formatText() + "</i>";
 }
}

//Concrete Decorator for Underline
class UnderlineTextDecorator extends TextDecorator {
 public UnderlineTextDecorator(TextEditor textEditor) {
     super(textEditor);
 }

 @Override
 public String formatText() {
     return "<u>" + super.formatText() + "</u>";
 }
}

//Main Class to run Decorator Pattern Example
public class DecoratorPattern {
 public static void main(String[] args) {
     // Plain text
     TextEditor plainText = new PlainText("Hello, World!");

     // Add bold feature
     TextEditor boldText = new BoldTextDecorator(plainText);
     System.out.println(boldText.formatText());  // Output: <b>Hello, World!</b>

     // Add italic feature
     TextEditor italicText = new ItalicTextDecorator(boldText);
     System.out.println(italicText.formatText());  // Output: <i><b>Hello, World!</b></i>

     // Add underline feature
     TextEditor underlineText = new UnderlineTextDecorator(italicText);
     System.out.println(underlineText.formatText());  // Output: <u><i><b>Hello, World!</b></i></u>
 }
}
