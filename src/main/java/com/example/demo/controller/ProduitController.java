package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Produit;
import com.example.demo.service.IProduitService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/produit")
@CrossOrigin
public class ProduitController {
@Autowired
private IProduitService produitService;
@Autowired ServletContext context;
@GetMapping
@JsonInclude(value=Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public List<Produit> produits(){
	return produitService.getProduits();
}


@GetMapping(value="/chercherProduits")
public Page<Produit>  finProduits(
	@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size
		) {
	return produitService.chercherProduits(new PageRequest(page, size));
	
}

@CrossOrigin("*")
@GetMapping(value="/image")
public ResponseEntity<String>  getImagByRef(@RequestParam(name="ref",defaultValue="0")Long ref) {
	String encodage=Base64.getEncoder().encodeToString(produitService.getImage(ref));
	String image="data:image/jpg;base64, "+encodage;
	return new ResponseEntity<String>(image,HttpStatus.OK);
}





@GetMapping(value="/chercherByMc")
public Page<Produit>  
chercherProduits(@RequestParam(name="mc",defaultValue="")String mc,@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size
		) {
	return produitService.chercherByMc("%"+mc+"%",new PageRequest(page, size));
	
}




@PostMapping
public void addProduit(@RequestBody Produit p) {
	produitService.addProduit(p);
	System.out.println(p.getRef() +"  produit ajouter");

}


@PostMapping(value="/saveProduit")
public ResponseEntity<Produit> saveProduit(@RequestParam("file") MultipartFile file, @RequestParam("produit") String produit ) throws JsonParseException, JsonMappingException, IOException {
	Produit p=new ObjectMapper().readValue(produit,Produit.class);
	p.setImage(file.getBytes());
	if(produitService.addProduit(p)) {
		System.out.println("  produit ajouter");
		return new ResponseEntity<Produit>(p,HttpStatus.OK);
	}
	else {
		System.out.println("  Non ajouter");
		return new ResponseEntity<Produit>(HttpStatus.BAD_REQUEST);
	}
	
	}

@PostMapping(value="/saveProduitInserver")
public ResponseEntity<Produit> saveProduitInserver(@RequestParam("file") MultipartFile file, @RequestParam("produit") String produit ) throws JsonParseException, JsonMappingException, IOException {
	Produit p=new ObjectMapper().readValue(produit,Produit.class);
//	p.setImage(file.getBytes());
	boolean isExist= new File(context.getRealPath("/produitProfile/")).exists();
	if(!isExist) {
		new File(context.getRealPath("/produitProfile/")).mkdir();
		System.out.println("Not exist");
	}
	String filename=file.getOriginalFilename();
	String modifierFileName=FilenameUtils.getBaseName(filename)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(filename);
	File Serverfile=new File(context.getRealPath("/produitProfile/"+File.separator+modifierFileName));
	try {
		FileUtils.writeByteArrayToFile(Serverfile, file.getBytes());
		System.out.println("Copy byte to file");

	}catch (Exception e) {
		e.printStackTrace();
}
	if(produitService.addProduit(p)) {
		System.out.println("  produit ajouter");
		return new ResponseEntity<Produit>(p,HttpStatus.OK);
	}
	else {
		System.out.println("  Non ajouter");
		return new ResponseEntity<Produit>(HttpStatus.BAD_REQUEST);
	}
	
}


@PutMapping
public void updateProduit(@RequestBody Produit p) {
	produitService.updateProduit(p);
	System.out.println(p.getRef() +"  produit modifier");

}
@DeleteMapping("/{id}")
public void deleteProduit(@PathVariable Long id) {
	produitService.deleteProduit(id);
	System.out.println(id +"  produit supprimer");
}


@GetMapping(value="/getProduitImages")
public Page<Produit>  getProduitsImages(
	@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="5")int size
		) {
	return produitService.getProduitsImages(new PageRequest(page,size));
	
}

}
