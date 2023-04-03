package it.prova.service.televisore;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import it.prova.televisoredaowithservices.connection.MyConnection;
import it.prova.televisoredaowithservices.dao.Constants;
import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public class TelevisoreServiceImpl implements TelevisoreService {

	private TelevisoreDAO televisoreDao;

	public void setTelevisoreDao(TelevisoreDAO televisoreDao) {
		this.televisoreDao = televisoreDao;
	}

	@Override
	public List<Televisore> listAll() throws Exception {
		List<Televisore> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public Televisore findById(Long idInput) throws Exception {

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Televisore result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public int aggiorna(Televisore input) throws Exception {

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.update(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public int inserisciNuovo(Televisore input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	public int rimuovi(Long idDaCancellare) throws Exception {
		if (idDaCancellare < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.delete(idDaCancellare);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	public List<Televisore> findByExample(Televisore input) throws Exception {
		List<Televisore> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.findByExample(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	@Override
	public Televisore trovaTelevisoreConPiuPollici() throws Exception {
		Televisore televisoreGrande = null;
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);
		televisoreGrande = televisoreDao.findTelevisorePiuGrande();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return televisoreGrande;
		
	}

	@Override
	public int DimmiQuantiTelevisoriProdottiTraLeDueDate(LocalDate primaData, LocalDate secondaData) throws Exception {
		int contaTelevisori = 0;
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);
		contaTelevisori = televisoreDao.quantiTelevisoriProdottiTraDueDate(primaData, secondaData);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return contaTelevisori;
		
	}

	@Override
	public List<String> marcheTelevisoreProdottiNegliUltimiSeiMesi() throws Exception {
		
		List<String> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.marcheTelevisoreProdottiNegliUltimiSeiMesi();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

}
