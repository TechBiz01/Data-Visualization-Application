package com.company;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PieChart {
    private JTextField item;
    private JTextField amount;
    private JButton ADDDATAButton;
    private JPanel mainFrame;
    private JPanel panel2;
    private JButton PIECHARTButton;
    private JPanel piePanel;
    private JButton RESETButton;
    private DefaultPieDataset pieDataset;
    private JFreeChart pieChart;
    private ChartPanel chartPanel;
    private DefaultTableModel model;
    private JTable table;
    private JFrame frame;

    public PieChart() {
        // Initialize frame and main panel
        frame = new JFrame("Pie Chart Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        mainFrame = new JPanel(new BorderLayout());  // Main layout
        
        // Initialize item input fields and buttons
        item = new JTextField(10);
        amount = new JTextField(5);
        ADDDATAButton = new JButton("Add Data");
        PIECHARTButton = new JButton("Show Pie Chart");
        RESETButton = new JButton("Reset");
        
        // Initialize panels
        panel2 = new JPanel();
        piePanel = new JPanel(new BorderLayout());
        
        // Create top control panel
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Item:"));
        controlPanel.add(item);
        controlPanel.add(new JLabel("Amount:"));
        controlPanel.add(amount);
        controlPanel.add(ADDDATAButton);
        controlPanel.add(PIECHARTButton);
        controlPanel.add(RESETButton);
        
        // Add controlPanel and piePanel to the main frame
        mainFrame.add(controlPanel, BorderLayout.NORTH);
        mainFrame.add(panel2, BorderLayout.CENTER);
        mainFrame.add(piePanel, BorderLayout.SOUTH);
        
        frame.setContentPane(mainFrame);
        frame.setVisible(true);
        
        // Set up table
        displayTable();
        
        // Action listeners
        ADDDATAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = item.getText();
                String amountData = amount.getText();
                Object[] data = {itemName, amountData};
                model.addRow(data);
                item.setText("");
                amount.setText("");
            }
        });

        PIECHARTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piePanel.removeAll();
                showPie();
                frame.validate();
            }
        });

        RESETButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piePanel.removeAll();
                panel2.removeAll();
                displayTable();
                frame.validate();
            }
        });
    }

    public void displayTable() {
        String[] columns = {"ITEMS", "AMOUNT"};
        model = new DefaultTableModel(null, columns);
        table = new JTable(model);
        panel2.setLayout(new BorderLayout());
        panel2.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void showPie() {
        pieDataset = new DefaultPieDataset();
        for (int i = 0; i < table.getRowCount(); i++) {
            String name = table.getValueAt(i, 0).toString();
            Double amt = Double.valueOf(table.getValueAt(i, 1).toString());
            pieDataset.setValue(name, amt);
        }
        pieChart = ChartFactory.createPieChart("PIE CHART", pieDataset, true, true, true);
    chartPanel = new ChartPanel(pieChart);
    piePanel.setLayout(new BorderLayout());  // Ensure BorderLayout for piePanel
    piePanel.add(chartPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new PieChart();
    }
}
