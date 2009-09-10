/*******************************************************************************
 * This file is part of the authoring environment in Nested Context Language -
 * NCL Eclipse.
 * 
 * Copyright: 2007-2009 UFMA/LAWS (Laboratory of Advanced Web Systems), All Rights Reserved.
 * 
 * This program is free software; you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU General Public License version 2 for more 
 * details.
 * 
 * You should have received a copy of the GNU General Public License version 2
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 * For further information contact:
 * 		ncleclipse@laws.deinf.ufma.br
 * 		http://www.laws.deinf.ufma.br/ncleclipse
 * 		http://www.laws.deinf.ufma.br
 ********************************************************************************/
package br.ufma.deinf.laws.ncleclipse.layout.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import br.ufma.deinf.laws.ncleclipse.layout.model.Node;
import br.ufma.deinf.laws.ncleclipse.layout.model.Region;

/**
 * 
 * @author Roberto Azevedo <roberto@laws.deinf.ufma.br>
 * 
 */
public class RegionCreateCommand extends Command {
	private Node rgBase;
	private Region rg;

	public RegionCreateCommand() {
		super();
		rgBase = null;
		rg = null;
	}

	public void setRegion(Object s) {
		if (s instanceof Region)
			this.rg = (Region) s;
	}

	public void setRegionBase(Object e) {
		if (e instanceof Node)
			this.rgBase = (Node) e;
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

	public void execute() {
		rgBase.addChild(rg);
	}

	public boolean canUndo() {
		if (rgBase == null || rg == null)
			return false;
		return true;
	}

	public void undo() {
		rgBase.removeChild(rg);
	}

}