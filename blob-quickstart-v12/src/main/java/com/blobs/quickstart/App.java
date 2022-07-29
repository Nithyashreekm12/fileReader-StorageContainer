package com.blobs.quickstart;

/**
 * Azure blob storage v12 SDK quickstart
 */
import com.azure.storage.blob.*;
import com.azure.storage.blob.models.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        try {
        System.out.println( "Hello World!" );
        String connectStr = "DefaultEndpointsProtocol=https;AccountName=nptkafkastorageaccount;AccountKey=xhn8LKpFrorHR6bjafehjcVMDTDNQTGLNTFTDzb3HL9keHk0Pll3dA04Tn9OXwvhNMHy5iobMkC2+ASt34o3ww==;EndpointSuffix=core.windows.net";

        // Create a BlobServiceClient object which will be used to create a container client
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();

        //Create a unique name for the container

        String containerName = "quickstartblobs2"; 
        if(!blobServiceClient.getBlobContainerClient(containerName).exists()) {


        } else {
        // Create the container and return a container client object
        
        BlobContainerClient containerClient  = blobServiceClient.getBlobContainerClient(containerName);
    

        // Create a local file in the ./data/ directory for uploading and downloading
        String localPath = "./data/";
        String fileName = "quickstart" + java.util.UUID.randomUUID() + ".txt";
        Path localFile = FileSystems.getDefault().getPath(fileName);
        System.out.println(localFile);

        // Write text to the file
        
        // FileWriter writer = new FileWriter(localFile, true);
        // writer.write("Hello, World!");
        // writer.close();
        String message = "Hello WOrld";
        Files.write(localFile, message.getBytes(StandardCharsets.UTF_8),
        StandardOpenOption.CREATE,
        StandardOpenOption.APPEND);

        // Get a reference to a blob
        BlobClient blobClient = containerClient.getBlobClient(localFile.toFile().toString());

        System.out.println("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());

        // Upload the blob
        blobClient.uploadFromFile(localFile.toFile().getAbsolutePath());

        System.out.println("\nListing blobs...");

        // List the blob(s) in the container.
        for (BlobItem blobItem : containerClient.listBlobs()) {
            System.out.println("\t" + blobItem.getName());
        }
    }
    } catch(Exception e) {
        e.printStackTrace();
    }
        // Download the blob to a local file
        // Append the string "DOWNLOAD" before the .txt extension so that you can see both files.
        // String downloadFileName = fileName.replace(".txt", "DOWNLOAD.txt");
        // File downloadedFile = new File(localPath + downloadFileName);

        // System.out.println("\nDownloading blob to\n\t " + localPath + downloadFileName);

        // blobClient.downloadToFile(localPath + downloadFileName);

        // // Clean up
        // System.out.println("\nPress the Enter key to begin clean up");
        // System.console().readLine();

        // System.out.println("Deleting blob container...");
        // containerClient.delete();

        // System.out.println("Deleting the local source and downloaded files...");
        // localFile.delete();
        // downloadedFile.delete();

        // System.out.println("Done");


    }
}
