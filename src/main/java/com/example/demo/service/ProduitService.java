package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Produit;

@Service
@Primary
public class ProduitService implements IProduitService{
	@Autowired
	private ProduitRespository produitRepository;
	@Override
	public List<Produit> getProduits() {
		return produitRepository.findAll();
	}
	
	@Override
	public Boolean addProduit(Produit p) {
		Produit produit= produitRepository.save(p);
		if(produit==null)
			return false;
		else 
			return true;
		}

	@Override
	public void updateProduit(Produit p) {
		 produitRepository.save(p);
	}
	@Override
	public void deleteProduit(Long  id) {
		produitRepository.delete(produitRepository.getOne(id));
	}

	@Override
	public Page<Produit> chercherProduits(PageRequest page) {
		return produitRepository.chercherProduits( page);
	}


	@Override
	public Page<Produit> chercherByMc(String mc,PageRequest page) {
		return produitRepository.chercherByMc(mc, page);
	}

	@Override
	public byte[] getImage(Long ref) {
		return produitRepository.getImagByRef(ref);
	}
	
	
	public  Page<Produit> getProduitsImages(Pageable pePageable){
		
		return produitRepository.getProduitsImages(pePageable);
	}
	

}
