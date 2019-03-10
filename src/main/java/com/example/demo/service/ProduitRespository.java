package com.example.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Produit;

@Repository
public interface ProduitRespository extends JpaRepository<Produit, Long> {

}
