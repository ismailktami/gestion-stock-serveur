package com.example.demo;

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
		prorep.save(new Produit("Ref1",120,56));
		prorep.save(new Produit("Ref2",142,56));
		prorep.save(new Produit("Ref3",44,56));
		prorep.save(new Produit("Ref4",12440,56));

	}

}
