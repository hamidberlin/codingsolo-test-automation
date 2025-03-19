package de.codingsolo.selenium.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Diese Klasse verwendet Apache POI, um Excel-Daten aus einer .xlsx-Datei auszulesen.
 */
public class Apache_POI {
	
    /**
     * Liest die Daten aus einer Excel-Datei und speichert sie in einer ArrayList.
     * 
     * @param filepath Pfad zur Excel-Datei (.xlsx)
     * @return ArrayList mit den gelesenen Daten
     * @throws IOException Falls die Datei nicht gefunden wird oder ein Fehler beim Lesen auftritt.
     */
	public ArrayList<Object[]> getExcelData(String filepath) throws IOException {
		
		// Liste zum Speichern der Daten
		ArrayList<Object[]> output = new ArrayList<>();

		// Try-with-resources: Automatisches Schließen der Ressourcen (Vermeidung von Speicherlecks)
		try (FileInputStream is = new FileInputStream(filepath);
		     XSSFWorkbook workbook = new XSSFWorkbook(is)) {
			
			// Zugriff auf das Arbeitsblatt mit dem Namen "Testform3"
			XSSFSheet worksheet = workbook.getSheet("Testform3");
			
			// Iteration über alle Zeilen der Tabelle
			for (Row row : worksheet) {
				
				// Überspringe die erste Zeile (Header)
				if (row.getRowNum() >= 1) {
					
					// Array zur Speicherung der Zellenwerte in der aktuellen Zeile
					Object[] values = new Object[row.getPhysicalNumberOfCells()];
					
					// Iteriere über alle Zellen der aktuellen Zeile
					Iterator<Cell> cellIterator = row.cellIterator();
					
					int column = 0;
					while (cellIterator.hasNext()) {
						Cell cellValue = cellIterator.next();
						
						// Prüfen, ob die Zelle einen String oder eine Zahl enthält
						if (cellValue.getCellType() == CellType.STRING) {
							values[column] = cellValue.getStringCellValue();
						} else {
							values[column] = NumberToTextConverter.toText(cellValue.getNumericCellValue());
						}
						column++;
					}
					
					// Speichert die Zeilen-Daten in die ArrayList
					output.add(values);
				}
			}
		}
		
		// Rückgabe der gesammelten Daten
		return output;
	}

    /**
     * Hauptmethode zum Testen der getExcelData-Methode.
     * 
     * @param args Kommandozeilenargumente
     * @throws IOException Falls ein Fehler beim Lesen der Datei auftritt.
     */
	public static void main(String[] args) throws IOException {
		Apache_POI testFall = new Apache_POI();
		
		// Pfad zur Excel-Datei (anpassen, falls nötig)
		ArrayList<Object[]> list = testFall.getExcelData("src/test/resources/TestCaseTestform3.xlsx");
		
		// Ausgabe der Werte in der Konsole
		for (Object[] row : list) {
			for (Object cell : row) {
				System.out.print(cell + "\t");
			}
			System.out.println(); // Neue Zeile nach jeder Reihe
		}
	}
}
