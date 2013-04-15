package com.example.gymphone;


public class Lista_Rutinas {
	long id_rutina;
	long rutina_id;
	String rutina_name;
	
	public long getid(){
		return this.id_rutina;	
	}
	
	public String getname(){
		return this.rutina_name;	
	}
	
	public long getpertenece(){
		return this.rutina_id;	
	}
	
	public void setid(long nombre){
		this.id_rutina = nombre;	
	}
	
	public void setrutina_id(long nombre){
		this.rutina_id = nombre;	
	}
	
	public void setname(String nombre){
		this.rutina_name = nombre;	
	}
	
	
}
