package br.ufma.deinf.laws.ncleclipse.layout.part;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutListener;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;

import br.ufma.deinf.laws.ncleclipse.layout.editpolicies.AppEditLayoutPolicy;
import br.ufma.deinf.laws.ncleclipse.layout.figure.RegionBaseFigure;
import br.ufma.deinf.laws.ncleclipse.layout.model.Node;
import br.ufma.deinf.laws.ncleclipse.layout.model.RegionBase;

public class RegionBasePart extends AppAbstractEditPart {
	
	LayoutListener layoutListener = new LayoutListener(){
		IFigure prev = null;
		@Override
		public void invalidate(IFigure container) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean layout(IFigure container) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void postLayout(IFigure container) {
			// TODO Auto-generated method stub
			if(prev != null){
				if(!prev.getSize().equals(container.getSize())){
					System.out.println("#### size changed!");
					System.out.println("prev = " + prev.getSize().height +" " + prev.getSize().width);
					System.out.println("atual = " + container.getSize().height +" " + container.getSize().width);
				}
			}
			prev = new RegionBaseFigure();
			prev.setBounds(new Rectangle(container.getBounds()));
		}

		@Override
		public void remove(IFigure child) {
			// TODO Auto-generated method stub

		}

		@Override
		public void setConstraint(IFigure child, Object constraint) {
			// TODO Auto-generated method stub

		}
	};
	
	@Override
	protected IFigure createFigure() {
		IFigure figure = new RegionBaseFigure();
		return figure;
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.NODE_ROLE, new AppEditLayoutPolicy());
	}

	protected void refreshVisuals() {
		RegionBaseFigure figure = (RegionBaseFigure) getFigure();
		RegionBase model = (RegionBase) getModel();
		figure.setName(model.getId());
		figure.addLayoutListener(layoutListener);
	}

	public List<Node> getModelChildren() {
		return ((RegionBase) getModel()).getChildrenArray();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getPropertyName().equals(Node.PROPERTY_LAYOUT))
			refreshVisuals();
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD))
			refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE))
			refreshChildren();
	}

}
