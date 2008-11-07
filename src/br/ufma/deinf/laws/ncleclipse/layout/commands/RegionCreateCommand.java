package br.ufma.deinf.laws.ncleclipse.layout.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import br.ufma.deinf.laws.ncleclipse.layout.model.Node;
import br.ufma.deinf.laws.ncleclipse.layout.model.Region;


public class RegionCreateCommand extends Command
{
     private Node rgBase;
     private Region rg;
     public RegionCreateCommand() {
           super();
           rgBase = null;
           rg = null;
     }
     public void setRegion(Object s) {
           if (s instanceof Region)
                  this.rg = (Region)s;
     }
     public void setRegionBase(Object e) {
           if (e instanceof Node)
                  this.rgBase = (Node)e;
     }
     public void setLayout(Rectangle r) {
           if (rg == null)
                  return;
           rg.setLayout(r);
     }
     @Override
     public boolean canExecute() {
           if (rg == null || rgBase == null)
                  return false;
           return true;
     }
     
     public void execute(){
    	 rgBase.addChild(rg);
     }
     
     public boolean canUndo(){
    	 if(rgBase == null || rg == null)
    		 return false;
    	 return true;
     }
     
     public void undo(){
    	 rgBase.removeChild(rg);
     }

}