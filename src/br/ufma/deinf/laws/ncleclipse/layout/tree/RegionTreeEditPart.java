package br.ufma.deinf.laws.ncleclipse.layout.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import br.ufma.deinf.laws.ncleclipse.layout.editpolicies.AppDeletePolicy;
import br.ufma.deinf.laws.ncleclipse.layout.model.Node;
import br.ufma.deinf.laws.ncleclipse.layout.model.Region;


public class RegionTreeEditPart extends AppAbstractTreeEditPart {
	protected List<Node> getModelChildren() {
		return ((Region) getModel()).getChildrenArray();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
	}

	public void refreshVisuals(){
         Region model = (Region)getModel();
         setWidgetText(model.getId());
         setWidgetImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT));
    }

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD))
			refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE))
			refreshChildren();
	}
}
