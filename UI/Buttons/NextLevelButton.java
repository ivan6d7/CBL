package UI.Buttons;

import java.awt.event.*;
import javax.swing.*;
import main.game;

/**
 * Button used to advance to the next level.
 *
 * When clicked, this button increments the global level counter and asks
 * the main game to render the next level. Implementations should ensure any
 * required save operations or cleanup are performed before calling game.render().
 */
public class NextLevelButton extends JButton{


    String text = "Next Level";

    /**
     * Constructor.
     *
     * The constructor sets the button label, preferred size and installs an
     * ActionListener that increases game.levelNumber by one and calls
     * game.render() to load the next level when the button is pressed.
     */
    public NextLevelButton() {
        this.setText(text);
        this.setSize(500, 100);
        
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // advance saved/current level and rebuild UI for the new level
                game.levelNumber += 1;
                game.frame.removeAll();
                game.frame.revalidate();
                game.frame.dispose();

                game.main(null);

                game.playerPanel.updateData();
            }
        });
    }
}
