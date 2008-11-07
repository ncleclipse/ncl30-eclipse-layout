package br.ufma.deinf.laws.ncleclipse.layout.model;

import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.internal.Workbench;

public class NodeCreationFactory implements CreationFactory
{
     private Class<?> template;
     public NodeCreationFactory(Class<?> t) {
          this.template = t;
     }
     @Override
     public Object getNewObject() {
          if (template == null)
                return null;
          if (template == Region.class)
          {
        	  final Region rg = new Region();
              
              //Dialog to get Id of the region
              //change this code to other place
              InputDialog dialog = new InputDialog(Workbench.getInstance().getActiveWorkbenchWindow().getShell(), "Region Id", "id", "regionid", null);
              if(dialog.open() == Window.OK) {
                rg.setId(dialog.getValue());
                //será que o identificador já existe???
              }else{
                return null;
              }
              return rg;
         }
         return null;
    }
    @Override
    public Object getObjectType() {
         return template;
    }
  }

