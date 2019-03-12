package com.example.demo.service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Produit;
public interface IProduitService {
List<Produit> getProduits();
void addProduit(Produit p);
void updateProduit(Produit p);
public void deleteProduit(Long  id);
public Page<Produit> chercherProduits(PageRequest page);
public Page<Produit> chercherByMc(String mc,PageRequest page);
public byte[] getImage(Long ref);

}
