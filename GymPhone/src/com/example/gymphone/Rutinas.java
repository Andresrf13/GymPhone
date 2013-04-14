package com.example.gymphone;

public class Rutinas {
	long id_rutina;
	String rutina_name;
	String pertenece;
	
	public long getid(){
		return this.id_rutina;	
	}
	
	public String getname(){
		return this.rutina_name;	
	}
	
	public String getpertenece(){
		return this.pertenece;	
	}
	
	public void setid(long nombre){
		this.id_rutina = nombre;	
	}
	
	public void setname(String nombre){
		this.rutina_name = nombre;	
	}
	
	public void setpertenece(String nombre){
		this.pertenece = nombre;	
	}
}
