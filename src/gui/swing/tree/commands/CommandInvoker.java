package gui.swing.tree.commands;

import view.MainFrame;

import java.util.ArrayList;

public class CommandInvoker {
  private ArrayList<AbstractCommand> commands = new ArrayList<>();
  private int currentCommand = 0;

  public void addCommand(AbstractCommand command) {
    while (currentCommand < commands.size()) {
      commands.remove(currentCommand);
    }
    commands.add(command);
    doCommand();
  }

  public void doCommand() {
    if (currentCommand < commands.size()) {
      commands.get(currentCommand++).doCommand();
      MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
    }
    if (currentCommand == commands.size()) {
      MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
    }
  }

  public void undoCommand() {
    if (currentCommand > 0) {
      commands.get(--currentCommand).undoCommand();
      MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
    }
    if (currentCommand == 0) {
      MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
    }
  }
}
