package giti7083s.angel.command.invoker;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import giti7083s.angel.command.command.Command;

/**
 * Created by angel on 10/04/16.
 */
public class Invoker {
    private Deque<Command> queueUndo = new LinkedList<>();
    private Deque<Command> queueRedo = new LinkedList<>();
    //for display
    ArrayList<String> historyList = new ArrayList<>();


    private InvokerListener invokerListener;

    public Invoker() {
        invokerListener = new InvokerListener() {
            @Override
            public void commandAdded() {

            }

            @Override
            public void commandRemoved() {

            }
        };
    }


    public void addCommand(Command command) {
        queueUndo.addFirst(command);
        command.execute();

        //add to history text
        historyList.add(0, command.toString());

        //raise callback
        invokerListener.commandAdded();
    }


    public void undo() {
        if (queueUndo.size() > 0) {
            Command lastCommand = queueUndo.pollFirst();
            lastCommand.undo();

            //add to redo list
            queueRedo.addFirst(lastCommand);

            //remove history
            historyList.remove(0);

            //raise callback
            invokerListener.commandRemoved();
        }
    }


    public void redo() {
        if (queueRedo.size() > 0) {
            Command command = queueRedo.pollFirst();
            command.redo();

            //add back to undo list
            queueUndo.addFirst(command);

            //re-add to history
            historyList.add(0, command.toString());

            //raise callback
            invokerListener.commandAdded();
        }
    }

    public void setInvokerListener(InvokerListener invokerListener) {
        this.invokerListener = invokerListener;
    }

    public ArrayList<String> getHistoryList() {
        return historyList;
    }

    public interface InvokerListener {
        void commandAdded();

        void commandRemoved();
    }
}
