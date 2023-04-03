package it.prova.televisorewithservices.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.prova.service.televisore.TelevisoreService;

import it.prova.televisoredaowithservices.model.Televisore;
import it.prova.televisoredaowithservices.service.MyServiceFactory;

public class TestTelevisore {

	public static void main(String[] args) {
		// parlo direttamente con il service
		TelevisoreService televisoreService = MyServiceFactory.getTelevisoreServiceImpl();

		try {

			// ora con il service posso fare tutte le invocazioni che mi servono
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + "elementi.");

//
//					testInserimentoNuovoTelevisore(televisoreService);
//					System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

//					testRimozioneTelevisore(televisoreService);
//					System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

//			testFindByExample(televisoreService);
//			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

//			testFindTelevisorePiuPollici(televisoreService);
			
//			testContaTelevisoriProdottiTraLeDueDate(televisoreService);
			
			testMarcheUltimiSeiMesi(televisoreService);

//					testUpdateUser(userService);
//					System.out.println("In tabella ci sono " + userService.listAll().size() + " elementi.");

			// E TUTTI I TEST VANNO FATTI COSI'

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void testInserimentoNuovoTelevisore(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testInserimentoNuovoTelevisore inizio.............");
		Televisore newTelevisoreInstance = new Televisore("Samsung", "S98GELT", 75, LocalDate.now());
		if (televisoreService.inserisciNuovo(newTelevisoreInstance) != 1)
			throw new RuntimeException("testInserimentoNuovoTelevisore FAILED ");

		System.out.println(".......testInserimentoNuovoTelevisore PASSED.............");
	}

	private static void testRimozioneTelevisore(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testRimozioneTelevisore inizio.............");
		// recupero tutti i televisori
		List<Televisore> interoContenutoTabella = televisoreService.listAll();
		if (interoContenutoTabella.isEmpty() || interoContenutoTabella.get(0) == null)
			throw new Exception("Non ho nulla da rimuovere");

		Long idDelPrimo = interoContenutoTabella.get(0).getId();
		// ricarico per sicurezza con l'id individuato
		Televisore toBeRemoved = televisoreService.findById(idDelPrimo);
		if (televisoreService.rimuovi(toBeRemoved.getId()) != 1)
			throw new RuntimeException("testRimozioneTelevisore FAILED ");

		System.out.println(".......testRimozioneTelevisore PASSED.............");
	}

	private static void testFindByExample(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testFindByExample inizio.............");
		// inserisco i dati che poi mi aspetto di ritrovare
		televisoreService.inserisciNuovo(new Televisore("Lg", "Kret-678", 68, LocalDate.now()));
		televisoreService.inserisciNuovo(new Televisore("Samsung", "S906-gt", 56, LocalDate.now()));

		// preparo un example che ha come nome 'as' e ricerco
		List<Televisore> risultatifindByExample = televisoreService.findByExample(new Televisore("sa"));
		if (risultatifindByExample.size() != 2)
			throw new RuntimeException("testFindByExample FAILED ");

		// se sono qui il test è ok quindi ripulisco i dati che ho inserito altrimenti
		// la prossima volta non sarebbero 2 ma 4, ecc.
		for (Televisore televisoreItem : risultatifindByExample) {
			televisoreService.rimuovi(televisoreItem.getId());
		}

		System.out.println(".......testFindByExample PASSED.............");
	}

	private static void testFindTelevisorePiuPollici(TelevisoreService televisoreService) throws Exception {
		System.out.println(".........testFindTelevisorePiuPollici inizio................");
		List<Televisore> elencoTelevisori = televisoreService.listAll();
		if (elencoTelevisori.size() < 1) {
			throw new RuntimeException("testFindTelevisorePiuPollici FAILED");
		}

		Televisore televisoreConPiuPollici = televisoreService.trovaTelevisoreConPiuPollici();

		System.out.println(televisoreConPiuPollici);
		
		System.out.println("...........testFindTelevisorePiuPollici PASSED..........");

	}
	
	
	private static void testContaTelevisoriProdottiTraLeDueDate(TelevisoreService televisoreService) throws Exception {
		System.out.println(".........testContaTelevisoriProdottiTraLeDueDate inizio................");
		int contatore = 0;
		LocalDate primaData = LocalDate.parse("2000-01-01");
		LocalDate secondaData = LocalDate.parse("2023-01-01");
		List<Televisore> elencoTelevisori = televisoreService.listAll();
		if (elencoTelevisori.size() < 1) {
			throw new RuntimeException("testContaTelevisoriProdottiTraLeDueDate FAILED");
		}
		
		 contatore = televisoreService.DimmiQuantiTelevisoriProdottiTraLeDueDate(primaData, secondaData);
		
		System.out.println(contatore);
		
		System.out.println(".............testContaTelevisoriProdottiTraLeDueDate PASSED............");
	}
	
	
	private static void testMarcheUltimiSeiMesi(TelevisoreService televisoreService) throws Exception{
		System.out.println(".........testMarcheUltimiSeiMesi inizio................");
		
		List<String> result = new ArrayList<>();
		
		result = televisoreService.marcheTelevisoreProdottiNegliUltimiSeiMesi();
		System.out.println(result);
		
		System.out.println(".........testMarcheUltimiSeiMesi PASSED................");


	}
}
