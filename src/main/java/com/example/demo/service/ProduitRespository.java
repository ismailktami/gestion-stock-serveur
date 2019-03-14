package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import com.example.demo.entity.Produit;

@Repository
public interface ProduitRespository extends JpaRepository<Produit, Long> {
	@Query("select p from Produit p")
	public Page<Produit> chercherProduits(Pageable pePageable);

	@Query("select p from Produit p where p.ref like:x")
	public Page<Produit> chercherByMc(@Param("x") String mc,Pageable pePageable);
	
	@Query("select p.image from Produit p where p.id=:x")
	public  byte[] getImagByRef(@Param("x") Long mc);
	
	@Query("select  p.image from Produit p")
	public  Page<Produit> getProduitsImages(Pageable pePageable);
	
}
