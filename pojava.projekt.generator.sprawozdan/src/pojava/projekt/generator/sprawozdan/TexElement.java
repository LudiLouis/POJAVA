package pojava.projekt.generator.sprawozdan;


import javax.swing.JTextArea;

public abstract class TexElement {

	String title;
	String elementCode;
	
	public TexElement() {
		title = "title";
	}

	String getElementCode()
	{
		return elementCode;
	}
	// Takes code of an element and paste it to codeField in MainInterface class
	// Final method - there is no need to enable to modificate this method by subclasses
	public final void pasteToTextFrame(JTextArea codeField, String elementCode) { //Paste code of the element to code field before the caret position,
		//if user selected some text, it would replace this text with element code 
		codeField.replaceSelection("");
		codeField.insert(elementCode, codeField.getCaretPosition());
	}
	
	// Every TeX element should be able to generate specified TeX code
	 abstract String makeElementCode();
}
