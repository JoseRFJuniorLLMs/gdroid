package com.gdroid.modelo;

import java.io.Serializable;

import android.provider.BaseColumns;

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;



	public static String[] colunas = new String[] { Cliente.CODCLI,
			Cliente.CNPJCLI,
			Cliente.INSCLI,
			Cliente.SUFRA,
			Cliente.RAZCLI,
			Cliente.FANCLI,
			Cliente.ENDCLI,
			Cliente.NUMCLI,
			Cliente.BAICLI,
			Cliente.CEPCLI,
			Cliente.DDDFONCLI,
			Cliente.FONCLI,
			Cliente.CADCLI,
			Cliente.CODVEN,
			Cliente.EMAILCLI,
			Cliente.OBSCLI,
			Cliente.ALERTA,
			Cliente.NOMCID,
			Cliente.NOMEST,
			Cliente.ENDCOB,
			Cliente.NUMCOB,
			Cliente.BAICOB,
			Cliente.CEPCOB,
			Cliente.CPFCLI,
			Cliente.RGCLI,
			Cliente.PROPCLI,
			Cliente.TIPOVENDA };

	private long CodCli;
	private String CNPJCli;
	private String InsCli;
	private String Sufra;
	private String RazCli;
	private String FanCli;
	private String EndCli;
	private String NumCli;
	private String BaiCli;
	private String CepCli;
	private String DDDFonCli;
	private String FonCli;
	private String CadCli;
	private String CodVen;
	private String EmailCli;
	private String ObsCli;
	private String Alerta;
	private String NomCid;
	private String NomEst;
	private String EndCob;
	private String NumCob;
	private String BaiCob;
	private String CepCob;
	private String CPFCli;
	private String RGCli;
	private String PropCli;
	private String TipoVenda;
	
	

	public long getId() {
		return CodCli;
	}

	public void setId(long CodCli) {
		this.CodCli = CodCli;
	}

	public String getCNPJCli() {
		return CNPJCli;
	}

	public void setCNPJCli(String CNPJCli) {
		this.CNPJCli = CNPJCli;
	}

	public String getInsCli() {
		return InsCli;
	}

	public  String setInsCli(String InsCli) {
		return this.InsCli = InsCli;
	}

	public String getSufra() {
		return Sufra;
	}

	public void setSufra(String Sufra) {
		this.Sufra = Sufra;
	}

	public String getRazCli() {
		return RazCli;
	}

	public void setRazCli(String RazCli) {
		this.RazCli = RazCli;
	}

	public String getFanCli() {
		return FanCli;
	}

	public void setFanCli(String FanCli) {
		this.FanCli = FanCli;
	}

	public String getEndCli() {
		return EndCli;
	}

	public void setEndCli(String EndCli) {
		this.EndCli = EndCli;
	}

	public String getNumCli() {
		return NumCli;
	}

	public void setNumCli(String NumCli) {
		this.NumCli = NumCli;
	}

	public String getBaiCli() {
		return BaiCli;
	}

	public void setBaiCli(String BaiCli) {
		this.BaiCli = BaiCli;
	}

	public String getCepCli() {
		return CepCli;
	}

	public void setCepCli(String CepCli) {
		this.CepCli = CepCli;
	}

	public String getDDDFonCli() {
		return DDDFonCli;
	}

	public void setDDDFonCli(String DDDFonCli) {
		this.DDDFonCli = DDDFonCli;
	}

	public String getFonCli() {
		return FonCli;
	}

	public void setFonCli(String FonCli) {
		this.FonCli = FonCli;
	}

	public String getCadCli() {
		return CadCli;
	}

	public void setCadCli(String CadCli) {
		this.CadCli = CadCli;
	}

	public String getCodVen() {
		return CodVen;
	}

	public void setCodVen(String CodVen) {
		this.CodVen = CodVen;
	}	

	public String getEmailCli() {
		return EmailCli;
	}

	public void setEmailCli(String EmailCli) {
		this.EmailCli = EmailCli;
	}

	
	public String getAlerta() {
		return Alerta;
	}

	public void setAlerta(String Alerta) {
		this.Alerta = Alerta;
	}
	
	
	
	public String getObsCli() {
		return ObsCli;
	}

	public void setObsCli(String ObsCli) {
		this.ObsCli = ObsCli;
	}
	
	
	
	public String getNomCid() {
		return NomCid;
	}

	public void setNomCid(String NomCid) {
		this.NomCid = NomCid;
	}
	
	
	
	
	public String getNomEst() {
		return NomEst;
	}

	public void setNomEst(String NomEst) {
		this.NomEst = NomEst;
	}
	
	
	
	public String getEndCob() {
		return EndCob;
	}

	public void setEndCob(String EndCob) {
		this.EndCob = EndCob;
	}
	
	
	
	public String getNumCob() {
		return NumCob;
	}

	public void setNumCob(String NumCob) {
		this.NumCob = NumCob;
	}
	
	
	
	
	public String getBaiCob() {
		return BaiCob;
	}

	public void setBaiCob(String BaiCob) {
		this.BaiCob = BaiCob;
	}

	
	
	
	public String getCepCob() {
		return CepCob;
	}

	public void setCepCob(String CepCob) {
		this.CepCob = CepCob;
	}
	
	
	
	public String getCPFCli() {
		return CPFCli;
	}

	public void setCPFCli(String CPFCli) {
		this.CPFCli = CPFCli;
	}
	
	
	
	public String getRGCli() {
		return RGCli;
	}

	public void setRGCli(String RGCli) {
		this.RGCli = RGCli;
	}

	
	
	
	public String getPropCli() {
		return PropCli;
	}

	public void setPropCli(String PropCli) {
		this.PropCli = PropCli;
	}
	
	
	
	
	public String getTipoVenda() {
		return TipoVenda;
	}

	public void setTipoVenda(String TipoVenda) {
		this.TipoVenda = TipoVenda;
	}

	public static final class Cliente implements BaseColumns {
		public static final String CODCLI = "CodCli";
		public static final String CNPJCLI = "CNPJCli";
		public static final String INSCLI = "InsCli";
		public static final String SUFRA = "Sufra";
		public static final String RAZCLI = "RazCli";
		public static final String FANCLI = "FanCli";
		public static final String ENDCLI = "EndCli";
		public static final String NUMCLI = "NumCli";
		public static final String BAICLI = "BaiCli";
		public static final String CEPCLI = "CepCli";
		public static final String DDDFONCLI = "DDDFonCli";
		public static final String FONCLI = "FonCli";
		public static final String CADCLI = "CadCli";
		public static final String CODVEN = "CodVen";
		public static final String EMAILCLI = "EmailCli";
		public static final String OBSCLI = "ObsCli";
		public static final String ALERTA = "Alerta";
		public static final String NOMCID = "NomCid";
		public static final String NOMEST = "NomEst";
		public static final String ENDCOB = "EndCob";
		public static final String NUMCOB = "NumCob";
		public static final String BAICOB = "BaiCob";
		public static final String CEPCOB = "CepCob";
		public static final String CPFCLI = "CPFCli";
		public static final String RGCLI = "RGCli";
		public static final String PROPCLI = "PropCli";
		public static final String TIPOVENDA = "TipoVenda";
		
		
	}
}
