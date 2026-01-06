package my_package;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

class RoundButton extends JButton {
    public RoundButton(String label) {
        super(label);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFont(new Font("Arial", Font.BOLD, 18));
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isArmed()) g2.setColor(Color.LIGHT_GRAY);
        else g2.setColor(getBackground());
        g2.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        super.paintComponent(g);
    }
}