package br.ufma.deinf.laws.ncleclipse.layout.commands;
import org.eclipse.draw2d.geometry.Rectangle;

import br.ufma.deinf.laws.ncleclipse.layout.model.Region;

public class RegionChangeLayoutCommand extends AbstractLayoutCommand {
       private Region model;
       private Rectangle layout;
       private Rectangle oldLayout;
       
       public void execute() {
            model.setLayout(layout);
       }
       public void setConstraint(Rectangle rect) {
            this.layout = rect;
       }
       public void setModel(Object model) {
            this.model = (Region)model;
            this.oldLayout = ((Region)model).getLayout();
       }
       public void undo(){
    	   this.model.setLayout(oldLayout);
       }
}
