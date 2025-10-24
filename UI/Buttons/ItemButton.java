package UI.Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import SaveSystem.saveReader;
import SaveSystem.saveWriter;

public abstract class ItemButton extends JButton{
    
    public abstract void apply();

    Integer[] itemStatus;

    public boolean isActive;

    public ItemButton() {
        itemStatus = saveReader.readItems();
        this.isActive = false;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apply();
            }
        });    
    }

    public void update(boolean x) {
        saveWriter.saveItems();
    }

    public Integer isActive() {
        if (this.isActive) {
            return 1;
        } else {
            return 0;
        }
    }

}
