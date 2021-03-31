package it.polito.tdp.lab04.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;


public class Model {
private CorsoDAO corsoDao;
private StudenteDAO studenteDao;
	
	public Model() {
		corsoDao = new CorsoDAO();
		studenteDao = new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi(){
		return corsoDao.getTuttiICorsi();
	}
	public List<Studente> getTuttiStudenti(){
		return studenteDao.getTuttiStudenti();
	}
	public Studente getStudente(int matricola){
		return studenteDao.getStudente(matricola);
	}
	public List<Studente> getTuttiStudentiCorso(Corso c){
		return  corsoDao.getStudentiIscrittiAlCorso(c);
	}
	public Corso getCorso(String nome) {
		return corsoDao.getCorso(nome);
	}
	public List<Corso> getCorsiIscrittiStudente(Studente s){
		return studenteDao.getCorsiIscrittiStudente(s);
	}
	public boolean iscrittoCorso(Studente s, Corso c) {
		for(Corso cor : getCorsiIscrittiStudente(s)) {
			if(cor.getCodins().equals(c.getCodins()))
				return true;
		}
		return false;
	}
	
	

}
