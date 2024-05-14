import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

class obadaaa extends JFrame implements ActionListener, KeyListener {
    private JButton exit = null;
    private JLabel octalLabel, hexadecimalLabel, decimalLabel;
    private JTextField binaryInput;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == binaryInput) {
            updateConversion();
        }
    }

    private void updateConversion() {
        String binaryString = binaryInput.getText();
        if (isValidBinary(binaryString)) {
            int decimalValue = Integer.parseInt(binaryString, 2);
            decimalLabel.setText("Decimal: " + decimalValue);
            octalLabel.setText("Octal: " + Integer.toOctalString(decimalValue));
            hexadecimalLabel.setText("Hexadecimal: " + Integer.toHexString(decimalValue).toUpperCase());
        } else {
            clearLabels();
        }
    }

    private boolean isValidBinary(String binaryString) {
        return binaryString.matches("[01]+");
    }

    private void clearLabels() {
        decimalLabel.setText("Decimal: ");
        octalLabel.setText("Octal: ");
        hexadecimalLabel.setText("Hexadecimal: ");
    }

    private void init() {
        octalLabel = new JLabel("Octal: ");
        hexadecimalLabel = new JLabel("Hexadecimal: ");
        decimalLabel = new JLabel("Decimal: ");
        binaryInput = new JTextField(15);
        exit = new JButton("Exit");

        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);
        northPanel.setBorder(BorderFactory.createTitledBorder("Enter a binary number (0 or 1):"));
        northPanel.add(binaryInput);

        JPanel centerPanel = new JPanel();
        add(centerPanel, BorderLayout.CENTER);
        centerPanel.setBorder(BorderFactory.createTitledBorder("Converted Results"));
        centerPanel.add(decimalLabel);
        centerPanel.add(octalLabel);
        centerPanel.add(hexadecimalLabel);

        JPanel southPanel = new JPanel();
        add(southPanel, BorderLayout.SOUTH);
        southPanel.add(exit);
        exit.setToolTipText("Press me to exit");

        exit.addActionListener(this);
        binaryInput.addKeyListener(this);

        centerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        northPanel.setBorder(BorderFactory.createLineBorder(Color.green));
        southPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public obadaaa() {
        super("Binary Converter");
        init();

        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new obadaaa();
    }
}
