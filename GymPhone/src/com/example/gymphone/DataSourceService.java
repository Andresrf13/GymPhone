package com.example.gymphone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;



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

	public int update_perfil(String nombre, String edad1, String estatura1, String peso1) {
		// TODO Auto-generated method stub
		//String Line = "UPDATE usuarios SET edad="+edad1+", estatura="+estatura1+", peso="+peso1+" WHERE username='"+nombre+"';";
		ContentValues cv=new ContentValues();
		cv.put("edad",edad1);
		cv.put("altura",estatura1);
		cv.put("peso",peso1);				
		//database.beginTransaction();
		this.open();
		int resp = 0;
		resp = database.update("usuarios", cv, "username='"+nombre+"'", null);
		//database.insert("usuarios", null, cv);
		this.close();
		//database.execSQL(Line);
		//database.endTransaction();
				
		return resp;
	}

}
