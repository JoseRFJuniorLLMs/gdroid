package com.gdroid.modelo;

import java.io.Serializable;

import android.provider.BaseColumns;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	public static String[] colunas = new String[] { Usuarios._ID, Usuarios.NOMVEN, Usuarios.APELIDO};
	
	private long CodVen;
	private String NomVen;
	private String Apelido;
	
	
	public long getId() {
		return CodVen;
	}

	public void setId(long CodVen) {
		this.CodVen = CodVen;
	}

	public String getNomVen() {
		return NomVen;
	}

	public void setNomVen(String NomVen) {
		this.NomVen = NomVen;
	}

	public String getApelido() {
		return Apelido;
	}

	public void setApelido(String Apelido) {
		this.Apelido = Apelido;
	}

	

	public static final class Usuarios implements BaseColumns{
		public static final String DEFAULT_SORT_ORDER = "CodVen ASC";
		public static final String NOMVEN = "NomVen";
		public static final String APELIDO = "Apelido";

	}	
}