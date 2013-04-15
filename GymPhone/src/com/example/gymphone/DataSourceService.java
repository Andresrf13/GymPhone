package com.example.gymphone;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;



public class DataSourceService {
	private SQLiteDatabase database;
	private DataBaseHelper dbHelper;
	
	
	public DataSourceService(Context context) {
		dbHelper = new DataBaseHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	//-------------------------------USUARIOS BASE DE DATOS---------------------------//
	public List<Usuarios> getUsuarios()
	{
		List<Usuarios> usuarios_obtenidos = new ArrayList<Usuarios>(); 
		String[] columnas = {"id_user", "username", "password", "edad", "altura", "peso"};
		Cursor c = database.query("usuarios", columnas, null, null, null, null, null);
		
		c.moveToFirst();
		while (!c.isAfterLast()) {
			Usuarios obtiene = cursorToService(c);
			usuarios_obtenidos.add(obtiene);
			c.moveToNext();
		}
		
		c.close();
		return usuarios_obtenidos;
	}

	private Usuarios cursorToService(Cursor c) {		
		Usuarios nuevo_usuario = new Usuarios();
		nuevo_usuario.setid(c.getLong(0));
		nuevo_usuario.setnombre(c.getString(1));
		nuevo_usuario.setcontrasena(c.getString(2));
		nuevo_usuario.setedad(c.getLong(3));
		nuevo_usuario.setaltura(c.getLong(4));
		nuevo_usuario.setpeso(c.getLong(5));
		
		return nuevo_usuario;
		}
	
	//---------------------------------------ACTUALIZAR PERFIL DE PERSONA ------------------------------//

	public int update_perfil(String nombre, String edad1, String estatura1, String peso1) {		
		ContentValues cv=new ContentValues();
		cv.put("edad",edad1);
		cv.put("altura",estatura1);
		cv.put("peso",peso1);						
		this.open();
		int resp = 0;
		resp = database.update("usuarios", cv, "username='"+nombre+"'", null);		
		this.close();
		
				
		return resp;
	}

	 //----------------------------------OBTENER EJERCICIOS EN BASE DE DATOS --------------------//
	public List<Ejercicios> getEjercicios() {
		List<Ejercicios> ejercicio = new ArrayList<Ejercicios>(); 
		String[] columnas = {"id_ejercicio", "ejercicio_name", "tipo", "instrucciones"};
		Cursor c = database.query("ejercicio", columnas, null, null, null, null, null);
		
		c.moveToFirst();
		while (!c.isAfterLast()) {
			Ejercicios obtiene = SetEjerjcio(c);
			ejercicio.add(obtiene);
			c.moveToNext();
		}
		
		c.close();
		return ejercicio;		
	}

	private Ejercicios SetEjerjcio(Cursor c) {
		Ejercicios nuevo_ejercicio = new Ejercicios();
		nuevo_ejercicio.setid(c.getLong(0));
		nuevo_ejercicio.setejercicio_name(c.getString(1));
		nuevo_ejercicio.settipo(c.getString(2));
		nuevo_ejercicio.setinstrucciones(c.getString(3));
		return nuevo_ejercicio;
	}

	//----------------------------------OBTENER RUTINAS ---------------------------//
	public List<Rutinas> getRutinas() {
		List<Rutinas> rutina = new ArrayList<Rutinas>(); 
		String[] columnas = {"id_rutina", "rutina_name", "pertenece"};
		Cursor c = database.query("rutinas", columnas, null, null, null, null, null);
		
		c.moveToFirst();
		while (!c.isAfterLast()) {
			Rutinas obtiene = setRutina(c);
			rutina.add(obtiene);
			c.moveToNext();
		}
		
		c.close();
		return rutina;		
	}

	private Rutinas setRutina(Cursor c) {
		Rutinas nueva_rutina = new Rutinas();
		nueva_rutina.setid(c.getLong(0));
		nueva_rutina.setname(c.getString(1));
		nueva_rutina.setpertenece(c.getString(2));		
		return nueva_rutina;
	}
	
	//----------------------------------OBTENER LISTA_RUTINAS ---------------------------//
		public List<Lista_Rutinas> getListaRutinas() {
			List<Lista_Rutinas> rutina = new ArrayList<Lista_Rutinas>(); 
			String[] columnas = {"id_listarutina", "rutina", "listarutina_name"};
			Cursor c = database.query("lista_rutinas", columnas, null, null, null, null, null);
			
			c.moveToFirst();
			while (!c.isAfterLast()) {
				Lista_Rutinas obtiene = setListaRutina(c);
				rutina.add(obtiene);
				c.moveToNext();
			}
			
			c.close();
			return rutina;		
		}

		private Lista_Rutinas setListaRutina(Cursor c) {
			Lista_Rutinas nueva_rutina = new Lista_Rutinas();
			nueva_rutina.setid(c.getLong(0));	
			nueva_rutina.setrutina_id(c.getLong(1));
			nueva_rutina.setname(c.getString(2));
			return nueva_rutina;
		}

		
		//---------------------------------------------OBTENER LISTA_EJERCICIOS-----------------------------/
		public List<Lista_ejercicios> getListaEjercicios(long x) {	
			String s = String.valueOf(x);
			String[] args = {s};
			List<Lista_ejercicios> ejercicios = new ArrayList<Lista_ejercicios>(); 
			String[] columnas = {"id_lista", "ejercicio", "rutina", "calorias", "tiempo"};
			Cursor c = database.query("lista_ejercicios", columnas, "rutina=?", args, null, null, null);
			
			c.moveToFirst();
			while (!c.isAfterLast()) {
				Lista_ejercicios obtiene = setListaEjercicios(c);
				ejercicios.add(obtiene);
				c.moveToNext();
			}
			
			c.close();
			return ejercicios;		
		}

		private Lista_ejercicios setListaEjercicios(Cursor c) {
			Lista_ejercicios nuevo_ejercicio = new Lista_ejercicios();
			nuevo_ejercicio.setid_lista(c.getLong(0));	
			nuevo_ejercicio.setejercicio(c.getString(1));
			nuevo_ejercicio.setrutina(c.getLong(2));
			nuevo_ejercicio.setcalorias(c.getLong(3));
			nuevo_ejercicio.settiempo(c.getLong(4));
			return nuevo_ejercicio;
		}

		public List<Lista_ejercicios> getListaEjercicios() {					
			List<Lista_ejercicios> ejercicios = new ArrayList<Lista_ejercicios>(); 
			String[] columnas = {"id_lista", "ejercicio", "rutina", "calorias", "tiempo"};
			Cursor c = database.query("lista_ejercicios", columnas, null, null, null, null, null);
			
			c.moveToFirst();
			while (!c.isAfterLast()) {
				Lista_ejercicios obtiene = setListaEjercicios(c);
				ejercicios.add(obtiene);
				c.moveToNext();
			}
			
			c.close();
			return ejercicios;			
		}
}
