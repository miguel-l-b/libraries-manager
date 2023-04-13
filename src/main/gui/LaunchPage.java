package main.gui;

import javax.swing.*;

import infrastructure.providers.api.CEPProvider;
import infrastructure.repositories.jackson.LibraryRepository;
import main.gui.Windows.CreatePage;
import main.gui.Windows.DeletePage;
import main.gui.Windows.SelectPage;
import main.gui.Windows.UpdatePage;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;

public class LaunchPage implements ActionListener {
    public final CEPProvider API_CEP;
    public final LibraryRepository REPOSITORY;

    private JFrame frame = new JFrame();

    private JLabel title = new JLabel("Launch Page");
    private JLabel subtitle = new JLabel("Escolha o que deseja fazer:");

    private JButton btnCreate = new JButton("Create");
    private JButton btnDelete = new JButton("Delete");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnSelect = new JButton("Select");
    
    public LaunchPage(String pathData, String urlApi) {

        this.API_CEP = new CEPProvider(urlApi);
        this.REPOSITORY = new LibraryRepository(pathData);
        //Definição do tamanho de title na LaunchPage e sua fonte
        this.title.setBounds(30, 5, 350, 30);
        this.title.setFont(new Font("Serif", Font.BOLD, 22));

        this.subtitle.setBounds(30, 30, 350, 30);
        this.subtitle.setFont(new Font("TimesNewRoman",Font.PLAIN, 18));

        this.btnCreate.setBounds(50,70,100,40);
        this.btnCreate.setFocusable(false);
        this.btnCreate.addActionListener(this);

        this.btnUpdate.setBounds(50,140,100,40);
        this.btnUpdate.setFocusable(false);
        this.btnUpdate.addActionListener(this);

        this.btnDelete.setBounds(250,70,100,40);
        this.btnDelete.setFocusable(false);
        this.btnDelete.addActionListener(this);

        this.btnSelect.setBounds(250,140,100,40);
        this.btnSelect.setFocusable(false);
        this.btnSelect.addActionListener(this);
        
        this.frame.add(this.title);
        this.frame.add(this.subtitle);
        this.frame.add(this.btnCreate);
        this.frame.add(this.btnUpdate);
        this.frame.add(this.btnDelete);
        this.frame.add(this.btnSelect);
        

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(420, 250);
        this.frame.setLayout(null);
        this.frame.setVisible(true);
        this.handleLocation();
    }
    @Override 
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.btnCreate) new CreatePage(this.REPOSITORY);
        else if(e.getSource() == this.btnDelete) new DeletePage(this.REPOSITORY);
        else if(e.getSource() == this.btnSelect) new SelectPage(this.REPOSITORY, this.API_CEP);
        else if(e.getSource() == this.btnUpdate) new UpdatePage(this.REPOSITORY);
    }
    
    private void handleLocation() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); 
        frame.setLocation(((d.width - frame.getWidth())/2), ((d.height - frame.getHeight())/3));
    }
}
