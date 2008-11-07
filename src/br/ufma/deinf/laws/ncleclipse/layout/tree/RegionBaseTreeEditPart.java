package br.ufma.deinf.laws.ncleclipse.layout.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import br.ufma.deinf.laws.ncleclipse.layout.model.Node;
import br.ufma.deinf.laws.ncleclipse.layout.model.RegionBase;


public class RegionBaseTreeEditPart extends AppAbstractTreeEditPart {
    protected List<Node> getModelChildren() {
         return ((RegionBase)getModel()).getChildrenArray();
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
         if(evt.getPropertyName().equals(Node.PROPERTY_ADD)) refreshChildren();
         if(evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) refreshChildren();
    }
}
