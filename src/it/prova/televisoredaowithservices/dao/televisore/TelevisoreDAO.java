package it.prova.televisoredaowithservices.dao.televisore;


import java.time.LocalDate;
import java.util.List;


import it.prova.televisoredaowithservices.dao.IBaseDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public interface TelevisoreDAO extends IBaseDAO<Televisore> {
	public List<Televisore> list() throws Exception;
	public Televisore get(Long idInput) throws Exception;
	public int insert(Televisore televisoreInput) throws Exception;
	public int update(Televisore televisoreinput) throws Exception;
	public int delete(Long idDaRimuovere) throws Exception;
	public List<Televisore> findByExample(Televisore example) throws Exception;
	public Televisore findTelevisorePiuGrande() throws Exception;
	public int quantiTelevisoriProdottiTraDueDate(LocalDate primaData, LocalDate secondaData) throws Exception;
	public List<String> marcheTelevisoreProdottiNegliUltimiSeiMesi() throws Exception;
}
