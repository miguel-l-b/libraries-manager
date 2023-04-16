package main.gui.Windows;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.use_cases.GetLibrary;
import app.use_cases.UpdateLibraryByCepAndNumber;
import core.entities.Library;
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
    
    private JLabel labelNewEmail = new JLabel("Novo Email:");
    private JLabel labelNewComplement = new JLabel("Novo Complemento:");
    
    private JTextField txtNewName = new JTextField();
    private JTextField txtNewCep = new JTextField();
    private JTextField txtNewNumber = new JTextField();
    private JTextField txtNewEmail = new JTextField();
    private JTextField txtNewComplement = new JTextField();

    private int confirmDialog;
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
        this.txtOldCep.setBounds(200,120,160,25);

        this.labelOldNumber.setBounds(30,150,160,25);
        this.labelOldNumber.setFont(new Font("Serif", Font.PLAIN, 18));
		this.txtOldNumber.setBounds(200,150,160,25);

        this.topicTchu.setBounds(30,200,350,30);
        this.topicTchu.setFont(new Font("Serif", Font.BOLD, 18));

        this.labelNewName.setBounds(30,240,160,25);
        this.labelNewName.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtNewName.setBounds(200,240,160,25);

        this.labelNewCep.setBounds(30,270,160,25);
        this.labelNewCep.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtNewCep.setBounds(200,270,160,25);

        this.labelNewNumber.setBounds(30,300,160,25);
        this.labelNewNumber.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtNewNumber.setBounds(200,300,160,25);

        this.labelNewEmail.setBounds(30,330,160,25);
        this.labelNewEmail.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtNewEmail.setBounds(200,330,160,25);

        this.labelNewComplement.setBounds(30,360,160,25);
        this.labelNewComplement.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtNewComplement.setBounds(200,360,160,25);

        this.status.setBounds(30,390,400,30);
        this.status.setFont(new Font("Serif", Font.PLAIN, 18));

        this.btnFinish.setBounds(300,450,100,40);
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

        this.frame.add(this.labelNewEmail);
        this.frame.add(this.txtNewEmail);

        this.frame.add(this.labelNewComplement);
        this.frame.add(this.txtNewComplement);

        this.frame.add(this.status);
        this.frame.add(this.btnFinish);

        this.frame.setSize(440, 550);
        this.frame.setLayout(null);
        this.frame.setVisible(true);
        this.handleLocation();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.btnFinish){
            try{
                if (this.txtOldCep.getText().isEmpty() || this.txtOldNumber.getText().isEmpty())
                    this.status.setText("Ambos os campos CEP e Número devem ser preenchidos!");
                else {
    
                    Library library = REPOSITORY_FIND.getLibraryBy(this.txtOldCep.getText(), Integer.parseInt(this.txtOldNumber.getText()));
    
                    if (this.txtNewCep.getText().isEmpty())
                        this.txtNewCep.setText(library.getCep());
    
                    if (this.txtNewName.getText().isEmpty())
                        this.txtNewName.setText(library.getName());
    
                    if (this.txtNewNumber.getText().isEmpty())
                        this.txtNewNumber.setText(String.valueOf(library.getNumber()));
    
                    if (this.txtNewEmail.getText().isEmpty())
                        this.txtNewEmail.setText(library.getEmail());
    
                    if (this.txtNewComplement.getText().isEmpty())
                        this.txtNewComplement.setText(library.getComplement());
    
                    confirmDialog = JOptionPane.showOptionDialog(frame, "" + this.txtNewName.getText() +
                                                                 ", " + this.txtNewEmail.getText() + 
                                                                 ", " + this.txtNewComplement.getText() + 
                                                                 ", " + this.txtNewCep.getText() + 
                                                                 ", " + Integer.parseInt(this.txtNewNumber.getText()), 
                                                                 "Deseja mesmo alterar?", 
                                                                 JOptionPane.OK_CANCEL_OPTION, 
                                                                 JOptionPane.QUESTION_MESSAGE, 
                                                                 null, 
                                                                 null, 
                                                                 null);
                    if (confirmDialog == JOptionPane.CANCEL_OPTION)
                        return;
                    else {
                        REPOSITORY_UPDATE.updateLibrary(this.txtOldCep.getText(),
                                                      Integer.parseInt(this.txtOldNumber.getText()),
                                                      new Library(this.txtNewName.getText(),
                                                                  this.txtNewEmail.getText(),
                                                                  this.txtNewComplement.getText(),
                                                                  this.txtNewCep.getText(),
                                                                  Integer.parseInt(this.txtNewNumber.getText())));
                        this.status.setText("Repositório alterado com Sucesso.");
                    }
                }
    
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
