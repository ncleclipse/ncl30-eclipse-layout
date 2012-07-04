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
package br.ufma.deinf.laws.ncleclipse.layout.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * 
 * @author Roberto Azevedo <roberto@laws.deinf.ufma.br>
 *
 */
public class RegionBaseFigure extends Figure {
	private Label labelName = new Label();
	private Label labelAddress = new Label();
	private Label labelCapital = new Label();
	private XYLayout layout;

	public RegionBaseFigure() {
		layout = new XYLayout();
		setLayoutManager(layout);
		labelName.setForegroundColor(ColorConstants.blue);
		add(labelName);
		setConstraint(labelName, new Rectangle(getBounds().height - 3,
				getBounds().width - 3, -1, -1));
		labelAddress.setForegroundColor(ColorConstants.lightBlue);
		add(labelAddress);
		setConstraint(labelAddress, new Rectangle(5, 17, -1, -1));
		labelCapital.setForegroundColor(ColorConstants.lightBlue);
		add(labelCapital);
		setConstraint(labelCapital, new Rectangle(5, 30, -1, -1));
		setForegroundColor(ColorConstants.black);
		setBorder(new LineBorder(5));
	}

	public void setLayout(Rectangle rect) {
		setBounds(rect);
	}

	public void setName(String text) {
		labelName.setText(text);
	}

	public void setAddress(String text) {
		labelAddress.setText(text);
	}

	public void setCapital(int capital) {
		labelCapital.setText("Capital : " + capital);
	}
}
