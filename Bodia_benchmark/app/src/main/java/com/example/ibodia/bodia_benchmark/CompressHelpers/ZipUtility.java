package com.example.ibodia.bodia_benchmark.CompressHelpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by ibodia on 5/5/2017.
 */

public class ZipUtility {

    List<String> fileList;

    public String OUTPUT_ZIP_FILE;
    public String SOURCE_FOLDER;

    public ZipUtility()
    {
        fileList = new ArrayList<String>();
    }

    public void zipFileOrFolder(String zipFile){

        byte[] buffer = new byte[1024];
        try{
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            System.out.println("Output to Zip : " + zipFile);

            for(String file : this.fileList){

                System.out.println("File Added : " + file);
                ZipEntry ze= new ZipEntry(file);
                zos.putNextEntry(ze);

                FileInputStream in = new FileInputStream(SOURCE_FOLDER + File.separator + file);

                int len;
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                in.close();
            }

            zos.closeEntry();
            zos.close();

            System.out.println("Done");
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void generateFileList(File node){

        if(node.isFile()){
            fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
        }

        if(node.isDirectory()){
            String[] subNote = node.list();
            for(String filename : subNote){
                generateFileList(new File(node, filename));
            }
        }
    }

    private String generateZipEntry(String file){
        return file.substring(SOURCE_FOLDER.length()+1, file.length());
    }
}