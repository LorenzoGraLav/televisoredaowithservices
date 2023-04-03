package it.prova.televisoredaowithservices.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Televisore {
	private Long id;
	private String marca;
	private String modello;
	private int pollici;
	private LocalDate dataProduzione;
	
	public Televisore() {
		
	}
	
	public Televisore(String marca) {
		this.marca = marca;
	}

	public Televisore(String marca, String modello) {
		super();
		this.marca = marca;
		this.modello = modello;
	}

	public Televisore(String marca, String modello, int pollici) {
		super();
		this.marca = marca;
		this.modello = modello;
		this.pollici = pollici;
	}

	public Televisore(String marca, String modello, int pollici, LocalDate dataProduzione) {
		super();
		this.marca = marca;
		this.modello = modello;
		this.pollici = pollici;
		this.dataProduzione = dataProduzione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public int getPollici() {
		return pollici;
	}

	public void setPollici(int pollici) {
		this.pollici = pollici;
	}

	public LocalDate getDataProduzione() {
		return dataProduzione;
	}

	public void setDataProduzione(LocalDate dataProduzione) {
		this.dataProduzione = dataProduzione;
	}

	@Override
	public String toString() {
		String dateProduzioneString = dataProduzione != null ? DateTimeFormatter.ofPattern("dd/MM/yyyy").format(dataProduzione)
				: " N.D.";

		return "Televisore [id=" + id + ", marca=" + marca + ", modello=" + modello + ", pollici=" + pollici
				+ ", dataProduzione=" + dateProduzioneString + "]";
	}
	
	
	
	
	
	
	
	
}
