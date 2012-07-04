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
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * 
 * @author Roberto Azevedo <roberto@laws.deinf.ufma.br>
 * 
 */
public class RegionFigure extends AlphaLayerFigure {

	public static final int SERVICE_FIGURE_DEFWIDTH = 250;
	public static final int SERVICE_FIGURE_DEFHEIGHT = 150;

	private Label labelId = new Label();

	public RegionFigure() {
		super(new Color(null,
				(new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128), 128);
		XYLayout layout = new XYLayout();
		setLayoutManager(layout);
		labelId.setForegroundColor(ColorConstants.darkGray);
		add(labelId, ToolbarLayout.ALIGN_CENTER);
		setConstraint(labelId, new Rectangle(3, 3, -1, -1));
		setForegroundColor(new Color(null, (new Double(Math.random() * 128))
				.intValue(), (new Double(Math.random() * 128)).intValue(),
				(new Double(Math.random() * 128)).intValue()));
		/*
		 * setBackgroundColor(new Color(null, (new Double(Math.random()
		 * 128)).intValue() + 128 , (new Double(Math.random() 128)).intValue() +
		 * 128 , (new Double(Math.random() 128)).intValue() + 128 ));
		 */
		setBorder(new LineBorder(1));
		setOpaque(true);
	}

	public void setId(String text) {
		labelId.setText(text);
	}

	public void setLayout(Rectangle rect) {
		getParent().setConstraint(this, rect);
	}
}
