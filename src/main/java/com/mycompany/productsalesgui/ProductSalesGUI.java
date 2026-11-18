package com.mycompany.productsalesgui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

// question 2.1, 2.2, 2.3
public class ProductSalesGUI extends JFrame implements ActionListener {
    
    private JTextArea txtDisplay;
    private JLabel lblYears;
    private JButton btnLoad, btnSave;
    private JMenuItem mntmExit, mntmLoad, mntmSave, mntmClear;
    private ProductSales sales;

    public ProductSalesGUI() {
        sales = new ProductSales();
        
        setTitle("Product Sales Application");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        createMenuBar();
        createMainForm();

        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(this);
        fileMenu.add(mntmExit);
        menuBar.add(fileMenu);

        // Tools Menu
        JMenu toolsMenu = new JMenu("Tools");
        mntmLoad = new JMenuItem("Load Product Data");
        mntmSave = new JMenuItem("Save Product Data");
        mntmClear = new JMenuItem("Clear");
        mntmLoad.addActionListener(this);
        mntmSave.addActionListener(this);
        mntmClear.addActionListener(this);
        toolsMenu.add(mntmLoad);
        toolsMenu.add(mntmSave);
        toolsMenu.add(mntmClear);
        menuBar.add(toolsMenu);

        setJMenuBar(menuBar);
    }

    private void createMainForm() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Buttons Panel
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnLoad = new JButton("Load Product Data");
        btnSave = new JButton("Save Product Data");
        btnLoad.addActionListener(this);
        btnSave.addActionListener(this);
        pnlButtons.add(btnLoad);
        pnlButtons.add(btnSave);
        mainPanel.add(pnlButtons);

        mainPanel.add(Box.createVerticalStrut(10));

        // Text Area
        txtDisplay = new JTextArea(8, 30);
        txtDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtDisplay);
        mainPanel.add(scrollPane);

        mainPanel.add(Box.createVerticalStrut(10));

        // Years Label
        lblYears = new JLabel("Years Processed: 0");
        JPanel pnlLabel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlLabel.add(lblYears);
        mainPanel.add(pnlLabel);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void loadData() {
        String report = "Total Sales: " + sales.GetTotalSales() + "\n"
                + "Average Sales: " + String.format("%.0f", sales.GetAverageSales()) + "\n"
                + "Sales over limit: " + sales.GetSalesOverLimit() + "\n"
                + "Sales under limit: " + sales.GetSalesUnderLimit();
        
        txtDisplay.setText(report);
        lblYears.setText("Years Processed: " + sales.GetProductsProcessed());
    }

    private void saveData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt"))) {
            writer.println("DATA LOG");
            writer.println("****************************************");
            writer.println("Total Sales: " + sales.GetTotalSales());
            writer.println("Average Sales: " + String.format("%.0f", sales.GetAverageSales()));
            writer.println("Sales over limit: " + sales.GetSalesOverLimit());
            writer.println("Sales under limit: " + sales.GetSalesUnderLimit());
            writer.println("****************************************");
            JOptionPane.showMessageDialog(this, "Data saved to data.txt!");
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearAll() {
        txtDisplay.setText("");
        lblYears.setText("Years Processed: 0");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == btnLoad || source == mntmLoad) {
            loadData();
        }
        else if (source == btnSave || source == mntmSave) {
            saveData();
        }
        else if (source == mntmClear) {
            clearAll();
        }
        else if (source == mntmExit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProductSalesGUI::new);
    }
}