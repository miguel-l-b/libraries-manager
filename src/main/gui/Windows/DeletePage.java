package main.gui.Windows;

import javax.swing.*;

import app.use_cases.DeleteLibrary;
import infrastructure.repositories.jackson.LibraryRepository;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.Font;
import java.awt.event.*;

public class DeletePage implements ActionListener {
    public final DeleteLibrary REPOSITORY;

    JFrame frame = new JFrame();
    JLabel title = new JLabel("Deletar itens");
    JLabel subtitle = new JLabel("Preencha os dados do valor que deseja excluir: ");

    JLabel labelCep = new JLabel("CEP: ");
    JLabel labelNumber = new JLabel("Número:");

    JTextField txtCep = new JTextField();
    JTextField txtNumber = new JTextField();

    JButton btnFinish = new JButton("Excluir");
    JButton goBack;

    JLabel status = new JLabel("");

    public DeletePage(LibraryRepository repository) {
        this.REPOSITORY = new DeleteLibrary(repository);

        this.title.setBounds(10, 5, 350, 30);
        this.title.setFont(new Font("Serif", Font.BOLD, 22));

        this.subtitle.setBounds(30, 30, 500, 30);
        this.subtitle.setFont(new Font("Serif", Font.PLAIN, 18));

        this.labelCep.setBounds(30, 100, 160, 25);
        this.labelCep.setFont(new Font("Serif", Font.BOLD, 18));
        this.txtCep.setBounds(140, 100, 160, 25);
        
        this.labelNumber.setBounds(30, 140, 160, 25);
        this.labelNumber.setFont(new Font("Serif", Font.BOLD, 18));
        this.txtNumber.setBounds(140, 140, 160, 25);

        this.status.setBounds(30, 200, 350, 25);
        this.status.setFont(new Font("Serif", Font.PLAIN, 18));

        this.btnFinish.setBounds(250, 220, 100, 40);
        this.btnFinish.setFocusable(false);
        this.btnFinish.addActionListener(this);


        this.frame.add(this.title);
        this.frame.add(this.subtitle);
        this.frame.add(this.labelCep);
        this.frame.add(this.txtCep);
        this.frame.add(this.labelNumber);
        this.frame.add(this.txtNumber);
        this.frame.add(this.status);
        this.frame.add(this.btnFinish);

        this.frame.setSize(400, 350);
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
                REPOSITORY.deleteLibrary(Integer.parseInt(this.txtCep.getText()), 
                                        Integer.parseInt(this.txtNumber.getText())
                );
                this.status.setText("Respositório deletado com sucesso.");
            } 
            catch(Exception error) {
                this.status.setText(error.getMessage());
            }
        }
    }
}
