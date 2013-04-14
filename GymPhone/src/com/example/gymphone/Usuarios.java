package com.example.gymphone;

public class Usuarios {
	private long id;
	private String nombre;
	private String contrasena;
	private long edad;
	private long altura;
	private long peso;
	
	public long getid(){
		return this.id;	
	}
	
	public String getnombre(){
		return this.nombre;	
	}
	
	public String getcontrasena(){
		return this.contrasena;	
	}
	
	public long getedad(){
		return this.edad;	
	}
	
	public long getaltura(){
		return this.altura;	
	}
	
	public long getpeso(){
		return this.peso;	
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
	
	public void setedad(long l){
		this.edad = l;	
	}
	
	public void setaltura(long l){
		this.altura = l;	
	}
	
	public void setpeso(long l){
		this.peso = l;	
	}
}
