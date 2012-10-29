package com.gdroid.modelo;

import java.io.Serializable;

import android.provider.BaseColumns;

public class Produtos implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String[] colunas = new String[] { 
			Produto.CODPED, Produto.CODPRO, Produto.DESPRO,
			Produto.UNID, Produto.QUANT, Produto.CUSTO, Produto.PRUNIT,
			Produto.PRTOT, Produto.PRDES,
			};

	
	private long CodPed;
	private long CodPro;
	private String DesPro;
	private String Unid;
	private double Quant;
	private Number Custo;
	private double PrUnit;
	private double PrTot;
	private double PrDes;
	

	public long getCodPed() {
		return CodPed;
	}

	public void setCodPed(long CodPed) {
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
	
	
	public double getQuant() {
		return Quant;
	}

	public  void setQuant(double Quant) {
		 this.Quant = Quant;
	}
	
	
	public Number getCusto() {
		return Custo;
	}

	public  void setCusto(Number Custo) {
		 this.Custo = Custo;
	}

	
	public double getPrUnit() {
		return PrUnit;
	}

	public  void setPrUnit(double PrUnit) {
		 this.PrUnit = PrUnit;
	}
	
	
	
	public double getPrTot() {
		return PrTot;
	}

	public  void setPrTot(double PrTot) {
		 this.PrTot = PrTot;
	}
	
	
	
	
	public double getPrDes() {
		return PrDes;
	}

	public  void setPrDes(double PrDes) {
		 this.PrDes = PrDes;
	}
	
	
	

	public static final class Produto implements BaseColumns {
		public static final String CODPED = "CodPed";
		public static final String CODPRO = "CodPro";
		public static final String DESPRO = "DesPro";
		public static final String UNID = "Unid";
		public static final String QUANT = "Quant";
		public static final String TOTPEC = "TotPec";
		public static final String CUSTO = "Custo";
		public static final String PRUNIT = "PrUnit";
		public static final String PRTOT = "PrTot";
		public static final String PRDES = "PrDes";
	}
	
	
	
}
