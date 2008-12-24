package br.ufma.deinf.laws.ncleclipse.layout.editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import br.ufma.deinf.laws.ncleclipse.layout.commands.DeleteCommand;
import br.ufma.deinf.laws.ncleditor.editor.contentassist.NCLSourceDocument;


public class AppDeletePolicy extends ComponentEditPolicy {
	//NCLSourceDocument nclSourceDocument;
	
	//public AppDeletePolicy(NCLSourceDocument nclSourceDocument){
		//super();
		//this.nclSourceDocument = nclSourceDocument;
	//}
	
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		DeleteCommand command = new DeleteCommand();
		command.setModel(getHost().getModel());
		command.setParentModel(getHost().getParent().getModel());
		//command.setNclSourceDocument(nclSourceDocument);
		return command;
	}
}
