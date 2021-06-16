package pojava.projekt.generator.sprawozdan;


public class Section extends TexElement {

	/*TO DO: sectionText handling - communication between sectionText and SectionDialog textArea*/
	String sectionText;
	public Section(String t, String sT) {
		super();
		title = t;
		sectionText = sT;
	}

	@Override
	String makeElementCode() {
		String sectionCode = "\n\\section{" +
							title +
							"}" +
							"\n"+
							sectionText +
							"\n";
		return sectionCode;
	}

}
