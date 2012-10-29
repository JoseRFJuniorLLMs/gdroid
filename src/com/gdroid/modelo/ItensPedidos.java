package com.gdroid.modelo;

import java.io.Serializable;

import android.provider.BaseColumns;

public class ItensPedidos implements Serializable {

	private static final long serialVersionUID = 1L;

	

	public static String[] colunas = new String[] { 
		ItensPedido._ID,
		ItensPedido.CODPED,
		ItensPedido.CODPRO,
		ItensPedido.DESPRO,
		ItensPedido.UNID,
		ItensPedido.CUSTTOT
			};
	private long id;
	private long CodPed;
	private long CodPro;
	private String DesPro;
	private String Unid;
	private double CustTot;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getCodPed() {
		return CodPed;
	}

	public  void setCodPed(long CodPed) {
		 this.CodPed = CodPed;
	}
	
	public long getCodPro() {
		return CodPro;
	}

	public  void setCodPro(long CodPro) {
		 this.CodPro = CodPro;
	}

	
	public String getDesPro() {
		return DesPro;
	}

	public  void setDesPro(String DesPro) {
		 this.DesPro = DesPro;
	}

	
	public String getUnid() {
		return Unid;
	}

	public  void setUnid(String Unid) {
		 this.Unid = Unid;
	}
	
	
	public double getCustTot() {
		return CustTot;
	}

	public  void setCustTot(double CustTot) {
		 this.CustTot = CustTot;
	}
	

	

	public static final class ItensPedido implements BaseColumns {
		public static final String _ID = "_ID";
		public static final String CODPED = "CodPed";
		public static final String CODPRO = "CodPro";
		public static final String DESPRO = "DesPro";
		public static final String UNID = "Unid";
		public static final String CUSTTOT = "CustTot";
		
	}
	
	
	
}
