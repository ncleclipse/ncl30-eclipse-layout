package br.ufma.deinf.laws.ncleclipse.layout.figure;

/*
 * Created on 2005/07/28
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