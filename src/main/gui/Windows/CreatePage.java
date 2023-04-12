package main.gui.Windows;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.use_cases.CreateLibrary;
import infrastructure.repositories.jackson.LibraryRepository;

public class CreatePage implements ActionListener{

    public final CreateLibrary REPOSIROTY;

    private JFrame frame = new JFrame();

    private JLabel title = new JLabel("Create Page");
    private JLabel subtitle = new JLabel("Preencha os campos abaixo: ");
    
    private JTextField txtName = new JTextField();
    private JTextField txtCEP = new JTextField();
    private JTextField txtNumber = new JTextField();

    private JLabel status = new JLabel();
    private JLabel labelName = new JLabel("Name: ");
    private JLabel labelCep = new JLabel ("CEP: ");
    private JLabel labelNumber = new JLabel("Number: ");

    private JButton btnFinish = new JButton("Criar");

    public CreatePage(LibraryRepository repository){
        this.REPOSIROTY = new CreateLibrary(repository);

        this.title.setBounds(10, 5, 350, 30);
        this.title.setFont(new Font("Serif", Font.BOLD, 22));

        this.subtitle.setBounds(30, 30, 500, 30);
        this.subtitle.setFont(new Font("Serif", Font.PLAIN, 18));

        this.labelName.setBounds(30,100,120,25);
        this.labelName.setFont(new Font("Serif", Font.PLAIN,18));

        this.labelCep.setBounds(30,140,120,25);
        this.labelCep.setFont(new Font("Serif", Font.PLAIN,18));

        this.labelNumber.setBounds(30,180,120,25);
        this.labelNumber.setFont(new Font("Serif",Font.PLAIN,18));

        this.txtName.setBounds(140,100,160,25);
        this.txtCEP.setBounds(140,140,160,25);
        this.txtNumber.setBounds(140,180,160,25);

        this.status.setBounds(30,220,250,25);
        this.status.setFont(new Font("Serif", Font.PLAIN, 18));

        this.btnFinish.setBounds(250,250,100,40);
        this.btnFinish.setFocusable(false);
        this.btnFinish.addActionListener(this);

        this.frame.add(this.title);
        this.frame.add(this.subtitle);
        this.frame.add(this.labelName);
        this.frame.add(this.txtName);
        this.frame.add(this.labelCep);
        this.frame.add(this.txtCEP);
        this.frame.add(this.labelNumber);
        this.frame.add(this.txtNumber);
        this.frame.add(this.status);
        this.frame.add(this.btnFinish);

        this.frame.setSize(400,350);
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
       if(e.getSource() == this.btnFinish){
        try{
            REPOSIROTY.createLibrary(this.txtName.getText(),
                                     this.txtCEP.getText(), 
                                     Integer.valueOf(this.txtNumber.getText()));
            this.status.setText("Repositorio criado com sucesso.");
        }
        catch(Exception error){
            error.printStackTrace();
            this.status.setText(error.getMessage());
        }
       }
    }
    
}
