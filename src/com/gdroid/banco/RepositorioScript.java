package com.gdroid.banco;

import com.gdroid.controle.Repositorio;

import android.content.Context;

public class RepositorioScript extends Repositorio {

	private static final String[] SCRIPT_DATABASE_DELETE = new String[] {
			 
		"DROP TABLE IF EXISTS Clientes;", 
		"DROP TABLE IF EXISTS Vendedores;",
		"DROP TABLE IF EXISTS Pedidos;",
		"DROP TABLE IF EXISTS Produtos;",
		"DROP TABLE IF EXISTS ItemsPed;"};

	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
		
		"CREATE TABLE [Clientes] ("+
				  "[CodCli] BIGINT PRIMARY KEY,"+
				  "[CNPJCli] VARCHAR(19),"+
				  "[InsCli] VARCHAR(20),"+
				  "[Sufra] VARCHAR(20),"+
				  "[RazCli] VARCHAR(50),"+ 
				  "[FanCli] VARCHAR(50),"+ 
				  "[EndCli] VARCHAR(70),"+ 
				  "[NumCli] VARCHAR(15),"+ 
				  "[BaiCli] VARCHAR(40),"+ 
				  "[CEPCli] VARCHAR(8),"+ 
				  "[DDDFonCli] VARCHAR(2),"+ 
				  "[FonCli] VARCHAR(10),"+ 
				  "[CadCli] DATE,"+ 
				  "[CodVen] BIGINT,"+ 
				  "[EmailCli] CHAR(150),"+ 
				  "[ObsCli] TEXT,"+ 
				  "[Alerta] TEXT,"+ 
				  "[NomCid] VARCHAR,"+ 
				  "[NomEst] VARCHAR(2),"+ 
				  "[EndCob] VARCHAR(70),"+
				  "[NumCob] VARCHAR(15),"+ 
				  "[BaiCob] VARCHAR(40),"+ 
				  "[CepCob] VARCHAR(8),"+ 
				  "[CPFCli] VARCHAR(19),"+ 
				  "[RGCli] VARCHAR(20),"+ 
				  "[PropCli] VARCHAR(50),"+ 
				  "[TipoVenda] CHAR(1));"+
				  		"CREATE TABLE [Vendedores] ("+
				  "[CodVen] BIGINT PRIMARY KEY,"+ 
				  "[NomVen] VARCHAR(50),"+ 
				  "[Apelido] VARCHAR(30));"+
				  		"CREATE TABLE [Pedidos] ("+
				  "[CodPed] BIGINT PRIMARY KEY,"+ 
				  "[DataPed] DATE,"+ 
				  "[CodCli] BIGINT CONSTRAINT [FK_Clientes] REFERENCES [Clientes]([CodCli]),"+ 
				  "[CodVen] BIGINT CONSTRAINT [FK_Vendedor] REFERENCES [Vendedores]([CodVen]),"+ 
				  "[TotLan] INT,"+ 
				  "[TotPec] DOUBLE,"+ 
				  "[TotIcms] DOUBLE,"+ 
				  "[TotIpi] DOUBLE,"+ 
				  "[TotPro] DOUBLE,"+ 
				  "[TotDes] DOUBLE,"+ 
				  "[TotBruto] DOUBLE,"+ 
				  "[TotPed] DOUBLE);"+
				  			"CREATE TABLE [Produtos] ("+
				  "[CodPro] BIGINT PRIMARY KEY,"+ 
				  "[DesPro] VARCHAR,"+ 
				  "[Unid] CHAR(3),"+ 
				  "[CustTot] DOUBLE);"+
				  			"CREATE TABLE [ItemsPed] ("+
				  "[ID] BIGINT PRIMARY KEY,"+
				  "[CodPed] BIGINT NOT NULL ON CONFLICT FAIL CONSTRAINT [FK_ItemsPed_Pedidos] REFERENCES [Pedidos]([CodPed]),"+ 
				  "[CodPro] BIGINT NOT NULL CONSTRAINT [FK_Clientes] REFERENCES [Produtos]([CodPro]),"+ 
				  "[DesPro] VARCHAR2(50),"+ 
				  "[Unid] VARCHAR(3),"+ 
				  "[Quant] DOUBLE,"+ 
				  "[Custo] NUMERIC,"+ 
				  "[PrUnit] DOUBLE,"+ 
				  "[PrTot] DOUBLE,"+ 
				  "[PerDes] DOUBLE,"+ 
				  "CONSTRAINT [sqlite_autoindex_ItemsPed_1] PRIMARY KEY ([CodPed]));"
				  
		};

	private static final String NOME_BANCO = "gdroid";

	public static final int VERSAO_BANCO = 1;

	private SQLiteHelper dbHelper;

	public RepositorioScript(Context ctx) {
		dbHelper = new SQLiteHelper(ctx, RepositorioScript.NOME_BANCO,
				RepositorioScript.VERSAO_BANCO,
				RepositorioScript.SCRIPT_DATABASE_CREATE,
				RepositorioScript.SCRIPT_DATABASE_DELETE);
		bd = dbHelper.getWritableDatabase();
	}

	@Override
	public void fechar() {
		super.fechar();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
}