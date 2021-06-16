package pojava.projekt.generator.sprawozdan;

public class Picture extends TexElement {

	String picture;
	public Picture(String picName) {
		picture = picName;
	}

	@Override
	String makeElementCode() {
		String pictureCode = "\n\\begin{figure}[H]\n" +
				"\\includegraphics[scale=1]{"+ picture +"}\n" +
				"\\caption{" +
				title +
				"}" +
				"\n\\end{figure}\n";
		return pictureCode;
	}

}
