package br.ufma.deinf.laws.ncleclipse.layout.part;
import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import br.ufma.deinf.laws.ncleclipse.layout.editpolicies.AppEditLayoutPolicy;
import br.ufma.deinf.laws.ncleclipse.layout.figure.RegionBaseFigure;
import br.ufma.deinf.laws.ncleclipse.layout.model.Node;
import br.ufma.deinf.laws.ncleclipse.layout.model.RegionBase;

public class RegionBasePart extends AppAbstractEditPart{
     @Override
     protected IFigure createFigure() {
           IFigure figure = new RegionBaseFigure();
           return figure;
     }
     protected void createEditPolicies() {
         installEditPolicy(EditPolicy.NODE_ROLE, new AppEditLayoutPolicy());
    }

     protected void refreshVisuals(){
          RegionBaseFigure figure = (RegionBaseFigure)getFigure();
          RegionBase model = (RegionBase)getModel();
          figure.setName(model.getId());
     }
     public List<Node> getModelChildren() {
         return ((RegionBase)getModel()).getChildrenArray();
     }
 	
     public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
    	 if (evt.getPropertyName().equals(Node.PROPERTY_LAYOUT)) refreshVisuals();
    	 if (evt.getPropertyName().equals(Node.PROPERTY_ADD)) refreshChildren();
    	 if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) refreshChildren();
	}
     
}
