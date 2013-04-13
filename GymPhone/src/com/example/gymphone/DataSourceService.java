package com.example.gymphone;

import java.util.ArrayList;
import java.util.List;


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
		String[] columnas = {"id_user", "username", "password"};
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
		
		return nuevo_usuario;
		}


}
