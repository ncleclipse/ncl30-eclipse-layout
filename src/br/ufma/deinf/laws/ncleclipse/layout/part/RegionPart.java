package br.ufma.deinf.laws.ncleclipse.layout.part;
import java.awt.geom.RectangularShape;
import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import br.ufma.deinf.laws.ncleclipse.layout.editpolicies.AppDeletePolicy;
import br.ufma.deinf.laws.ncleclipse.layout.editpolicies.AppEditLayoutPolicy;
import br.ufma.deinf.laws.ncleclipse.layout.figure.RegionFigure;
import br.ufma.deinf.laws.ncleclipse.layout.model.Node;
import br.ufma.deinf.laws.ncleclipse.layout.model.Region;

public class RegionPart extends AppAbstractEditPart {
     @Override
     protected IFigure createFigure() {
           IFigure figure = new RegionFigure();
           return figure;
     }
     protected void createEditPolicies() {
         installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
         //Faltando resolver o problema do Layout quando coloca uma regões em cima da outra
         installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
    }

     protected void refreshVisuals(){
           RegionFigure figure = (RegionFigure)getFigure();
           Region model = (Region)getModel();
           figure.setId(model.getId());
           figure.setLayout(model.getLayout());
     }
     public List<Node> getModelChildren() {
          return ((Region)getModel()).getChildrenArray();
     }
	
     @Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
    	 if (evt.getPropertyName().equals(Node.PROPERTY_LAYOUT)) refreshVisuals();
    	 if (evt.getPropertyName().equals(Node.PROPERTY_ADD)) refreshChildren();
    	 if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) refreshChildren();
	}
}
