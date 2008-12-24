package br.ufma.deinf.laws.ncleclipse.layout.commands;

import org.eclipse.gef.commands.Command;

import br.ufma.deinf.laws.ncleclipse.layout.model.Node;
import br.ufma.deinf.laws.ncleditor.editor.contentassist.NCLSourceDocument;


public class DeleteCommand extends Command{
	private Node model;
	private Node parentModel;
	private NCLSourceDocument nclSourceDocument;
	
	public NCLSourceDocument getNclSourceDocument() {
		return nclSourceDocument;
	}

	public void setNclSourceDocument(NCLSourceDocument nclSourceDocument) {
		this.nclSourceDocument = nclSourceDocument;
	}

	public void execute(){		
		this.parentModel.removeChild(model);
		nclSourceDocument.removeElement(model.getId());
	}
	
	public void setModel(Object model){
		this.model = (Node) model;
	}
	
	public void setParentModel(Object model){
		this.parentModel = (Node)model;
	}
	
	public void undo(){
		this.parentModel.addChild(model);
	}
	public boolean canUndo(){
   	 return true;
    }
}
