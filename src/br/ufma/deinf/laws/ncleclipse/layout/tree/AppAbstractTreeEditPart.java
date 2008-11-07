package br.ufma.deinf.laws.ncleclipse.layout.tree;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import br.ufma.deinf.laws.ncleclipse.layout.model.Node;


public abstract class AppAbstractTreeEditPart extends AbstractTreeEditPart
		implements PropertyChangeListener {
	public void activate() {
		super.activate();
		((Node) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
		((Node) getModel()).removePropertyChangeListener(this);
		super.deactivate();
	}
}
