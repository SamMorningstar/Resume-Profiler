import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

class RProfiler {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        int counter;
        ArrayList<String> keyword = new ArrayList<String>();
        File resumeFile = new File("Files/Complete+Resume.pdf");
        int alIndex = 0;
        Boolean checker;
        PDDocument document = Loader.loadPDF(resumeFile);

        System.out.println("Enter how many Keywords you want to find:");
        counter = s.nextInt();

        for (int i = 1; i <= counter; i++) {
            System.out.println("Enter keyword " + i + ":");
            String K = s.next();
            keyword.add(K);
        }

        System.out.println(keyword);

        for (String i : keyword) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);
            checker = text.contains(keyword.get(alIndex));
            if (checker == true) {
                System.out.println(i + " Found");
            } else {
                System.out.println(i + " Not Found");
            }
            alIndex++;
        }

        document.close();
        s.close();
    }
}