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
package br.ufma.deinf.laws.ncleclipse.layout.model;

import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.internal.Workbench;

/**
 * 
 * @author Roberto Azevedo <roberto@laws.deinf.ufma.br>
 * 
 */
public class NodeCreationFactory implements CreationFactory {
	private Class<?> template;

	public NodeCreationFactory(Class<?> t) {
		this.template = t;
	}

	@Override
	public Object getNewObject() {
		if (template == null)
			return null;
		if (template == Region.class) {
			final Region rg = new Region();

			// Dialog to get Id of the region
			// change this code to other place
			InputDialog dialog = new InputDialog(Workbench.getInstance()
					.getActiveWorkbenchWindow().getShell(), "Region Id", "id",
					"regionid", null);
			if (dialog.open() == Window.OK) {
				rg.setId(dialog.getValue());
				// será que o identificador já existe???
			} else {
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
