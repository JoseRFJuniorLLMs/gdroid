package com.gdroid.modelo;

import java.io.Serializable;

import android.provider.BaseColumns;

public class Pedidos implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String[] colunas = new String[] {
		Pedido.CODPED,
		Pedido.DATAPED, 
		Pedido.CODCLI, 
		Pedido.CODVEN,
		Pedido.TOTLAN, 
		Pedido.TOTPEC, 
		Pedido.TOTICMS, 
		Pedido.TOTIPI,
		Pedido.TOTPRO, 
		Pedido.TOTDES,
		Pedido.TOTBRUTO, 
		Pedido.TOTPED};

	private long CodPed;
	private String DataPed;
	private long CodCli;
	private long CodVen;
	private long TotLan;
	private double TotPec;
	private double TotIcms;
	private double TotIpi;
	private double TotPro;
	private double TotDes;
	private double TotBruto;
	private double TotPed;

	
	

	public long getId() {
		return CodPed;
	}

	public void setId(long CodPed) {
		this.CodPed = CodPed;
	}

	public String getDataPed() {
		return DataPed;
	}

	public void setDataPed(String DataPed) {
		this.DataPed = DataPed;
	}

	public long getCodCli() {
		return CodCli;
	}

	public  void setCodCli(long CodCli) {
		 this.CodCli = CodCli;
	}

	
	public long getCodVen() {
		return CodVen;
	}

	public  void setCodVen(long CodVen) {
		 this.CodVen = CodVen;
	}

	
	public long getTotLan() {
		return TotLan;
	}

	public  void setTotLan(long TotLan) {
		 this.TotLan = TotLan;
	}
	
	
	public double getTotPec() {
		return TotPec;
	}

	public  void setTotPec(double TotPec) {
		 this.TotPec = TotPec;
	}
	
	
	public double getTotIcms() {
		return TotIcms;
	}

	public  void setTotIcms(double TotIcms) {
		 this.TotIcms = TotIcms;
	}

	
	public double getTotIpi() {
		return TotIpi;
	}

	public  void setTotIpi(double TotIpi) {
		 this.TotIpi = TotIpi;
	}
	
	
	
	public double getTotPro() {
		return TotPro;
	}

	public  void setTotPro(double TotPro) {
		 this.TotPro = TotPro;
	}
	
	
	
	
	public double getTotDes() {
		return TotDes;
	}

	public  void setTotDes(double TotDes) {
		 this.TotDes = TotDes;
	}
	
	
	
	public double getTotBruto() {
		return TotBruto;
	}

	public  void setTotBruto(double TotBruto) {
		 this.TotBruto = TotBruto;
	}
	
	
	
	public double getTotPed() {
		return TotPed;
	}

	public  void setTotPed(double TotPed) {
		 this.TotPed = TotPed;
	}

	public static final class Pedido implements BaseColumns {
		public static final String CODPED = "CodPed";
		public static final String DATAPED = "DataPed";
		public static final String CODCLI = "CodCli";
		public static final String CODVEN = "CodVen";
		public static final String TOTLAN = "TotLan";
		public static final String TOTPEC = "TotPec";
		public static final String TOTICMS = "TotIcms";
		public static final String TOTIPI = "TotIpi";
		public static final String TOTPRO = "TotPro";
		public static final String TOTDES = "TotDes";
		public static final String TOTBRUTO = "TotBruto";
		public static final String TOTPED = "TotPed";
	}
}
