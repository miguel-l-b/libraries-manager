package main.gui;

import javax.swing.*;
import java.awt.event.*;

public class LaunchPage implements ActionListener {
    JFrame frame = new JFrame();

    JLabel title = new JLabel("LaunchPage");
    JLabel subtitle = new JLabel("Escolha o que deseja fazer:");

    JButton btnCreate = new JButton("Create");
    JButton btnDelete = new JButton("Delete");
    JButton btnUpdate = new JButton("Update");
    JButton btnSelect = new JButton("Select");
    
    public LaunchPage() {

        
        this.handleLocation();
    }
    @Override 
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.btnCreate) {
            
        }
    }

    private void handleLocation() {

    }
}
