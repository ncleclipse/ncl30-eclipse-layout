/*******************************************************************************
 * This file is part of the NCL authoring environment - NCL Eclipse.
 *
 * Copyright (C) 2007-2012, LAWS/UFMA.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License version 2 for
 * more details. You should have received a copy of the GNU General Public 
 * License version 2 along with this program; if not, write to the Free 
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 
 * 02110-1301, USA.
 *
 * For further information contact:
 * - ncleclipse@laws.deinf.ufma.br
 * - http://www.laws.deinf.ufma.br/ncleclipse
 * - http://www.laws.deinf.ufma.br
 *
 ******************************************************************************/
package br.ufma.deinf.laws.ncleclipse.layout.tree;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import br.ufma.deinf.laws.ncleclipse.layout.model.Node;

/**
 * 
 * @author Roberto Azevedo <roberto@laws.deinf.ufma.br>
 * 
 */
public abstract class AppAbstractTreeEditPart extends AbstractTreeEditPart
		implements PropertyChangeListener {
	public void activate() {
		super.activate();
		((Node) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
		((Node) getModel()).removePropertyChangeListener(this);
		super.deactivate();
	}
}
