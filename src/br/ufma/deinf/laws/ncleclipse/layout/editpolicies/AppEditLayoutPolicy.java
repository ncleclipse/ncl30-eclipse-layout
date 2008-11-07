package br.ufma.deinf.laws.ncleclipse.layout.editpolicies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import br.ufma.deinf.laws.ncleclipse.layout.commands.AbstractLayoutCommand;
import br.ufma.deinf.laws.ncleclipse.layout.commands.RegionChangeLayoutCommand;
import br.ufma.deinf.laws.ncleclipse.layout.commands.RegionCreateCommand;
import br.ufma.deinf.laws.ncleclipse.layout.figure.RegionFigure;
import br.ufma.deinf.laws.ncleclipse.layout.part.AppAbstractEditPart;
import br.ufma.deinf.laws.ncleclipse.layout.part.RegionPart;


public class AppEditLayoutPolicy extends XYLayoutEditPolicy {
	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		AbstractLayoutCommand command = null;
		if (child instanceof RegionPart) {
			command = new RegionChangeLayoutCommand();
		}
		command.setModel(child.getModel());
		command.setConstraint((Rectangle) constraint);
		return command;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		// TODO Auto-generated method stub
		if (request.getType() == REQ_CREATE && (getHost() instanceof AppAbstractEditPart)) {
			RegionCreateCommand cmd = new RegionCreateCommand();
			cmd.setRegionBase(getHost().getModel());
			cmd.setRegion(request.getNewObject());
			Rectangle constraint = (Rectangle) getConstraintFor(request);
			constraint.x = (constraint.x < 0) ? 0 : constraint.x;
			constraint.y = (constraint.y < 0) ? 0 : constraint.y;
			constraint.width = (constraint.width <= 0) ? RegionFigure.SERVICE_FIGURE_DEFWIDTH
					: constraint.width;
			constraint.height = (constraint.height <= 0) ? RegionFigure.SERVICE_FIGURE_DEFHEIGHT
					: constraint.height;
			cmd.setLayout(constraint);
			return cmd;
		}
		return null;
	}
}
