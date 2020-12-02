package com.openwebinars.hibernate.PrimerExemple;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuari {
	@Id
	private int num;

	private String nom;

	private String missatge;

	public Usuari() {
		super();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMissatge() {
		return missatge;
	}

	public void setMissatge(String missatge) {
		this.missatge = missatge;
	}

}
