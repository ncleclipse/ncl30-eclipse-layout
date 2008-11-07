package br.ufma.deinf.laws.ncleclipse.layout.editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import br.ufma.deinf.laws.ncleclipse.layout.commands.DeleteCommand;


public class AppDeletePolicy extends ComponentEditPolicy {
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		DeleteCommand command = new DeleteCommand();
		command.setModel(getHost().getModel());
		command.setParentModel(getHost().getParent().getModel());
		return command;
	}
}
