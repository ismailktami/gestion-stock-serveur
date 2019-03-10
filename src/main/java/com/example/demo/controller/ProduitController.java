package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
