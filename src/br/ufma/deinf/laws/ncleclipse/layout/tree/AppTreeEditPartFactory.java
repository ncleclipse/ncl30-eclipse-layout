package br.ufma.deinf.laws.ncleclipse.layout.tree;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import br.ufma.deinf.laws.ncleclipse.layout.model.Region;
import br.ufma.deinf.laws.ncleclipse.layout.model.RegionBase;


public class AppTreeEditPartFactory implements EditPartFactory {
    @Override
    public EditPart createEditPart(EditPart context, Object model) {
       EditPart part = null;
       if (model instanceof RegionBase)
         part = new RegionBaseTreeEditPart();
       else if (model instanceof Region)
               part = new RegionTreeEditPart();
       if (part != null)
         part.setModel(model);
       return part;
     }
}

