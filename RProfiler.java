import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import javax.swing.*;
import java.awt.*;

class RProfiler {
    public static void main(String[] args){
        JFrame frame = new JFrame("Resume Profiler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 200);
        frame.setLayout(new GridLayout(3, 1));

        JPanel upPanel = new JPanel();
        JLabel upLabel = new JLabel("Upload Resume you want to scan");
        JButton upButton = new JButton("Upload Resume");
        upPanel.add(upLabel);
        upPanel.add(upButton);

        JPanel keyPanel = new JPanel();
        JLabel keyLabel = new JLabel("Enter the Keywords you want to scan(Seperated by commas(,))");
        JTextField keyTxt = new JTextField(40);
        keyPanel.add(keyLabel);
        keyPanel.add(keyTxt);

        JPanel resPanel = new JPanel();
        JLabel resLabel = new JLabel("Are the Keywords found?");
        JLabel resYLabel = new JLabel("Yes");
        JLabel resNLabel = new JLabel("No");
        resPanel.add(resLabel);
        resPanel.add(resYLabel);
        resPanel.add(resNLabel);
        resYLabel.setVisible(false);
        resNLabel.setVisible(false);

        frame.getContentPane().add(upPanel);
        frame.getContentPane().add(keyPanel);
        frame.getContentPane().add(resPanel);
        frame.setVisible(true);
    }

    public void Profiler() throws IOException{
        ArrayList<String> keyword = new ArrayList<String>();
        File resumeFile = new File("Files/");
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
