import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import javax.swing.*;

class RProfiler {
    public static void main(String[] args){
        JFrame frame = new JFrame("Resume Profiler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setVisible(true);
    }

    public void Profiler() throws IOException{
        ArrayList<String> keyword = new ArrayList<String>();
        File resumeFile = new File("Files/Complete+Resume.pdf");
        int alIndex = 0;
        Boolean checker;
        PDDocument document = Loader.loadPDF(resumeFile);

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
    }
}
