package com.example.gymphone;

public class Usuarios {
	private long id;
	private String nombre;
	private String contrasena;
	
	public long getid(){
		return this.id;	
	}
	
	public String getnombre(){
		return this.nombre;	
	}
	
	public String getcontrasena(){
		return this.contrasena;	
	}
	
	public void setid(long l){
		this.id = l;	
	}
	
	public void setnombre(String nombre){
		this.nombre = nombre;	
	}
	
	public void setcontrasena(String contra){
		this.contrasena = contra;	
	}
}
