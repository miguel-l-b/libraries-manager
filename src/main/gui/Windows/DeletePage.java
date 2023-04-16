package main.gui.Windows;

import javax.swing.*;

import app.use_cases.DeleteLibrary;
import app.use_cases.GetLibrary;
import core.entities.Library;
import infrastructure.repositories.jackson.LibraryRepository;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.Font;
import java.awt.event.*;

public class DeletePage implements ActionListener {
    private final GetLibrary REPOSITORY_FIND;
    public final DeleteLibrary REPOSITORY;

    private JFrame frame = new JFrame();
    private JLabel title = new JLabel("Deletar dados");
    private JLabel subtitle = new JLabel("Preencha os dados do valor que deseja excluir: ");

    private JLabel labelCep = new JLabel("CEP: ");
    private JLabel labelNumber = new JLabel("Número:");

    private JTextField txtCep = new JTextField();
    private JTextField txtNumber = new JTextField();

    private JButton btnFinish = new JButton("Excluir");

    private int confirmDialog;

    private JLabel status = new JLabel("");

    public DeletePage(LibraryRepository repository) {
        this.REPOSITORY_FIND = new GetLibrary(repository, repository);
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

        this.status.setBounds(30, 200, 450, 25);
        this.status.setFont(new Font("Serif", Font.PLAIN, 16));

        this.btnFinish.setBounds(250, 240, 100, 40);
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

        this.frame.setSize(420, 350);
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
                if (this.txtCep.getText().isEmpty() || this.txtNumber.getText().isEmpty())
                this.status.setText("Ambos os campos CEP e Número devem ser preenchidos!");
                else {
                    Library library = REPOSITORY_FIND.getLibraryBy(this.txtCep.getText(), Integer.parseInt(this.txtNumber.getText()));
                    
                    confirmDialog = JOptionPane.showOptionDialog(frame, "" + library.getName() +
                                                                 ", " + library.getEmail() + 
                                                                 ", " + library.getComplement() + 
                                                                 ", " + library.getCep() + 
                                                                 ", " + library.getNumber(), 
                                                                 "Deseja mesmo excluir?", 
                                                                 JOptionPane.OK_CANCEL_OPTION, 
                                                                 JOptionPane.QUESTION_MESSAGE, 
                                                                 null, 
                                                                 null, 
                                                                 null);
                    if (confirmDialog == JOptionPane.CANCEL_OPTION)
                        return;
                    else {
                        REPOSITORY.deleteLibrary(this.txtCep.getText(), 
                                                Integer.parseInt(this.txtNumber.getText())
                        );
                        this.status.setText("Respositório deletado com sucesso.");

                    }

                }
            } 
            catch(Exception error) {
                this.status.setText(error.getMessage());
            }
        }
    }
}
