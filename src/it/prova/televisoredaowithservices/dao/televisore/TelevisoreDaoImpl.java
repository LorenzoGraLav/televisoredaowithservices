package it.prova.televisoredaowithservices.dao.televisore;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.prova.televisoredaowithservices.dao.AbstractMySQLDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public class TelevisoreDaoImpl extends AbstractMySQLDAO implements TelevisoreDAO {
	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Televisore> list() throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Televisore> result = new ArrayList<Televisore>();

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from televisore")) {

			while (rs.next()) {
				Televisore televisoreTemp = new Televisore();
				televisoreTemp.setMarca(rs.getString("MARCA"));
				televisoreTemp.setModello(rs.getString("MODELLO"));
				televisoreTemp.setPollici(rs.getInt("POLLICI"));
				televisoreTemp.setDataProduzione(
						rs.getDate("DATAPRODUZIONE") != null ? rs.getDate("DATAPRODUZIONE").toLocalDate() : null);
				televisoreTemp.setId(rs.getLong("ID"));
				result.add(televisoreTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Televisore get(Long idInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Televisore result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from televisore where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Televisore();
					result.setMarca(rs.getString("MARCA"));
					result.setModello(rs.getString("MODELLO"));
					result.setPollici(rs.getInt("POLLICI"));
					result.setDataProduzione(
							rs.getDate("DATAPRODUZIONE") != null ? rs.getDate("DATAPRODUZIONE").toLocalDate() : null);
					result.setId(rs.getLong("ID"));
				} else {
					result = null;
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Televisore televisoreInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (televisoreInput == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO televisore (marca, modello, pollici, dataproduzione) VALUES (?, ?, ?, ?);")) {
			ps.setString(1, televisoreInput.getMarca());
			ps.setString(2, televisoreInput.getModello());
			ps.setInt(3, televisoreInput.getPollici());
			// quando si fa il setDate serve un tipo java.sql.Date
			ps.setDate(4, java.sql.Date.valueOf(televisoreInput.getDataProduzione()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int update(Televisore televisoreInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (televisoreInput == null || televisoreInput.getId() == null || televisoreInput.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE televisore SET marca=?, modello=?, pollici=?, dataproduzione=? where id=?;")) {
			ps.setString(1, televisoreInput.getMarca());
			ps.setString(2, televisoreInput.getModello());
			ps.setInt(3, televisoreInput.getPollici());
			// quando si fa il setDate serve un tipo java.sql.Date
			ps.setDate(5, java.sql.Date.valueOf(televisoreInput.getDataProduzione()));
			ps.setLong(6, televisoreInput.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Long idDaRimuovere) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idDaRimuovere < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM televisore WHERE ID=?")) {
			ps.setLong(1, idDaRimuovere);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Televisore> findByExample(Televisore example) throws Exception {

		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (example == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Televisore> result = new ArrayList<Televisore>();

		String query = "select * from televisore where 1=1 ";
		if (example.getMarca() != null && !example.getMarca().isEmpty()) {
			query += " and marca like '" + example.getMarca() + "%' ";
		}

		if (example.getModello() != null && !example.getModello().isEmpty()) {
			query += " and modello like '" + example.getModello() + "%' ";
		}

		if (example.getPollici() != 0) {
			query += " and pollici like '" + example.getPollici() + "%' ";
		}

		if (example.getDataProduzione() != null) {
			query += " and DATECREATED='" + java.sql.Date.valueOf(example.getDataProduzione()) + "' ";
		}

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				Televisore televisoreTemp = new Televisore();
				televisoreTemp.setMarca(rs.getString("MARCA"));
				televisoreTemp.setModello(rs.getString("MODELLO"));
				televisoreTemp.setPollici(rs.getInt("POLLICI"));
				televisoreTemp.setDataProduzione(
						rs.getDate("DATAPRODUZIONE") != null ? rs.getDate("DATAPRODUZIONE").toLocalDate() : null);
				televisoreTemp.setId(rs.getLong("ID"));
				result.add(televisoreTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Televisore findTelevisorePiuGrande() throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		Televisore televisorePiuGrande = new Televisore();
		try (PreparedStatement ps = connection.prepareStatement("select * from televisore where pollici = (select max(pollici) from televisore)")) {

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					Televisore televisoreTemp = new Televisore();
					televisoreTemp.setMarca(rs.getString("MARCA"));
					televisoreTemp.setModello(rs.getString("MODELLO"));
					televisoreTemp.setPollici(rs.getInt("POLLICI"));

					televisoreTemp.setDataProduzione(
							rs.getDate("DATAPRODUZIONE") != null ? rs.getDate("DATAPRODUZIONE").toLocalDate() : null);
					televisoreTemp.setId(rs.getLong("ID"));
					televisorePiuGrande = televisoreTemp;
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return televisorePiuGrande;

	}

	@Override
	public int quantiTelevisoriProdottiTraDueDate(LocalDate primaData, LocalDate secondaData) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		if(primaData == null && secondaData == null) {
			throw new Exception("non ci sono date!");
		}
		
		int contatore = 0;
		
		
		try (PreparedStatement ps = connection.prepareStatement("select * from televisore where dataproduzione between ? and ?")) {
			ps.setDate(1, java.sql.Date.valueOf(primaData));
			ps.setDate(2, java.sql.Date.valueOf(secondaData));
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					contatore++;
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		

	
		return contatore;
	}

	@Override
	public List<String> marcheTelevisoreProdottiNegliUltimiSeiMesi() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		List<String> result =  new ArrayList<>();
		
		try (PreparedStatement ps = connection.prepareStatement("select distinct (marca) from televisore where dataproduzione > ?;")) {
				ps.setDate(1, java.sql.Date.valueOf(LocalDate.now().minusMonths(6)));
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					String marcaTemp = "";
					marcaTemp = rs.getString("marca");
					result.add(marcaTemp);
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
		
	}

}
