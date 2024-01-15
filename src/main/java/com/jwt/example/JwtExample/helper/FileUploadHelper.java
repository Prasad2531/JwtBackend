package com.jwt.example.JwtExample.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {
    public static String UPLOAD_DIR = "C:\\Users\\Wissen\\Development\\Spring\\Jwt\\JwtExample\\src\\main\\resources\\static\\wsdlFiles";
    public static String filename="";

    public boolean uploadFile(MultipartFile file){
        boolean f = false;
        try{
//            InputStream is = file.getInputStream();
//            byte[] data=new byte[is.available()];
//            is.read(data);
//            //write
//            FileOutputStream os = new FileOutputStream(UPLOAD_DIR+ File.separator+file.getOriginalFilename());
//            os.write(data);
//            os.flush();
//            os.close();
            //Files.copy(source,path,options)
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            filename=file.getOriginalFilename();
            f=true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return f;
    }

    public static String setFile(){
        System.out.println(UPLOAD_DIR+File.separator+filename);
        return UPLOAD_DIR+File.separator+filename;
    }


}
