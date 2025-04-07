package compression;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class FileCompressor {
    public static void compressFile(String filePath) throws IOException {
        File inputFile = new File(filePath);
        String compressedFilePath = filePath + ".gz"; // Keeps original extension
        
        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(compressedFilePath);
             GZIPOutputStream gzipOut = new GZIPOutputStream(fos)) {
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                gzipOut.write(buffer, 0, bytesRead);
            }
        }

        System.out.println("Compression Successful! File saved as: " + compressedFilePath);
    }
}
