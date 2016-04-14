package giti7083s.angel.command.command;

import giti7083s.angel.command.receiver.Receiver;

/**
 * Created by angel on 10/04/16.
 */
public interface Command {
        void execute();
        void undo();
        void redo();
        void setReceiver(Receiver receiver);
}
