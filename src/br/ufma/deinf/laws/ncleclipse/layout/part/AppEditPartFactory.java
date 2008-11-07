package br.ufma.deinf.laws.ncleclipse.layout.part;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import br.ufma.deinf.laws.ncleclipse.layout.model.Region;
import br.ufma.deinf.laws.ncleclipse.layout.model.RegionBase;

public class AppEditPartFactory implements EditPartFactory {
     @Override
     public EditPart createEditPart(EditPart context, Object model) {
    	 AbstractGraphicalEditPart part = null;
    	 if (model instanceof RegionBase) {
    	       part = new RegionBasePart();
    	 } else if (model instanceof Region) {
    	       part = new RegionPart();
    	 }
    	 part.setModel(model);
    	 return part;
     }
}
