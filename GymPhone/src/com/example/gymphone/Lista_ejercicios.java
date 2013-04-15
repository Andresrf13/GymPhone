package com.example.gymphone;

public class Lista_ejercicios {
	long id_lista;
	String ejercicio;
	long rutina;
	long calorias;
	long tiempo;
	
	public void setid_lista(long num)
	{
		this.id_lista = num;
	}
	
	public void setejercicio(String num)
	{
		this.ejercicio = num;
	}
	
	public void setrutina(long num)
	{
		this.rutina = num;
	}
	
	public void setcalorias(long num)
	{
		this.calorias = num;
	}
	public void settiempo(long num)
	{
		this.tiempo = num;
	}
	
	public long getid_lista()
	{
		return id_lista;
	}
	
	public String getejercicio()
	{
		return ejercicio;
	}
	
	public long getrutina()
	{
		return rutina;
	}
	
	public long getcalorias()
	{
		return calorias;
	}
	
	public long gettiempo()
	{
		return tiempo;
	}
}
