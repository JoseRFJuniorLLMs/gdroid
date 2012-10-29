package com.gdroid.banco;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import com.gdroid.modelo.Clientes;
import com.gdroid.modelo.Pedidos;
import com.gdroid.modelo.Usuario;

public class Login {
        public static boolean estaLogado = false;
        public static long idUsuario = 0;
                
        public static ArrayList<Usuario> usuarios_atualizados_locais = new ArrayList<Usuario>();
        public static ArrayList<Clientes> clientes_atualizados_locais = new ArrayList<Clientes>();
        public static ArrayList<Pedidos> pedidos_atualizados_locais = new ArrayList<Pedidos>();
        public static ArrayList<Pedidos> itenspedidos_atualizados_locais = new ArrayList<Pedidos>();
        
        public static boolean flag_enviar_pedidos_atualizados_servidor = false;
        public static boolean flag_enviar_usuarios_atualizados_servidor = false;
        public static boolean flag_enviar_clientes_atualizados_servidor = false;
        public static boolean flag_enviar_itenspedidos_atualizados_servidor = false;
        
        public static long idCentroAtual = 0;
        
        public static String hashMD5(String password) {
                String hashword = null;
                try {
                        MessageDigest md5 = MessageDigest.getInstance("MD5");
                        md5.update(password.getBytes());
                        BigInteger hash = new BigInteger(1, md5.digest());
                        hashword = hash.toString(16);
                } catch (NoSuchAlgorithmException nsae) {
                        // ignore
                }
                return hashword;
        }
        
}