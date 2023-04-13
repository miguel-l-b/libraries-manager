package main.gui.Windows;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import app.use_cases.GetLibrary;
import core.entities.CEP;
import core.entities.Library;
import core.entities.Logradouro;
import infrastructure.providers.api.CEPProvider;

public class TablePage{
    public final GetLibrary REPOSITORY;
	public final CEPProvider API_CEP;
    
    JFrame frame = new JFrame();
    JTextArea txt = new JTextArea();
    JTable table;
    DefaultTableCellRenderer cellRenderer;

    public String[][] newFields;
    
    private String[] getRegister(Library data) throws Exception {
		if(data == null) throw new Exception();
		try {
			Logradouro l = this.API_CEP.getAddress(CEP.parseCep(data.getCep()));
            String[] fields = {data.getName(), l.getLogradouro(), data.getNumber() + "", l.getComplemento(), l.getBairro(), l.getCidade(), l.getEstado(), data.getCep()};
            return fields;
		} catch (Exception e) { 
            // address = String.format("%s, %d", data.getCep(), data.getNumber()); 
            String[] fields = {data.getName(), "", data.getNumber() + "", "", "", "", "", data.getCep()};
            return fields;
        }
	}

    public void getAllLibraries(Library[] libraries) throws Exception {
        int line = 0;
        for (Library l : libraries) {
            this.newFields[line] = this.getRegister(l);
            line++;
        }
    }

    public TablePage(GetLibrary repository, CEPProvider apiCep, int choice) {
        this.REPOSITORY = repository;
		this.API_CEP = apiCep;

        try {
            Library[] libraries =  REPOSITORY.getAllLibraries();
            String[] columnNames = {"Nome", "Logradouro", "Número", "Complemento", "Bairro", "Cidade", "Estado", "CEP"};
            newFields = new String[libraries.length][columnNames.length];
            
            switch (choice) {
                case(1):
                    this.getAllLibraries(libraries);
                    break;
                case(2):


            }

            table = new JTable(this.newFields, columnNames);
            
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
            
        } 
        catch(Exception erro) {
            System.out.println(erro.getMessage());
        }

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        frame.setSize(1000, 400);
        frame.setVisible(true);
        handleLocation();
    }
    
    private void handleLocation(){
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); 
        frame.setLocation(((d.width - frame.getWidth())/2), ((d.height - frame.getHeight())/3));
    }
}