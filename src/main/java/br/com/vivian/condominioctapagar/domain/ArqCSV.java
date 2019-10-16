package br.com.vivian.condominioctapagar.domain;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ArqCSV {

	

	public static void main(String[] args) throws IOException {

		CSVReader arqCsv = new CSVReader(new FileReader("ExtratoCC.csv"), ';');

		List<CtaPagar> ctaPagar1 = new ArrayList<CtaPagar>();

		// read line by line
		String[] record = null;

		while ((record = arqCsv.readNext()) != null) {
			CtaPagar ctaPagar = new CtaPagar();
			ctaPagar.setObservacao(record[0]);
			ctaPagar.setDocValido(record[1]);
			ctaPagar.setComprovPgto(record[2]);
			ctaPagar.setPendente(record[3]);
			ctaPagar1.add(ctaPagar);
		}

		System.out.println(ctaPagar1);

	}
}
