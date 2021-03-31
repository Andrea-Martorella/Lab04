package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> listaCorsi;
    
    @FXML
    private Button btnControllaIscrizione;

    @FXML
    private Button btnCerca;

    @FXML
    private TextField txtMatricola;

    @FXML
    private CheckBox checkBox;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea areaResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	areaResult.clear();
    	if(txtMatricola.getText().matches("[a-zA-Z]+\\.?")) {
    		areaResult.appendText("inserire una matricola valida");
    		return;
    	}
    	Studente c = model.getStudente(Integer.parseInt(txtMatricola.getText()));
    	List<Corso> studenti = model.getCorsiIscrittiStudente(c);
    	for(Corso s : studenti)
    		areaResult.appendText(s.toString()+"\n");
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	areaResult.clear();
    	if(listaCorsi.getValue().compareTo("")==0) {
    		areaResult.appendText("scegliere un corso");
    		return;
    	}
    	Corso c = model.getCorso(listaCorsi.getValue());
    	List<Studente> studenti = model.getTuttiStudentiCorso(c);
    	for(Studente s : studenti)
    		areaResult.appendText(s.toString()+"\n");
    	
    }

    @FXML
    void doCheckBox(ActionEvent event) {
    	if(txtMatricola.getText().matches("[a-zA-Z]+\\.?")) {
    		txtNome.setText("inserire matricola valida");
    		checkBox.setPickOnBounds(false);
    		return;
    	}
    	int matricola = Integer.parseInt(txtMatricola.getText());
    	Studente s = model.getStudente(matricola);
    	if(s==null) {
    		txtNome.setText("matricola");
    		txtCognome.setText("non valide");
    		return;
    	}
    	txtNome.setText(s.getNome());
    	txtCognome.setText(s.getCognome());
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	
    	
    	
   
    }

    @FXML
    void doControllaIscrizione(ActionEvent event) {
    	areaResult.clear();
    	if(txtMatricola.getText().matches("[a-zA-Z]+\\.?")) {
    		areaResult.appendText("inserire una matricola valida");
    		return;
    	}
    	Studente d = model.getStudente(Integer.parseInt(txtMatricola.getText()));
    	Corso c = model.getCorso(listaCorsi.getValue());
    	boolean iscritto = model.iscrittoCorso(d, c);
    	if(iscritto) {
    		areaResult.appendText("Studente iscritto");
    		return;
    	}
    	else {
    		areaResult.appendText("Studente non iscritto");
    		return;
    	}
    }
    
    @FXML
    void doReset(ActionEvent event) {
    	areaResult.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtMatricola.clear();
    	
    }

    @FXML
    void initialize() {
        assert listaCorsi != null : "fx:id=\"listaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert checkBox != null : "fx:id=\"checkBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert areaResult != null : "fx:id=\"areaResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnControllaIscrizione != null : "fx:id=\"btnControllaIscrizione\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    public void setModel(Model model) {
    	this.model = model;
    	List<Corso> corsi = model.getTuttiICorsi();
    	listaCorsi.getItems().add("");
    	List<String> corsiNomi = new ArrayList<>();
    	for(Corso c : corsi)
    		corsiNomi.add(c.getNome());
    	listaCorsi.getItems().addAll(corsiNomi);
    	
    }
    
}
