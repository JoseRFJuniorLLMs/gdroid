package com.gdroid.controle;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.gdroid.modelo.Clientes;
import com.gdroid.visao.R;

public class CadastroClientes extends Activity {
	static final int RESULT_SALVAR = 1;

	protected static Repositorio repositorio;
	private EditText EdtCNPJCli;
	private EditText EdtInsCli;
	private RadioGroup RGTipoVenda;
	private RadioButton RBVendaNormal;
	private RadioButton RBZonaFranca;
	private RadioButton RBLivreComercio;
	private EditText EdtRazCli;
	private EditText EdtFanCli;
	private EditText EdtEndCli;
	private EditText EdtNumCli;
	private EditText EdtBaiCli;
	private EditText EdtCepCli;
	private EditText EdtDDDFonCli;
	private EditText EdtFonCli;
	private EditText EdtEmailCli;
	private EditText EdtObsCli;
	private EditText EdtAlerta;
	private EditText EdtCidCli;
	private EditText EdtEstCli;
	private EditText EdtEndCobCli;
	private EditText EdtNumCobCli;
	private EditText EdtBaiCobCli;
	private EditText EdtCepCobCli;
	private EditText EdtCpfCli;
	private EditText EdtRGCli;
	private EditText EdtNomPropCli;
	private Button BtnCadastra;
	
	
	
	

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		Log.i("ciclo", getClassName() + ".onCreate() chamado: " + icicle);

		setContentView(R.layout.cadclientes);

		ScrollView view = (ScrollView) findViewById(R.id.scrollView);
		view.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
		view.setFocusable(true);
		view.setFocusableInTouchMode(true);
		view.setOnTouchListener(new View.OnTouchListener() {
		
			public boolean onTouch(View v, MotionEvent event) {
				v.requestFocusFromTouch();
				return false;
			}
		});

		repositorio = new Repositorio(getApplicationContext());

		EdtCNPJCli = (EditText) findViewById(R.id.EdtCNPJCli);
		EdtInsCli = (EditText) findViewById(R.id.EdtInsCli);
		RGTipoVenda = (RadioGroup) findViewById(R.id.RGTipoVenda);
		EdtRazCli = (EditText) findViewById(R.id.EdtRazCli);
		EdtFanCli = (EditText) findViewById(R.id.EdtFanCli);
		EdtEndCli = (EditText) findViewById(R.id.EdtEndCli);
		EdtNumCli = (EditText) findViewById(R.id.EdtNumCli);
		EdtBaiCli = (EditText) findViewById(R.id.EdtBaiCli);
		EdtCepCli = (EditText) findViewById(R.id.EdtCepCli);
		EdtDDDFonCli = (EditText) findViewById(R.id.EdtDDDFonCli);
		EdtFonCli = (EditText) findViewById(R.id.EdtFonCli);
		EdtEmailCli = (EditText) findViewById(R.id.EdtEmailCli);
		EdtObsCli = (EditText) findViewById(R.id.EdtObsCli);
		EdtAlerta = (EditText) findViewById(R.id.EdtAlerta);
		EdtCidCli = (EditText) findViewById(R.id.EdtCidCli);
		EdtEstCli = (EditText) findViewById(R.id.EdtEstCli);
		EdtEndCobCli = (EditText) findViewById(R.id.EdtEndCobCli);
		EdtNumCobCli = (EditText) findViewById(R.id.EdtNumCobCli);
		EdtBaiCobCli = (EditText) findViewById(R.id.EdtBaiCobCli);
		EdtCepCobCli = (EditText) findViewById(R.id.EdtCepCobCli);
		EdtCpfCli = (EditText) findViewById(R.id.EdtCpfCli);
		EdtRGCli = (EditText) findViewById(R.id.EdtRGCli);
		EdtNomPropCli = (EditText) findViewById(R.id.EdtNomPropCli);
		BtnCadastra = (Button) findViewById(R.id.BtnCadastra);
		
		
		BtnCadastra.setOnClickListener(new OnClickListener() {
			
			public void onClick(View view) {

				String erro = "";
				boolean campo_obrigatorio_nao_preenchido = false;
				boolean campo_invalido = false;

				
				
				
				
				EdtCNPJCli.setCompoundDrawablesWithIntrinsicBounds(0,	0, 0, 0);
				EdtInsCli.setCompoundDrawablesWithIntrinsicBounds(0,	0, 0, 0);
				RBLivreComercio.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtRazCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtFanCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtEndCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtNumCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtBaiCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtCepCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtDDDFonCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtFonCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtEmailCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtObsCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtAlerta.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtCidCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtEstCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtEndCobCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtNumCobCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtBaiCobCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtCepCobCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtCpfCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtRGCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtNomPropCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		
				
				String CNPJCli = EdtCNPJCli.getText().toString().trim();
				String InsCli = EdtInsCli.getText().toString().trim();
				String TipoVenda = RGTipoVenda.getContext().toString().trim();
				String RazCli = EdtRazCli.getText().toString().trim();
				String FanCli = EdtFanCli.getText().toString().trim();
				String EndCli = EdtEndCli.getText().toString().trim();
				String NumCli = EdtNumCli.getText().toString().trim();
				String BaiCli = EdtBaiCli.getText().toString().trim();
				String CepCli = EdtCepCli.getText().toString().trim();
				String DDDFonCli = EdtDDDFonCli.getText().toString().trim();
				String FonCli = EdtFonCli.getText().toString().trim();
				String EmailCli = EdtEmailCli.getText().toString().trim();
				String ObsCli = EdtObsCli.getText().toString().trim();
				String Alerta = EdtAlerta.getText().toString().trim();
				String CidCli = EdtCidCli.getText().toString().trim();
				String EstCli = EdtEstCli.getText().toString().trim();
				String EndCobCli = EdtEndCobCli.getText().toString().trim();
				String NumCobCli = EdtNumCobCli.getText().toString().trim();
				String BaiCobCli = EdtBaiCobCli.getText().toString().trim();
				String CepCobCli = EdtCepCobCli.getText().toString().trim();
				String CpfCli = EdtCpfCli.getText().toString().trim();
				String RGCli = EdtRGCli.getText().toString().trim();
				String NomPropCli = EdtNomPropCli.getText().toString().trim();
							

				String[] campos = new String[] { CNPJCli, InsCli, TipoVenda, RazCli,
						FanCli,EndCli, NumCli ,BaiCli,CepCli,DDDFonCli,FonCli, EmailCli,ObsCli,Alerta, CidCli, 
						EstCli,EndCobCli,NumCobCli,BaiCobCli,CepCobCli,CpfCli,RGCli,NomPropCli};

				if (campo_vazio(campos)) { // tem campo vazio
					erro = "Campo(s) obrigat�rio(s) em branco.";
					campo_obrigatorio_nao_preenchido = true;
					if (CNPJCli.equals("")) {
						EdtCNPJCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_input_error, 0);
					}
					if (RazCli.equals("")) {
						EdtRazCli.setCompoundDrawablesWithIntrinsicBounds(0, 0,	R.drawable.indicator_input_error, 0);
					}
					if (EmailCli.equals("")) {
						EdtEmailCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_input_error, 0);
					}
					if (FonCli.equals("")) {
						EdtFonCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_input_error, 0);
					}
					
				}
				// a partir daqui verifica se os campos são válidos
				
				
				
				if (!(CNPJCli.equals("")) && !(CNPJCli.matches("^([a-zA-Z\\ ]+)$"))) {
					EdtCNPJCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_input_error, 0);
					campo_invalido = true;
				}
				if (!(RazCli.equals("")) && !(RazCli.matches("^\\d*[0-9]$"))) {
					EdtRazCli.setCompoundDrawablesWithIntrinsicBounds(0, 0,	R.drawable.indicator_input_error, 0);
					campo_invalido = true;
				}
				if (!(EmailCli.equals(""))
						&& !(EmailCli.matches("^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+[0-9,a-z,A-Z,.,-]*(.){1}[a-zA-Z]{2,4})+$"))) {
					EdtEmailCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_input_error, 0);
					campo_invalido = true;
				}
				if (!(FonCli.equals(""))
						&& !(FonCli.matches("^\\d*[0-9]$"))) {
					EdtFonCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_input_error, 0);
					campo_invalido = true;
				}

				if (campo_invalido) {
					erro += "Campo(s) inv�lido(s).";
				}

				if (repositorio.buscarCliente(CNPJCli) != null) {
					EdtCNPJCli.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_input_error, 0);
					EdtRazCli.setText("");
					EdtEmailCli.setText("");
					EdtFonCli.setText("");

					if (campo_invalido || campo_obrigatorio_nao_preenchido) {
						erro += "\nLogin já cadastrado.";
					} else {
						erro += "Login já cadastrado.";
					}
				}

				if (erro.equals("")) { // se não tem campo vazio e todos já
										// passaram na validação
					salvarClientes();
				} else {
					Toast.makeText(CadastroClientes.this, erro, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	public void salvarClientes() {

		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Clientes cliente = new Clientes();

		TelephonyManager telephony;
		// == Adquirindo uma referência ao Sistema de Gerenciamento do Telefone
		telephony = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		
		cliente.setCNPJCli(EdtCNPJCli.getText().toString());
		cliente.setInsCli(EdtInsCli.getText().toString());
		cliente.setTipoVenda(RGTipoVenda.getContext().toString());
		cliente.setRazCli(EdtRazCli.getText().toString());
		cliente.setEndCli(EdtEndCli.getText().toString());
		cliente.setNumCli(EdtNumCli.getText().toString());
		cliente.setRazCli(EdtRazCli.getText().toString());
		cliente.setBaiCli(EdtBaiCli.getText().toString());
		cliente.setCepCli(EdtCepCli.getText().toString());
		cliente.setDDDFonCli(EdtDDDFonCli.getText().toString());
		cliente.setFonCli(EdtFonCli.getText().toString());
		cliente.setEmailCli(EdtEmailCli.getText().toString());
		cliente.setObsCli(EdtObsCli.getText().toString());
		cliente.setAlerta(EdtAlerta.getText().toString());
		cliente.setNomCid(EdtCidCli.getText().toString());
		cliente.setNomEst(EdtEstCli.getText().toString());
		cliente.setEndCob(EdtEndCobCli.getText().toString());
		cliente.setNumCob(EdtNumCobCli.getText().toString());
		cliente.setBaiCob(EdtBaiCobCli.getText().toString());
		cliente.setAlerta(EdtAlerta.getText().toString());
		cliente.setCepCob(EdtCepCobCli.getText().toString());
		cliente.setCPFCli(EdtCpfCli.getText().toString());
		cliente.setRGCli(EdtRGCli.getText().toString());
		cliente.setPropCli(EdtNomPropCli.getText().toString());
		
		
		if(salvar(cliente)){		
			setResult(RESULT_OK, new Intent());
			finish();
		}
	}
	
	protected boolean salvar(Clientes clientes) {
		boolean salvar = true;
		long id = repositorio.inserir(clientes, 1); 
		if(id != 0){
			
			///////////////?????////////////
			//cliente.CodCli = id;
			//cliente.estaLogado = true;
			//////////////?/////////////////
			Toast.makeText(CadastroClientes.this, "Cadastro realizado com sucesso.", Toast.LENGTH_LONG).show();
		}else{
			salvar = false;
			
			AlertDialog.Builder alerta = new AlertDialog.Builder(CadastroClientes.this);
			alerta.setIcon(R.drawable.alerta);
			alerta.setTitle("Falha ao inserir este Cliente");
			alerta.setMessage("Este erro pode ter sido causado pela perda do sinal da rede sem fio");

			alerta.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
			});
			alerta.show();
		}
		return salvar;
	}

	public boolean campo_vazio(String[] campos) {
		int tam = campos.length;
		for (int i = 0; i < tam; i++) {
			if (campos[i].equals("")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i("ciclo", getClassName() + ".onStart() chamado.");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("ciclo", getClassName() + ".onResume() chamado.");
	}

	@Override
	public void onRestart() {
		super.onRestart();
		Log.i("ciclo", getClassName() + ".onRestart() chamado.");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("ciclo", getClassName() + ".onPause() chamado.");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i("ciclo", getClassName() + ".onStop() chamado.");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("ciclo", getClassName() + ".onDestroy() chamado.");

		repositorio.fechar();
	}
	
	private String getClassName() {
		String s = getClass().getName();
		return s.substring(s.lastIndexOf("."));
	}

	public RadioButton getRBVendaNormal() {
		return RBVendaNormal;
	}

	public void setRBVendaNormal(RadioButton rBVendaNormal) {
		RBVendaNormal = rBVendaNormal;
	}

	public RadioButton getRBZonaFranca() {
		return RBZonaFranca;
	}

	public void setRBZonaFranca(RadioButton rBZonaFranca) {
		RBZonaFranca = rBZonaFranca;
	}
}
