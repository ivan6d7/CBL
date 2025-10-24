package UI.Buttons;

import java.awt.event.*;
import javax.swing.*;

import SaveSystem.saveWriter;
import main.game;

public class RetryButton extends JButton {

    String text = "Retry";

    public RetryButton() {
        this.setText(text);
        this.setSize(500, 100);
        

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveWriter.saveItems(false);
                game.levelNumber = 1;

                game.frame.removeAll();
                game.frame.revalidate();
                game.frame.dispose();

                game.main(null);

                game.playerPanel.updateData();

            }
        });
    }

}