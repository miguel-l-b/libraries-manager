package main.gui.Windows;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import app.use_cases.GetLibrary;
import core.entities.CEP;
import core.entities.Library;
import core.entities.Logradouro;
import exceptions.InvalidValueException;
import infrastructure.providers.api.CEPProvider;

public class TablePage{
    private static GetLibrary REPOSITORY;
	private static CEPProvider API_CEP;
    
    private static Library[] libraries;
    private static String[][] newFields;
    private static String[] columnNames = {"Nome", "Logradouro", "Número", "Complemento", "Bairro", "Cidade", "Estado", "CEP"};

    private static JFrame frame = new JFrame();
    private static JTextArea txt = new JTextArea();
    private static JTable table;
    private static DefaultTableCellRenderer cellRenderer;
    
    private static String[] getRegister(Library data) throws Exception {
		if(data == null) throw new Exception();

		try {
			Logradouro l = API_CEP.getAddress(CEP.parseCep(data.getCep()));
            String[] fields = {data.getName(), l.getLogradouro(), data.getNumber() + "", l.getComplemento(), l.getBairro(), l.getCidade(), l.getEstado(), data.getCep()};
            return fields;
		} 
        catch (Exception e) { 
            String[] fields = {data.getName(), "", data.getNumber() + "", "", "", "", "", data.getCep()};
            return fields;
        }
	}

    public static void getAllLibraries() throws Exception  {
        try {
            libraries =  REPOSITORY.getAllLibraries();
            newFields = new String[libraries.length][columnNames.length];

            int line = 0;
            for (Library l : libraries) {
                newFields[line] = getRegister(l);
                line++;
            }
            run();
        }
        catch (InvalidValueException e) { System.out.println(e.getMessage()); }
    }

    public static void getLibrariesBy(String name) throws Exception {
        try {
            libraries =  REPOSITORY.getLibrariesBy(name);
            newFields = new String[libraries.length][columnNames.length];
    
            int line = 0;
            for (Library l : libraries) {
                newFields[line] = getRegister(l);
                line++;
            }
            run();
        }
        catch(InvalidValueException e) { System.out.println(e.getMessage()); }
    }

    public static void run() {

        table = new JTable(newFields, columnNames);
            
        table.getColumnModel().getColumn(0).setPreferredWidth(110);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(10);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(220);
        table.getColumnModel().getColumn(5).setPreferredWidth(70);
        table.getColumnModel().getColumn(6).setPreferredWidth(5);
        table.getColumnModel().getColumn(7).setPreferredWidth(50);
        
        cellRenderer = new DefaultTableCellRenderer();
        table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        frame.setSize(1000, 400);
        frame.setVisible(true);
        handleLocation();
    }
    public TablePage(GetLibrary repository, CEPProvider apiCep) {
        REPOSITORY = repository;
		API_CEP = apiCep;
    }
    // try {
        
        
        
    // } 
    // catch(Exception erro) {
    //     System.out.println(erro.getMessage());
    // }
    
    private static void handleLocation(){
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); 
        frame.setLocation(((d.width - frame.getWidth())/2), ((d.height - frame.getHeight())/3));
    }
}