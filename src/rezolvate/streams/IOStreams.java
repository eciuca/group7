package rezolvate.streams;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class IOStreams {

    public static final String INPUT_TXT = "input.txt";

    public static void main(String[] args) throws IOException {
        readFromFileWithFIS();
//        writeToFileWithFOS();
//        copyFileWithFISandFOS();
//        readFromFileWithFileReader();
//        writeToFileWithFileWriter();
//        readFromFileNIO();
//        BufferedReader();
    }

    //region INPUT/OUTPUT STREAMS
    private static void copyFileWithFISandFOS() throws IOException {
        FileInputStream fis = new FileInputStream("input.txt");
        FileOutputStream fos = new FileOutputStream("input-copy.txt");

        int data = fis.read();

        while (data != -1) {
            fos.write(data);
            data = fis.read();
        }

        fis.close();
        fos.close();
    }

    private static void writeToFileWithFOS() throws IOException {
        FileOutputStream fos = new FileOutputStream("output.txt");

        int a = 65;
        int b = 70;

        fos.write(a);
        fos.write(b);

        fos.close();
    }

    @SuppressWarnings("Duplicates")
    private static void readFromFileWithFIS() throws IOException {
        FileInputStream fis = new FileInputStream("input.txt");


//        Scanner x = new Scanner(System.in);
//        DataInputStream dataInputStream = new DataInputStream(fis);
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
//        ByteArrayInputStream bais = new ByteArrayInputStream()

        ObjectInputStream ois = new ObjectInputStream(fis);
        SequenceInputStream s = new SequenceInputStream(fis, new FileInputStream("input-copy.txt"));

        int data = s.read();

        while (data != -1) {
            System.out.print((char) data);
            data = s.read();
        }

        s.close();
    }

    @SuppressWarnings("Duplicates")
    private static void readFromFileWithFileReader() throws IOException {
        FileReader fis = new FileReader("input.txt");

        int data = fis.read();

        while (data != -1) {
            System.out.print((char) data);
            data = fis.read();
        }

        fis.close();
    }

    private static void writeToFileWithFileWriter() throws IOException {
        FileWriter fos = new FileWriter("output.txt");

        int a = 65;
        int b = 70;

        fos.write(a + " ");
        fos.write(b + " ");

        fos.close();
    }
    //endregion INPUT/OUTPUT STREAMS

    private static void readFromFileNIO() {
        Path path = Paths.get("input.txt");

        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void BufferedReader() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(INPUT_TXT));

        while(bufferedReader.ready()) {
            System.out.println(bufferedReader.readLine());
        }
    }
}
