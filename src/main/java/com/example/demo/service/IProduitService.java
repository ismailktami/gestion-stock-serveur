package com.example.demo.service;
import java.util.List;

import com.example.demo.entity.Produit;
public interface IProduitService {
List<Produit> getProduits();
void addProduit(Produit p);
void updateProduit(Produit p);
public void deleteProduit(Long  id);
}
