package main.gui.Windows;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.use_cases.GetLibrary;
import app.use_cases.UpdateLibraryByCepAndNumber;
import infrastructure.repositories.jackson.LibraryRepository;

public class UpdatePage implements ActionListener {
    public final UpdateLibraryByCepAndNumber REPOSITORY_UPDATE;
	public final GetLibrary REPOSITORY_FIND;
    
    private JFrame frame = new JFrame();
    private JLabel title = new JLabel("Update Page");
    private JLabel subtitle = new JLabel("Preencha os campos para editar: ");
    private JLabel topicUan = new JLabel("CEP e Número antigos: ");
    
    private JLabel labelOldCep = new JLabel("CEP antigo:");
    private JLabel labelOldNumber = new JLabel("Número antigo:");

    private JTextField txtOldCep = new JTextField();
    private JTextField txtOldNumber = new JTextField();
    
    private JLabel topicTchu = new JLabel("Novos valores: ");
    
    private JLabel labelNewName = new JLabel("Novo Nome:");
    private JLabel labelNewCep = new JLabel("Novo CEP:");
    private JLabel labelNewNumber = new JLabel("Novo Número:");

    private JTextField txtNewName = new JTextField();
    private JTextField txtNewCep = new JTextField();
    private JTextField txtNewNumber = new JTextField();
    
    private JLabel status = new JLabel();

    private JButton btnFinish = new JButton("Alterar");

    public UpdatePage(LibraryRepository repository) {
        this.REPOSITORY_FIND = new GetLibrary(repository, repository);
		this.REPOSITORY_UPDATE = new UpdateLibraryByCepAndNumber(repository);

        this.title.setBounds(10, 5, 350, 30);
        this.title.setFont(new Font("Serif", Font.BOLD, 22));

        this.subtitle.setBounds(30, 35, 500, 30);
        this.subtitle.setFont(new Font("Serif", Font.PLAIN, 18));

        this.topicUan.setBounds(30, 80, 350,30);
        this.topicUan.setFont(new Font("Serif", Font.BOLD, 18));

        this.labelOldCep.setBounds(30, 120,160,25);
        this.labelOldCep.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtOldCep.setBounds(160,120,160,25);

        this.labelOldNumber.setBounds(30,150,160,25);
        this.labelOldNumber.setFont(new Font("Serif", Font.PLAIN, 18));
		this.txtOldNumber.setBounds(160,150,160,25);

        this.topicTchu.setBounds(30,200,350,30);
        this.topicTchu.setFont(new Font("Serif", Font.BOLD, 18));

        this.labelNewName.setBounds(30,240,160,25);
        this.labelNewName.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtNewName.setBounds(160,240,160,25);

        this.labelNewCep.setBounds(30,270,160,25);
        this.labelNewCep.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtNewCep.setBounds(160,270,160,25);

        this.labelNewNumber.setBounds(30,300,160,25);
        this.labelNewNumber.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtNewNumber.setBounds(160,300,160,25);

        this.status.setBounds(30,370,250,25);
        this.status.setFont(new Font("Serif", Font.PLAIN, 18));

        this.btnFinish.setBounds(300,390,100,40);
        this.btnFinish.setFocusable(false);
        this.btnFinish.addActionListener(this);


        this.frame.add(this.title);
        this.frame.add(this.subtitle);
        this.frame.add(this.topicUan);
        this.frame.add(this.labelOldCep);
        this.frame.add(this.txtOldCep);
        this.frame.add(this.labelOldNumber);
        this.frame.add(this.txtOldNumber);

        this.frame.add(this.topicTchu);
        this.frame.add(this.labelNewName);
        this.frame.add(this.txtNewName);
        this.frame.add(this.labelNewCep);
        this.frame.add(this.txtNewCep);
        this.frame.add(this.labelNewNumber);
        this.frame.add(this.txtNewNumber);

        this.frame.add(this.status);
        this.frame.add(this.btnFinish);

        this.frame.setSize(420, 500);
        this.frame.setLayout(null);
        this.frame.setVisible(true);
        this.handleLocation();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.btnFinish){
            try{
                REPOSITORY_UPDATE.updateLibrary(this.txtOldCep.getText(),
                                                Integer.parseInt(this.txtOldNumber.getText()),
                                                REPOSITORY_FIND.getLibraryBy(this.txtNewCep.getText(),
                                                Integer.parseInt(this.txtNewNumber.getText())));
                this.status.setText("Repositório alterado com Sucesso.");
            }
            catch(Exception error){
                this.status.setText(error.getMessage());
            }
        }
    }
    
    private void handleLocation() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); 
        frame.setLocation(((d.width - frame.getWidth())/2), ((d.height - frame.getHeight())/3));
    }
}
