package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Produit;

@Service
public class ProduitMockServiceImpl implements IProduitService {

	private List<Produit> produits;
	int i=0;
	@Override
	public List<Produit> getProduits() {
		if(i==0) {
		produits=new ArrayList<Produit>();
		produits.add(new Produit("Ref1",120,56));
		produits.add(new Produit("Ref2",142,100));
		produits.add(new Produit("Ref3",20,785));
		i++;
		}
		return produits;
	}

	@Override
	public void addProduit(Produit p) {
		produits.add(p);
		
	}

	@Override
	public void updateProduit(Produit p) {
		produits.remove(p);
		produits.add(p);
	}

	/*@Override
	public void deleteProduit(int ref) {
		Produit p=new Produit();
		p.setRef(ref);
		produits.remove(p);

	}

*/
	public void deleteProduit(Long  id) {}

	@Override
	public Page<Produit> chercherProduits(PageRequest page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Produit> chercherByMc(String mc, PageRequest page) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
