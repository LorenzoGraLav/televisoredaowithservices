package it.prova.televisoredaowithservices.service;

import it.prova.service.televisore.TelevisoreService;
import it.prova.service.televisore.TelevisoreServiceImpl;
import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDaoImpl;

public class MyServiceFactory {
	public static TelevisoreService getTelevisoreServiceImpl() {
		TelevisoreService televisoreService = new TelevisoreServiceImpl();
		televisoreService.setTelevisoreDao(new TelevisoreDaoImpl());
		return televisoreService;
	}
}
