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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.gdroid.modelo.Pedidos;
import com.gdroid.visao.R;

public class CadastroPedidos extends Activity {
	static final int RESULT_SALVAR = 1;

	protected static Repositorio repositorio;
	
	
	
	
	private EditText EdtTotIcms;
	private EditText EdtTotIpi;
	private TextView TxtTotDes;
	private TextView TxtTotBruto;
	private TextView TxtTotPed;
	private Button btnPed;
	

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		Log.i("ciclo", getClassName() + ".onCreate() chamado: " + icicle);

		setContentView(R.layout.cadpedidos);

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


		
		EdtTotIcms = (EditText) findViewById(R.id.EdtTotIcms);
		EdtTotIpi = (EditText) findViewById(R.id.EdtTotIpi);
		TxtTotDes = (TextView) findViewById(R.id.TxtTotDes);
       // TxtTotBruto = (TextView) findViewById(R.id.TxtTotBruto);
		TxtTotPed = (TextView) findViewById(R.id.TxtTotPed);
		btnPed = (Button) findViewById(R.id.btnPed);
		
		
		btnPed.setOnClickListener(new OnClickListener() {
			
			public void onClick(View view) {

				String erro = "";
				boolean campo_obrigatorio_nao_preenchido = false;
				boolean campo_invalido = false;

				
				
				EdtTotIcms.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtTotIpi.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				TxtTotDes.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
			//	TxtTotBruto.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				TxtTotPed.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
			
				
				
				
				String TotIcms = EdtTotIcms.getText().toString().trim();
				String TotIpi = EdtTotIpi.getText().toString().trim();
				String TotDes = TxtTotDes.getText().toString().trim();
				String TotBruto = TxtTotBruto.getText().toString().trim();
				String TotPed = TxtTotPed.getText().toString().trim();
				
							

				String[] campos = new String[] { TotIcms, TotIpi, TotDes, TotBruto, TotPed};

//				if (campo_vazio(campos)) { // tem campo vazio
//					erro = "Campo(s) obrigat�rio(s) em branco.";
//					campo_obrigatorio_nao_preenchido = true;
//					if (nome.equals("")) {
//						campo_nome.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_input_error, 0);
//					}
//					if (cpf.equals("")) {
//						campo_cpf.setCompoundDrawablesWithIntrinsicBounds(0, 0,	R.drawable.indicator_input_error, 0);
//					}
//					if (email.equals("")) {
//						campo_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_input_error, 0);
//					}
//					if (login.equals("")) {
//						campo_login.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_input_error, 0);
//					}
//					if (senha.equals("")) {
//						campo_senha.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_input_error, 0);
//					}
//					if (confirma_senha.equals("")) {
//						campo_confirma_senha.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_input_error, 0);
//					}
//				}
				

				if (erro.equals("")) { // se não tem campo vazio e todos já
										// passaram na validação
					salvarPedidos();
				} else {
					Toast.makeText(CadastroPedidos.this, erro, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	public void salvarPedidos() {

		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Pedidos pedidos = new Pedidos();

		TelephonyManager telephony;
		// == Adquirindo uma referência ao Sistema de Gerenciamento do Telefone
		telephony = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

		
	
		
		pedidos.setTotIcms(Double.parseDouble(EdtTotIcms.getText().toString()));
		pedidos.setTotIpi(Double.parseDouble(EdtTotIpi.getText().toString()));
		pedidos.setTotDes(Double.parseDouble(TxtTotDes.getContext().toString()));
		pedidos.setTotBruto(Double.parseDouble(TxtTotBruto.getText().toString()));
		pedidos.setTotPed(Double.parseDouble(TxtTotPed.getText().toString()));
		
		
		if(salvar(pedidos)){		
			setResult(RESULT_OK, new Intent());
			finish();
		}
	}
	
	protected boolean salvar(Pedidos pedidos) {
		boolean salvar = true;
		long id = repositorio.inserir(pedidos, 1); 
		if(id != 0){
			
			///////////////?????////////////
			//pedidos.CodCli = id;
			//pedidos.estaLogado = true;
			//////////////?/////////////////
			Toast.makeText(CadastroPedidos.this, "Cadastro realizado com sucesso.", Toast.LENGTH_LONG).show();
		}else{
			salvar = false;
			
			AlertDialog.Builder alerta = new AlertDialog.Builder(CadastroPedidos.this);
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
}
