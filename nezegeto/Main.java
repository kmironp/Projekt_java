import java.io.*;
import java.nio.file.*;
import java.util.stream.Stream;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.out.println("HIBA: Nem megfelelo szamu argot adott meg.");
            // "C:\\Users\\Biboy\\Dokumentumok\\ppj_kepek"
            // C:\\Users\\Biboy\\Dokumentumok\\SimCity\\ppj_kepek
            System.exit(1);
        }
        String konyvtar = args[0];
        File konyv = new File(konyvtar);
        if(!konyv.isDirectory()) {
            System.out.println("Ez nem Directory!");
            System.exit(2);
        }
         

        System.out.println("A program elkezdte a feldolgozást.");
        FileWriter myWriter = new FileWriter("filenames.txt");
        BufferedWriter info = new BufferedWriter(myWriter);
        // konyvtar -ra cserélni az elérési utat
        showDir(1, new File(konyvtar),
                myWriter, info);
        myWriter.close();

        System.out.println("...");

        String fname = "filenames.txt";
        List<String> sorok = FileUtils.readLines(fname);

        for (String sor : sorok) {
            File f = new File(sor);
            if (f.isDirectory()) {
                int utolso = sor.lastIndexOf('\\') + 1;
                int hossz = sor.length();
                String jo = sor.substring(utolso, hossz);
                // System.out.println(jo);
            }
            if (ImageCheck.imageFile(sor)) {
                int hossz = sor.length();
                int utolso = sor.lastIndexOf('\\') + 1;
                String kep = sor.substring(utolso, hossz);
                // System.out.println(kep);
            }
        }
        System.out.println("Oldalak letrehozasa.");
        System.out.println("...");

        String ffname = "filenames.txt";
        List<String> foSorok = FileUtils.readLines(ffname);

        int dbf = 0;
        for (String foSor : foSorok) {
            if (dbf == 0) {
                // System.out.println("bent vagyok");
                String foFolder = foSor;
                Fooldal.oldalakHtml(foFolder);
            }
            dbf++;
        }

        System.out.println("A program sikeresen lefutott.");
    }

    static void showDir(int indent, File file, FileWriter myWriter, BufferedWriter info) throws IOException {

        try {
            myWriter.write(file.getAbsolutePath() + "\n");
            info.newLine();
            // myWriter.write(file.getName() + "\n");
            info.newLine();
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    showDir(indent + 4, files[i], myWriter, info);
                }
            }
        } catch (IOException e) {
            throw new IOException("HIBA: Ez a könyvtár nem létezik vagy nem is könyvtár!");
        }

    }
}