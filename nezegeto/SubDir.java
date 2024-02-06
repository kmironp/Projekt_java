import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class SubDir {

    public SubDir() {
        // ures
    }

    public static void subDir(String SubFile, int al_n) {
        File file = new File(SubFile + "\\index.html");
        if (file.exists()) {
            System.out.println("File already exist");
        } else {
            FileWriter fileWriter = null;
            BufferedWriter bufferedWriter = null;
            try {
                fileWriter = new FileWriter(file);
                bufferedWriter = new BufferedWriter(fileWriter);

                String al = "../";
                String alap = "../index.html";
                String uj = "";
                for (int i = 0; i < al_n; i++) {
                    uj = uj + al;
                }
                uj = uj + alap;

                String htmlPage = "<!DOCTYPE html>" +
                        "<html>" +
                        "<body>" +
                        "" +
                        "<h1><center><u><p><a href=\"" + uj + "\">Start Page</a></p></u></center></h1>" +
                        "" +
                        "</body>" +
                        "</html>";

                bufferedWriter.write(htmlPage);

                bufferedWriter.append("<strong><h2>----------</h2></strong>");

                bufferedWriter.append("<strong><h3>Directories:</h3></strong>");
                String fname = "filenames.txt";
                List<String> sorok = FileUtils.readLines(fname);

                bufferedWriter.append("<ul><li>");
                String upperPage = "<!DOCTYPE html>" +
                        "<html>" +
                        "<body>" +
                        "" +
                        "<h4><u><p><a href=\"../index.html\"> << </a></p></u></h4>" +
                        "" +
                        "</body>" +
                        "</html>";
                bufferedWriter.write(upperPage);
                bufferedWriter.append("</li></ul></body></html>");

                int db = 0;
                // int al_n = 1;
                int al_k = 0;
                File dir = new File(SubFile);
                for (String sor : sorok) {
                    File f = new File(sor);
                    if (f.isDirectory() && db != 0) {
                        File subDir = new File(sor);
                        if (subDir.getParentFile().equals(dir)) {
                            String subFile = sor;
                            SubDir.subDir(subFile, al_n);
                            subFile = subFile + "\\index.html";
                            System.out.println(subFile);
                            al_k++;
                            int utolso = sor.lastIndexOf('\\') + 1;
                            int hossz = sor.length();
                            String jo = sor.substring(utolso, hossz);
                            // System.out.println(jo);
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
                int sum = 0;
                for (String sor : sorok) {
                    if (db != 0) {
                        File subDir = new File(sor);
                        if (subDir.getParentFile().equals(dir)) {
                            if (ImageCheck.imageFile(sor)) {
                                sum++;
                            }
                        }
                    }
                    db++;
                }

                bufferedWriter.append("<strong><h2>----------</h2></strong>");
                if (sum == 0) {
                    bufferedWriter.append("<strong><h2>Itt nem talalhato kep.</h2></strong>");
                }

                db = 0;
                int talalat = 1;
                for (String sor : sorok) {
                    if (db != 0) {
                        File subDir = new File(sor);
                        if (subDir.getParentFile().equals(dir)) {
                            if (ImageCheck.imageFile(sor)) {
                                int hossz = sor.length();
                                int utolso = sor.lastIndexOf('\\') + 1;
                                String kep = sor.substring(utolso, hossz);
                                // System.out.println(kep);
                                String link = sor.substring(0, hossz - 4);
                                String fileImage = link + ".html";
                                String folderKep = sor.substring(0, utolso);
                                // System.out.println(folderKep);
                                Image.imageHtml(fileImage, kep, al_k, talalat, sum, folderKep);
                                bufferedWriter.append("<ul><li>");
                                String kepPage = "<!DOCTYPE html>" +
                                        "<html>" +
                                        "<body>" +
                                        "" +
                                        "<h4><u><p><a href=\"" + link + ".html\">" + kep
                                        + "</a></p></u></h4>" +
                                        "" +
                                        "</body>" +
                                        "</html>";
                                bufferedWriter.write(kepPage);
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

                System.out.println("A html oldalak elkészítése az almappakban is...");
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