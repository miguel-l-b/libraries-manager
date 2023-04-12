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
import javax.swing.text.MaskFormatter;

import main.gui.LaunchPage;

public class CreatePage implements ActionListener{
    private LaunchPage launchPage = new LaunchPage();
    private JFrame frame = new JFrame();

    private JLabel titleCreate = new JLabel("Create Page");
    private JLabel subtitleCreate = new JLabel("Preencha os campos abaixo: ");
    
    private JTextField txtName = new JTextField();
    private JTextField txtCEP = new JTextField();
    private JTextField txtNumber = new JTextField();

    private JLabel statusCreate = new JLabel();
    private JLabel labelName = new JLabel("Name: ");
    private JLabel labelCep = new JLabel ("CEP: ");
    private JLabel labelNumber = new JLabel("Number: ");

    private JButton btnCreate = new JButton("Create");

    public CreatePage(){
        this.titleCreate.setBounds(30, 5, 350, 30);
        this.titleCreate.setFont(new Font("Serif", Font.BOLD, 22));

        this.subtitleCreate.setBounds(30, 30, 350, 30);
        this.subtitleCreate.setFont(new Font("TimesNewRoman",Font.PLAIN, 18));

        this.labelName.setBounds(10,40,120,25);
        this.labelName.setFont(new Font("Serif", Font.BOLD,12));


        this.statusCreate.setBounds(170,160,300,50);
        this.statusCreate.setFont(new Font("Serif", Font.PLAIN, 12));

        this.handleLocation();
    }
    private void handleLocation() { 
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); 
        frame.setLocation(((d.width - frame.getWidth())/2), ((d.height - frame.getHeight())/3)); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    
}
