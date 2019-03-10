package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
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
	public void addProduit(Produit p) {
		 produitRepository.save(p);
		}

	@Override
	public void updateProduit(Produit p) {
		 produitRepository.save(p);
	}
	@Override
	public void deleteProduit(Long  id) {
		produitRepository.delete(produitRepository.getOne(id));
	}

	

}
