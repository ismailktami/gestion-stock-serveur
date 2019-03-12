package com.example.demo.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
@Entity
public class Produit {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String ref;
	
	private int quantite;
	
	private float prixUnitaire;
	
	 @Lob
	    private byte[] image;

	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Produit(String ref, int quantite, float prixUnitaire) {
		super();
		this.ref = ref;
		this.quantite = quantite;
		this.prixUnitaire = prixUnitaire;
	}
	public Produit(String ref, int quantite, float prixUnitaire,byte[] image) {
		super();
		this.ref = ref;
		this.quantite = quantite;
		this.prixUnitaire = prixUnitaire;
		this.image=image;
	}

	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public float getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produit other = (Produit) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	 public  void setImageProduit(String photoFilePath) throws IOException {
	        byte[] photoBytes = readBytesFromFile(photoFilePath);
	        this.setImage(photoBytes);
	    }
	 
	 private  byte[] readBytesFromFile(String filePath) throws IOException {
	        File inputFile = new File(filePath);
	        FileInputStream inputStream = new FileInputStream(inputFile);
	        byte[] fileBytes = new byte[(int) inputFile.length()];
	        inputStream.read(fileBytes);
	        inputStream.close();
	        return fileBytes;
	    }
	  public  void readPhotoOfPerson(String photoFilePath) throws IOException {
	        byte[] photoBytes = this.getImage();
	        saveBytesToFile(photoFilePath, photoBytes);
	    }
	  
	  public  void saveBytesToFile(String filePath, byte[] fileBytes) throws IOException {
	        FileOutputStream outputStream = new FileOutputStream(filePath);
	        outputStream.write(fileBytes);
	        outputStream.close();
	    }
	     
	 
	 

}
