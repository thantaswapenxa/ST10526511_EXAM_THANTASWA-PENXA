package com.mycompany.productappgui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class ProductAppGUI extends JFrame {
    
       
    private JComboBox<String> cmbMovies;
    private JTextField txtNumTickets;
    private JTextField txtTicketPrice;
    private JTextArea txtReport;

   
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu toolsMenu;
    private JMenuItem exitItem;
    private JMenuItem processItem;
    private JMenuItem clearItem;

    public ProductAppGUI() {
        // Frame Setup
        setTitle("Product App");
        setSize(400, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // menu bar
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);

        toolsMenu = new JMenu("Tools");
        processItem = new JMenuItem("Process");
        clearItem = new JMenuItem("Clear");
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        // question 2.1
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));

        //question 2.2

        JLabel lblCustomerName = new JLabel("Customer Name:");
        txtNumTickets = new JTextField();
        
        JLabel lblProducts = new JLabel("Select Product:");
        String[] products = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        cmbMovies = new JComboBox<>(products);

        JLabel lblTicketPrice = new JLabel("Enter Price:");
        txtTicketPrice = new JTextField();

        JLabel lblReport = new JLabel("Message:");

        formPanel.add(lblProducts);
        formPanel.add(cmbMovies);
        formPanel.add(lblCustomerName);
        formPanel.add(txtNumTickets);
        formPanel.add(lblTicketPrice);
        formPanel.add(txtTicketPrice);
        formPanel.add(lblReport);
        formPanel.add(new JLabel("")); // Empty placeholder for alignment

        // 3. Setup
        txtReport = new JTextArea(6, 30);
        txtReport.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtReport);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));

        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        
        // Q.2.4: Exit application
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Q.2.4: Process and clear button
        processItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processTicketReport();
            }
        });

        // Clear all fields
        clearItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmbMovies.setSelectedIndex(0);
                txtNumTickets.setText("");
                txtTicketPrice.setText("");
                txtReport.setText("");
            }
        });
    }

    private void processTicketReport() {
        try {
            String productName = (String) cmbMovies.getSelectedItem();
            int numTickets = Integer.parseInt(txtNumTickets.getText().trim());
            double ticketPrice = Double.parseDouble(txtTicketPrice.getText().trim());

            // Calculate Total with 14% VAT (Q.2.4)
            double subtotal = numTickets * ticketPrice;
            double totalWithVat = subtotal + (subtotal * 0.14);

            // Format report matching the Sample Screenshot
            String reportOutput = "PRODUCT NAME: " + productName + "\n"
                                + "MOVIE TICKET PRICE: R " + ticketPrice + "\n"
                                + "NUMBER OF TICKETS: " + numTickets + "\n"
                                + "TOTAL TICKET PRICE: R " + totalWithVat;

            txtReport.setText(reportOutput);

            // Save to report.txt (Q.2.5)
            saveReportToFile(reportOutput);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Please enter valid numeric values for Enter Price and a Customer Name.", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveReportToFile(String reportContent) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("report.txt"))) {
            writer.println("DATA LOG");
            writer.println("************************************");
            writer.println(reportContent);
            writer.println("************************************");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error saving report to file: " + ex.getMessage(), 
                "File Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProductAppGUI().setVisible(true);
        });
    }
}   