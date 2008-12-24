package br.ufma.deinf.laws.ncleclipse.layout;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.jface.action.IToolBarManager;


/**
 * Esta classe vai ter que mudar para outro lugar quando for juntar com o ncl30-eclipse
 * @author roberto
 *
 */

public class NCLLayoutEditorActionBarContributor extends ActionBarContributor{

	@Override
	protected void buildActions() {
		// TODO Auto-generated method stub
		addRetargetAction(new UndoRetargetAction());
		addRetargetAction(new RedoRetargetAction());
		addRetargetAction(new DeleteRetargetAction());
	}

	@Override
	protected void declareGlobalActionKeys() {
		// TODO Auto-generated method stub
		
	}
	
	public void contributeToToolBar(IToolBarManager toolBarManager){
		/*toolBarManager.add(getAction(ActionFactory.UNDO.getId()));
		toolBarManager.add(getAction(ActionFactory.REDO.getId()));
		toolBarManager.add(getAction(ActionFactory.DELETE.getId()));*/
	}

}
