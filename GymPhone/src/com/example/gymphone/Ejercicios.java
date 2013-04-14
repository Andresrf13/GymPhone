package com.example.gymphone;

public class Ejercicios {
	long id_ejercicio;
	String ejercicio_name;
	String tipo;
	String instrucciones;
	
	public long getid(){
		return id_ejercicio;
	}
	public String getejercicio_name(){
		return ejercicio_name;
	}
	
	public String gettipo(){
		return tipo;
	}
	
	public String getinstrucciones(){
		return instrucciones;
	}
	
	public void setid(long id){
		this.id_ejercicio = id;
	}
	
	public void setejercicio_name(String ejercicio_name){
		this.ejercicio_name = ejercicio_name;
	}
	
	public void settipo(String tipo){
		this.tipo = tipo;
	}
	
	public void setinstrucciones(String instrucciones){
		this.instrucciones = instrucciones;
	}
}
