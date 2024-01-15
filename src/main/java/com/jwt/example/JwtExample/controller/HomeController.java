package com.jwt.example.JwtExample.controller;

import com.jwt.example.JwtExample.entities.User;
import com.jwt.example.JwtExample.helper.FileUploadHelper;
import com.jwt.example.JwtExample.helper.ParseWsdl;
import com.jwt.example.JwtExample.service.UserService;
import org.apache.cxf.BusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.wsdl.WSDLException;
import javax.xml.namespace.QName;
import java.lang.reflect.Parameter;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileUploadHelper fileUploadHelper;
    @Autowired
    private ParseWsdl parseWsdl;
    @GetMapping("/users")
    public List<User>  getUser(){
        System.out.println("Getting user!!");
        return userService.getUsers();
    }

    @GetMapping("/user")
    public String getloggedUsername(Principal principal){
        return principal.getName();
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<String> fileUpload(@RequestParam("file")MultipartFile file){
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
            }

            //file upload code
            boolean uploaded = fileUploadHelper.uploadFile(file);
            if(uploaded){
                return ResponseEntity.ok("File uploaded successfully!!");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed!!");
    }

    @GetMapping("/parseFile")
    public ResponseEntity<Map<String, QName>> parseFile() throws WSDLException, BusException {
        Map<String, QName> map = parseWsdl.getWsdl();
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
}
