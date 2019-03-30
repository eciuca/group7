package live.streams;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
public class StreamStuff {

    public static void main(String[] args) throws IOException {
//        writeToFileWithFOS();
        writeToFileWithFileWriter();


    }



    public static void writeToFileWithFOS() throws IOException {
        // creaza legatura intre Java si fisierul output.txt
        FileOutputStream fos = new FileOutputStream("output-gr7.txt");

        int a = 65;
        int b = 66;


        fos.write(String.valueOf(a).getBytes()); // Se afiseaza 65
        fos.write(a); // Se afiseaza A
        fos.write(b); // Se afiseaza B

        // inchidem stream-ul
        fos.close();
    }

    private static void writeToFileWithFileWriter() throws IOException {
        FileWriter fos = new FileWriter("output.txt");

        int a = 65;
        int b = 70;

        fos.write(a + " ");
        fos.write(b + " ");

        fos.close();
    }




}
