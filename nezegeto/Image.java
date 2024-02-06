import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class Image {

    public Image() {
        // ures
    }

    public static void imageHtml(String fileImage, String kepnev, int al_n, int talalat, int sum, String folderKep) {
        File file = new File(fileImage);
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

                bufferedWriter.append("<ul><li>");
                String indexPage = "<!DOCTYPE html>" +
                        "<html>" +
                        "<body>" +
                        "" +
                        "<h4><u><p><a href=\"index.html\"> ^^ </a></p></u></h4>" +
                        "" +
                        "</body>" +
                        "</html>";
                bufferedWriter.write(indexPage);
                bufferedWriter.append("</li></ul></body></html>");

                String fname = "filenames.txt";
                List<String> sorok = FileUtils.readLines(fname);
                File dir = new File(folderKep);

                int i = 0;
                int db = 0;
                if (talalat == 1) {
                    int hossz = kepnev.length();
                    String elso = kepnev.substring(0, hossz - 3);
                    for (String sor : sorok) {
                        if (db != 0) {
                            File subDir = new File(sor);
                            if (subDir.getParentFile().equals(dir)) {
                                if (ImageCheck.imageFile(sor)) {
                                    if (i - talalat == 0) {
                                        int hossz_kovi = sor.length();
                                        int utolso = sor.lastIndexOf('\\') + 1;
                                        String kep = sor.substring(utolso, hossz_kovi - 3);

                                        String kepOldalE = "<!DOCTYPE html>" +
                                                "<html>" +
                                                "<body>" +
                                                "" +
                                                "<h3><u><p><a href=\"" + elso + "html\"> << </a> " + kepnev
                                                + " <a href=\"" + kep + "html\"> >> </a></p></u></h3>" +
                                                "" +
                                                "</body>" +
                                                "</html>";
                                        bufferedWriter.write(kepOldalE);
                                        String kepek = "<!DOCTYPE html>" +
                                                "<html>" +
                                                "<body>" +
                                                "" +
                                                "<a href=\"" + kep + "html\">" +
                                                "<img src=\"" + kepnev + "\" style=\"width:320px; height:200px;\">" +
                                                "" +
                                                "</body>" +
                                                "</html>";
                                        bufferedWriter.write(kepek);
                                    }
                                    i++;
                                }
                            }
                        }
                        db++;
                    }
                }
                i = 0;
                db = 0;

                if (talalat == sum) {
                    int hossz = kepnev.length();
                    String utolso_kep = kepnev.substring(0, hossz - 3);
                    for (String sor : sorok) {
                        if (db != 0) {
                            File subDir = new File(sor);
                            if (subDir.getParentFile().equals(dir)) {
                                if (ImageCheck.imageFile(sor)) {
                                    if (i - talalat == -2) {
                                        int hossz_elozo = sor.length();
                                        int utolso = sor.lastIndexOf('\\') + 1;
                                        String kep = sor.substring(utolso, hossz_elozo - 3);

                                        String kepOldalU = "<!DOCTYPE html>" +
                                                "<html>" +
                                                "<body>" +
                                                "" +
                                                "<h3><u><p><a href=\"" + kep + "html\"> << </a> " + kepnev
                                                + " <a href=\"" + utolso_kep + "html\"> >> </a></p></u></h3>" +
                                                "" +
                                                "</body>" +
                                                "</html>";
                                        bufferedWriter.write(kepOldalU);
                                        String kepek = "<!DOCTYPE html>" +
                                                "<html>" +
                                                "<body>" +
                                                "" +
                                                "<a href=\"" + utolso_kep + "html\">" +
                                                "<img src=\"" + kepnev + "\" style=\"width:320px; height:200px;\">" +
                                                "" +
                                                "</body>" +
                                                "</html>";
                                        bufferedWriter.write(kepek);
                                    }
                                    i++;
                                }
                            }
                        }
                        db++;
                    }

                }

                db = 0;
                i = 0;

                String kep_e_f = "";
                String kep_u_f = "";
                int j;
                j = talalat - 1;

                if (talalat > j && talalat < sum) {
                    for (String sor : sorok) {
                        if (db != 0 & talalat > j && talalat < sum) { // file-ba kimenteni és onnan elenőrizni ha
                                                                      // nincs
                                                                      // benne csinalja

                            File subDir = new File(sor);
                            if (subDir.getParentFile().equals(dir)) {
                                if (ImageCheck.imageFile(sor)) {
                                    if (i - talalat == -2) {
                                        int hossz_elozo = sor.length();
                                        int utolso = sor.lastIndexOf('\\') + 1;
                                        String kep_e = sor.substring(utolso, hossz_elozo - 3);
                                        kep_e_f = kep_e;
                                    }
                                    if (i - talalat == 0) {
                                        int hossz_kovi = sor.length();
                                        int utolso = sor.lastIndexOf('\\') + 1;
                                        String kep_u = sor.substring(utolso, hossz_kovi - 3);
                                        kep_u_f = kep_u;
                                    }
                                    if (kep_e_f != "" && kep_u_f != "") {
                                        String kepOldalK = "<!DOCTYPE html>" +
                                                "<html>" +
                                                "<body>" +
                                                "" +
                                                "<h3><u><p><a href=\"" + kep_e_f + "html\"> << </a> " + kepnev
                                                + " <a href=\"" + kep_u_f + "html\"> >> </a></p></u></h3>" +
                                                "" +
                                                "</body>" +
                                                "</html>";
                                        bufferedWriter.write(kepOldalK);
                                        String kepek = "<!DOCTYPE html>" +
                                                "<html>" +
                                                "<body>" +
                                                "" +
                                                "<a href=\"" + kep_u_f + "html\">" +
                                                "<img src=\"" + kepnev + "\" style=\"width:320px; height:200px;\">" +
                                                "" +
                                                "</body>" +
                                                "</html>";
                                        bufferedWriter.write(kepek);
                                        kep_e_f = "";
                                        kep_u_f = "";
                                    }
                                    i++;
                                }
                            }
                        }

                        db++;
                    }

                }

                System.out.println("A kepet tartalmazo html oldalak keszitese...");
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