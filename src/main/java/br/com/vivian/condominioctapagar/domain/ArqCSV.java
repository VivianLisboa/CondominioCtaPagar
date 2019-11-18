package br.com.vivian.condominioctapagar.domain;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;


public class ArqCSV {

	public static void main(String[] args) throws IOException {
//CSVReader reader = new CSVReader(new StringReader("one,two,three"));
		CSVReader arqCsv = new CSVReader(new FileReader("ExtratoCC.csv"), ';');

		List<CtaPagar> ctaPagar1 = new ArrayList<CtaPagar>();

		// read line by line
		String[] record = null;

		while ((record = arqCsv.readNext()) != null) {
			CtaPagar ctaPagar = new CtaPagar();
			ctaPagar.setId(Integer.parseInt(record[0]));
			
			ctaPagar.setData(record[0]);
			ctaPagar.setHistorico(record[1]);
			ctaPagar.setDebito(Double.parseDouble(record[2]));
			ctaPagar.setCredito(Double.parseDouble(record[3]));
			ctaPagar.setSaldo(Double.parseDouble(record[4]));
			ctaPagar.setObservacao(record[5]);
			ctaPagar.setDocValido(record[6]);
			ctaPagar.setComprovPgto(record[7]);
			ctaPagar.setPendente(record[8]);
			ctaPagar1.add(ctaPagar);
		}

		System.out.println(ctaPagar1);

	}
}
