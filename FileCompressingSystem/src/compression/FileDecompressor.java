package compression;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class FileDecompressor {
    public static void decompressFile(String compressedFilePath) throws IOException {
        if (!compressedFilePath.endsWith(".gz")) {
            System.out.println("Invalid compressed file format.");
            return;
        }

        String originalFilePath = compressedFilePath.replace(".gz", ""); // Remove .gz to get original name

        try (FileInputStream fis = new FileInputStream(compressedFilePath);
             GZIPInputStream gzipIn = new GZIPInputStream(fis);
             FileOutputStream fos = new FileOutputStream(originalFilePath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = gzipIn.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }

        System.out.println("Decompression Successful! File saved as: " + originalFilePath);
    }
}
