package UI.Buttons;

import java.awt.event.*;
import javax.swing.*;
import main.game;

public class NextLevelButton extends JButton{

    String text = "Next Level";

    public NextLevelButton() {
        this.setText(text);
        this.setSize(500, 100);
        

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.levelNumber += 1;
                game.render();
            }
        });
    }
}
