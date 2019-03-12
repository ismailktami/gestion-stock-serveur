package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.entity.Produit;
import com.example.demo.service.ProduitRespository;

@SpringBootApplication
public class GestionSotckServeurApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=	SpringApplication.run(GestionSotckServeurApplication.class, args);
		ProduitRespository prorep=ctx.getBean(ProduitRespository.class);
		Produit p=new Produit("iss",4444,4444);
		try {
			p.setImageProduit("C:\\Users\\ktami\\OneDrive\\FromYourPhone\\IMG_20190121_133129_803.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prorep.save(p);
}
}
