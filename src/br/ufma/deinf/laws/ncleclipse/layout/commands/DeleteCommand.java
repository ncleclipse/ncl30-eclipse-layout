package br.ufma.deinf.laws.ncleclipse.layout.commands;

import org.eclipse.gef.commands.Command;

import br.ufma.deinf.laws.ncleclipse.layout.model.Node;


public class DeleteCommand extends Command{
	private Node model;
	private Node parentModel;
	
	public void execute(){		
		this.parentModel.removeChild(model);
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
}
