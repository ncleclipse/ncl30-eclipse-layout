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
package br.ufma.deinf.laws.ncleclipse.layout.figure;

/*
 * Author aki@www.xucker.jpn.org
 * License Apache2.0 or Common Public License
 */
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;

/**
 * 
 * @author Roberto Azevedo <roberto@laws.deinf.ufma.br>
 *
 */
public class AlphaLayerFigure extends Figure {

	private Color color;
	protected Image layerImage;
	private ImageData imageData;

	public AlphaLayerFigure(Color color, int alpha) {
		super();
		this.color = color;

		PaletteData palette = new PaletteData(new RGB[] { color.getRGB() });
		imageData = new ImageData(1, 1, 8, palette);
		imageData.alpha = alpha;
		imageData.setPixel(0, 0, 0);
		layerImage = new Image(null, imageData);

	}

	public void setAlpha(int alpha) {
		imageData.alpha = alpha;
		if (layerImage != null && !layerImage.isDisposed()) {
			layerImage.dispose();
		}
		layerImage = new Image(null, imageData);
		repaint();
	}

	public int getAlpha() {
		return imageData.alpha;
	}

	public void setColor(Color color) {
		this.color = color;
		imageData.palette.colors[0] = color.getRGB();
		if (layerImage != null && !layerImage.isDisposed()) {
			layerImage.dispose();
		}
		layerImage = new Image(null, imageData);
		repaint();
	}

	public Color getColor() {
		return color;
	}

	public void paintFigure(Graphics g) {

		Rectangle rectangle = getClientArea();
		g.drawImage(layerImage, new Rectangle(layerImage.getBounds()),
				rectangle);
	}

}