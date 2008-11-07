package br.ufma.deinf.laws.ncleclipse.layout.part;
import java.beans.PropertyChangeListener;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import br.ufma.deinf.laws.ncleclipse.layout.model.Node;
public abstract class AppAbstractEditPart extends AbstractGraphicalEditPart implements
PropertyChangeListener {
     public void activate() {
           super.activate();
           ((Node) getModel()).addPropertyChangeListener(this);
     }
     public void deactivate() {
           super.deactivate();
           ((Node) getModel()).removePropertyChangeListener(this);
     }
}
