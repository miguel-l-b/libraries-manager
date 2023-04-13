package main.gui.Windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.use_cases.GetLibrary;
import core.entities.Library;
import infrastructure.providers.api.CEPProvider;
import infrastructure.repositories.jackson.LibraryRepository;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.Font;

public class SelectPage implements ActionListener{
    public final GetLibrary REPOSITORY;
	public final CEPProvider API_CEP;


    JFrame frame = new JFrame();

    JLabel title = new JLabel("Selecionar itens");
    JLabel subtitle = new JLabel("Preencha de acordo com sua escolha de busca: ");

    JLabel labelName = new JLabel("Nome: ");
    JLabel labelCep = new JLabel("CEP: ");
    JLabel labelNumber = new JLabel("Número:");

    JTextField txtName = new JTextField();
    JTextField txtCep = new JTextField();
    JTextField txtNumber = new JTextField();

    JLabel status = new JLabel("");
    
    JButton btnGetAll = new JButton("Todas");
    JButton btnFinish = new JButton("Selecionar");

    String message = "";

    public SelectPage(LibraryRepository repository, CEPProvider apiCep){
        this.REPOSITORY = new GetLibrary(repository, repository);
        this.API_CEP = apiCep;

        this.title.setBounds(10, 5, 350, 30);
        this.title.setFont(new Font("Serif", Font.BOLD, 22));

        this.subtitle.setBounds(30, 30, 500, 30);
        this.subtitle.setFont(new Font("Serif", Font.PLAIN, 18));

        this.labelName.setBounds(30, 100, 160, 25);
        this.labelName.setFont(new Font("Serif", Font.BOLD, 18));
        this.txtName.setBounds(140, 100, 160, 25);

        this.labelCep.setBounds(30, 140, 160, 25);
        this.labelCep.setFont(new Font("Serif", Font.BOLD, 18));
        this.txtCep.setBounds(140, 140, 160, 25);
        
        this.labelNumber.setBounds(30, 180, 160, 25);
        this.labelNumber.setFont(new Font("Serif", Font.BOLD, 18));
        this.txtNumber.setBounds(140, 180, 160, 25);

        this.status.setBounds(30, 220, 250, 25);
        this.status.setFont(new Font("Serif", Font.PLAIN, 18));

        this.btnGetAll.setBounds(30, 260, 100, 40);
        this.btnGetAll.setFocusable(false);
        this.btnGetAll.addActionListener(this);

        this.btnFinish.setBounds(250, 260, 100, 40);
        this.btnFinish.setFocusable(false);
        this.btnFinish.addActionListener(this);

        this.frame.add(this.title);
        this.frame.add(this.subtitle);
        this.frame.add(this.labelName);
        this.frame.add(this.txtName);
        this.frame.add(this.labelCep);
        this.frame.add(this.txtCep);
        this.frame.add(this.labelNumber);
        this.frame.add(this.txtNumber);
        this.frame.add(this.status);
        this.frame.add(this.btnGetAll);
        this.frame.add(this.btnFinish);

        this.frame.setSize(400,400);
        this.frame.setLayout(null);
        this.frame.setVisible(true);

        this.handleLocation();
    }

    private void handleLocation() { 
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); 
        frame.setLocation(((d.width - frame.getWidth())/2), ((d.height - frame.getHeight())/3)); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnFinish) {
            try {
                
                if (this.txtName.getText().length() < 1 || this.txtName.getText() != null) {

                    for (Library l : REPOSITORY.getLibrariesBy(this.txtName.getText()))
                        message += l + "";
                }

                else if (this.txtCep.getText() != null) {
                    if (this.txtNumber.getText() == null)
                        for (Library l : REPOSITORY.getLibrariesBy(Integer.parseInt(this.txtCep.getText())))
                            message += l + "";                    
                    else
                        message = (REPOSITORY.getLibraryBy(this.txtCep.getText(), Integer.parseInt(this.txtNumber.getText())) + "");
                }
                else if (this.txtNumber.getText() != null) 
                    for (Library l : REPOSITORY.getLibrariesBy(Integer.parseInt(this.txtNumber.getText())))
                        message += l + "";
                
                this.status.setText(message);  
            }
            catch (Exception error) {
                this.status.setText(error.getMessage());
            }
        }
        else if (e.getSource() == this.btnGetAll) 
            new TablePage(REPOSITORY, API_CEP);
    }
}