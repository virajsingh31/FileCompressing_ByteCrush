package ui;

import compression.FileCompressor;
import compression.FileDecompressor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class MainFrame extends JFrame {
    private JButton compressButton, decompressButton;
    private JProgressBar progressBar;

    public MainFrame() {
        setTitle("File Compressor/Decompressor");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Apply modern UI theme
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception ignored) {}

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        compressButton = new JButton("📂 Compress File");
        decompressButton = new JButton("📂 Decompress File");

        compressButton.setFont(new Font("Arial", Font.BOLD, 16));
        decompressButton.setFont(new Font("Arial", Font.BOLD, 16));

        compressButton.setBackground(new Color(72, 133, 237));
        decompressButton.setBackground(new Color(219, 68, 55));

        compressButton.setForeground(Color.WHITE);
        decompressButton.setForeground(Color.WHITE);

        compressButton.setFocusPainted(false);
        decompressButton.setFocusPainted(false);

        compressButton.addActionListener(this::compressFile);
        decompressButton.addActionListener(this::decompressFile);

        panel.add(compressButton);
        panel.add(decompressButton);
        add(panel, BorderLayout.CENTER);

        // Progress bar
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        add(progressBar, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void compressFile(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                progressBar.setIndeterminate(true);
                FileCompressor.compressFile(selectedFile.getAbsolutePath());
                progressBar.setIndeterminate(false);
                JOptionPane.showMessageDialog(this, "Compression Successful!");
            } catch (Exception ex) {
                progressBar.setIndeterminate(false);
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void decompressFile(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                progressBar.setIndeterminate(true);
                FileDecompressor.decompressFile(selectedFile.getAbsolutePath());
                progressBar.setIndeterminate(false);
                JOptionPane.showMessageDialog(this, "Decompression Successful!");
            } catch (Exception ex) {
                progressBar.setIndeterminate(false);
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
