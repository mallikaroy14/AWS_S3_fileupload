package com.example.fileupload;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

//    @PostMapping("/create")
//    public ResponseEntity<Object> createProduct(){
//        return new ResponseEntity("working", HttpStatus.OK);
//    }


    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@RequestParam("productFile") MultipartFile productfile, @RequestParam String name, @RequestParam Long age) throws IOException {

        String[] flp = productfile.getOriginalFilename().split("\\.");

//        System.out.println( productfile.getContentType());
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
        s3Client.putObject("mallika-java-demo", "abc/productFile." + flp[flp.length - 1], productfile.getInputStream(), new ObjectMetadata());
        if (true) {
            Products p = new Products();
            p.setName(name);
            p.setAge(age);
            p.setImageUrl("https://mallika-java-demo.s3.us-west-2.amazonaws.com/practice/productFile.pdf");
        }
        return new ResponseEntity("working", HttpStatus.OK);
    }


}
