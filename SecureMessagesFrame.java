/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.tut.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author Student
 */
public class SecureMessagesFrame extends JFrame {
    
    private JPanel headerPnl;
    private JPanel scrosllPnl;
    ;
    private JPanel mainPnl;
    
    private JLabel headerLbl;
    
    private JTextArea plainTxtArea;
    private JTextArea encryptTxtArea;
    
    private JScrollPane plainScrollPn;
    private JScrollPane encryptScrollPn;

    //menu
    private JMenuBar mBar;
    private JMenu fileMenu;

    //Menu Items
    private JMenuItem openFileMenuItem;
    private JMenuItem encryptMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem clearMenuItem;
    private JMenuItem exitMenuItem;
    
    public SecureMessagesFrame() {
        setTitle("Secure Message");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 310);
        
        setVisible(true);

        //Panels
        headerPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        scrosllPnl = new JPanel(new GridLayout(1, 2, 1, 1));
        mainPnl = new JPanel(new BorderLayout());

        //Label
        headerLbl = new JLabel("Message Encryptor");
        headerLbl.setFont(new Font(Font.SERIF, Font.ITALIC + Font.BOLD, 30));
        headerLbl.setForeground(Color.blue);
        headerLbl.setBorder(new BevelBorder(BevelBorder.RAISED));

        //Text Area
        plainTxtArea = new JTextArea(15, 30);
        encryptTxtArea = new JTextArea(15, 30);

        //Scroll pane
        plainScrollPn = new JScrollPane(plainTxtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        encryptScrollPn = new JScrollPane(encryptTxtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        plainScrollPn.setBorder(new TitledBorder(new LineBorder(Color.black, 1), "Plain message"));
        encryptScrollPn.setBorder(new TitledBorder(new LineBorder(Color.black, 1), "Encrypted message"));

        //menu
        mBar = new JMenuBar();
        fileMenu = new JMenu("File");

        //Menu Items
        openFileMenuItem = new JMenuItem("Open File...");
        openFileMenuItem.addActionListener(new OpenFileMenuItemListener());
        
        encryptMenuItem = new JMenuItem("Encrypt message...");
        saveMenuItem = new JMenuItem("Save encrypted message...");
        clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(new ClearMenuItemListener());
        
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ExitMenuItemListener());
        
        headerPnl.add(headerLbl);
        
        scrosllPnl.add(plainScrollPn);
        scrosllPnl.add(encryptScrollPn);
        
        mainPnl.add(scrosllPnl, BorderLayout.CENTER);
        
        fileMenu.add(openFileMenuItem);
        fileMenu.add(encryptMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(clearMenuItem);
        fileMenu.add(exitMenuItem);
        
        mBar.add(fileMenu);
        
        setJMenuBar(mBar);
        add(headerPnl, BorderLayout.NORTH);
        add(mainPnl, BorderLayout.CENTER);
        
        setVisible(true);
        pack();
    }
    
    private class ExitMenuItemListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(NORMAL);
        }
        
    }
    
    private class ClearMenuItemListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            plainTxtArea.setText("");
            encryptTxtArea.setText("");
        }
        
    }
    
    private class OpenFileMenuItemListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            BufferedReader br;
            String data, record = "";
            JFileChooser fc;
            int val;
            File file;
            
            fc = new JFileChooser();
            
            val = fc.showOpenDialog(SecureMessagesFrame.this);
            
            if (val == JFileChooser.APPROVE_OPTION) {
                try {
                    file = fc.getSelectedFile();
                    
                    br = new BufferedReader(new FileReader(file));
                    
                    while ((data = br.readLine()) != null) {
                        record += record + data + "\n";
                    }
                    
                    br.close();
                    
                    plainTxtArea.setText(record);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SecureMessagesFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SecureMessagesFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    private class EncryptMenuItem implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
        
    }
    
}
