import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class Fooldal {

    public Fooldal() {
        // ures
    }

    public static void oldalakHtml(String foFolder) {
        File file = new File(foFolder + "\\index.html");
        if (file.exists()) {
            System.out.println("File already exist");
        } else {
            FileWriter fileWriter = null;
            BufferedWriter bufferedWriter = null;
            try {
                fileWriter = new FileWriter(file);
                bufferedWriter = new BufferedWriter(fileWriter);

                String htmlPage = "<!DOCTYPE html>" +
                        "<html>" +
                        "<body>" +
                        "" +
                        "<h1><center><u><p><a href=\"./index.html\">Start Page</a></p></u></center></h1>"
                        +
                        "" +
                        "</body>" +
                        "</html>";

                bufferedWriter.write(htmlPage);

                bufferedWriter.append("<strong><h2>----------</h2></strong>");

                bufferedWriter.append("<strong><h3>Directories:</h3></strong>");
                String fname = "filenames.txt";
                List<String> sorok = FileUtils.readLines(fname);

                int db = 0;
                File dir = new File(foFolder);
                for (String sor : sorok) {
                    File f = new File(sor);
                    if (f.isDirectory() && db != 0) {
                        File subDir = new File(sor);
                        if (subDir.getParentFile().equals(dir)) {
                            int utolso = sor.lastIndexOf('\\') + 1;
                            int hossz = sor.length();
                            String jo = sor.substring(utolso, hossz);
                            // System.out.println(jo);
                            String subFile = sor;
                            Index.indexHtml(subFile);
                            // System.out.println(subFile);
                            subFile = subFile + "\\index.html";
                            // System.out.println(subFile);
                            bufferedWriter.append("<ul><li>");
                            String lowerPage = "<!DOCTYPE html>" +
                                    "<html>" +
                                    "<body>" +
                                    "" +
                                    "<h4><u><p><a href=\"" + subFile + "\">" + jo + "</a></p></u></h4>" +
                                    "" +
                                    "</body>" +
                                    "</html>";
                            bufferedWriter.write(lowerPage);
                            bufferedWriter.append("</li></ul></body></html>");
                        }
                    }
                    db++;
                }
                db = 0;

                bufferedWriter.append("<strong><h2>----------</h2></strong>");

                int talalat = 0;
                for (String sor : sorok) {
                    if (db != 0) {
                        File subDir = new File(sor);
                        if (subDir.getParentFile().equals(dir)) {
                            if (ImageCheck.imageFile(sor)) {
                                int hossz = sor.length();
                                int utolso = sor.lastIndexOf('\\') + 1;
                                String kep = sor.substring(utolso, hossz);
                                // System.out.println(kep);
                                bufferedWriter.append("<ul><li>");
                                bufferedWriter.append(kep);
                                bufferedWriter.append("</li></ul></body></html>");
                                talalat++;
                            }
                        }
                    }
                    db++;
                }
                if (talalat == 0) {
                    bufferedWriter.append("<strong><h2>Itt nem talalhato kep.</h2></strong>");
                }
                talalat = 0;

                bufferedWriter.append("<strong><h2>----------</h2></strong>");

                System.out.println("...");
                System.out.println("Az oldalak elkészültek.");
                System.out.println("A fooldal oldal elkészült.");
                bufferedWriter.flush();
                fileWriter.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    bufferedWriter.close();
                    fileWriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        }

    }

}