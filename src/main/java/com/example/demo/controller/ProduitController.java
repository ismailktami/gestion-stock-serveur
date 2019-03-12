package com.example.demo.controller;

import java.util.Base64;
import java.util.List;

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

import com.example.demo.entity.Produit;
import com.example.demo.service.IProduitService;

@RestController
@RequestMapping("/api/produit")
@CrossOrigin
public class ProduitController {
@Autowired
private IProduitService produitService;

@GetMapping
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



}
