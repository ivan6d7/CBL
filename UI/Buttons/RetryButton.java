package UI.Buttons;

import java.awt.event.*;
import javax.swing.*;
import main.game;

public class RetryButton extends JButton {

    String text = "Retry";

    public RetryButton() {
        this.setText(text);
        this.setSize(500, 100);

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.render();
            }
        });
    }

}