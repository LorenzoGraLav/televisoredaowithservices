package it.prova.service.televisore;

import java.time.LocalDate;
import java.util.List;

import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDAO;
import it.prova.televisoredaowithservices.model.Televisore;



public interface TelevisoreService {
	// questo mi serve per injection
		public void setTelevisoreDao(TelevisoreDAO televisoreDao);

		public List<Televisore> listAll() throws Exception;

		public Televisore findById(Long idInput) throws Exception;

		public int aggiorna(Televisore input) throws Exception;

		public int inserisciNuovo(Televisore input) throws Exception;

		public int rimuovi(Long idDaCancellare) throws Exception;

		public List<Televisore> findByExample(Televisore input) throws Exception;
		
		public Televisore trovaTelevisoreConPiuPollici() throws Exception;
		public int DimmiQuantiTelevisoriProdottiTraLeDueDate(LocalDate primaData, LocalDate secondaData) throws Exception;
		public List<String> marcheTelevisoreProdottiNegliUltimiSeiMesi() throws Exception;
}
