import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import javax.swing.*;
import java.awt.*;

class RProfiler {
    static File resumeFile;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Resume Profiler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 200);
        frame.setLayout(new GridLayout(3, 1));

        JPanel upPanel = new JPanel();
        JLabel upLbl = new JLabel("Upload Resume you want to scan");
        JButton upBtn = new JButton("Upload Resume");
        upPanel.add(upLbl);
        upPanel.add(upBtn);

        JPanel keyPanel = new JPanel();
        JLabel keyLbl = new JLabel("Enter the Keywords you want to scan(Seperated by commas(,))");
        JTextField keyTxt = new JTextField(40);
        JButton keyBtn = new JButton("Enter");
        keyBtn.setEnabled(false);
        keyPanel.add(keyLbl);
        keyPanel.add(keyTxt);
        keyPanel.add(keyBtn);

        JPanel resPanel = new JPanel();

        frame.getContentPane().add(upPanel);
        frame.getContentPane().add(keyPanel);
        frame.getContentPane().add(resPanel);
        frame.setVisible(true);

        upBtn.addActionListener(e -> {
            JFileChooser file = new JFileChooser();
            int s = file.showOpenDialog(null);
            if (s == JFileChooser.APPROVE_OPTION) {
                resumeFile = new File(file.getSelectedFile().getAbsolutePath());
                keyBtn.setEnabled(true);
            }
        });

        keyBtn.addActionListener(e -> {
            resPanel.removeAll();
            try {
                ArrayList<String> keyword;
                int alIndex = 0;
                Boolean checker;
                PDDocument document = Loader.loadPDF(resumeFile);

                String keyString = keyTxt.getText();
                String[] key = keyString.split(",");
                List<String> keyList = Arrays.asList(key);
                keyword = new ArrayList<String>(keyList);

                JLabel resLbl = new JLabel();

                for (String i : keyword) {
                    PDFTextStripper pdfStripper = new PDFTextStripper();
                    String text = pdfStripper.getText(document);
                    checker = text.contains(keyword.get(alIndex));
                    if (checker == true) {
                        resLbl = new JLabel(keyword.get(alIndex) + " is found");
                        resPanel.add(resLbl);
                    } else {
                        resLbl = new JLabel(keyword.get(alIndex) + " is not found");
                        resPanel.add(resLbl);
                    }
                    resPanel.revalidate();
                    resPanel.repaint();
                    alIndex++;
                }

                document.close();
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        });
    }
}
