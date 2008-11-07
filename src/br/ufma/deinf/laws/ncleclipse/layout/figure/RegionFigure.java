package br.ufma.deinf.laws.ncleclipse.layout.figure;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
public class RegionFigure extends AlphaLayerFigure{
	
	  public static final int SERVICE_FIGURE_DEFWIDTH = 250;
	  public static final int SERVICE_FIGURE_DEFHEIGHT = 150;


     private Label labelId = new Label();
     
     public RegionFigure() {
    	 	super(new Color(null,
                    (new Double(Math.random()   * 128)).intValue() + 128 ,
                    (new Double(Math.random()   * 128)).intValue() + 128 ,
                    (new Double(Math.random()   * 128)).intValue() + 128 ),
                    128);
           XYLayout layout = new XYLayout();
           setLayoutManager(layout);
           labelId.setForegroundColor(ColorConstants.darkGray);
           add(labelId, ToolbarLayout.ALIGN_CENTER);
           setConstraint(labelId, new Rectangle(3, 3, -1, -1));
           setForegroundColor(new Color(null,
                       (new Double(Math.random()   * 128)).intValue() ,
                       (new Double(Math.random()   * 128)).intValue(),
                       (new Double(Math.random()   * 128)).intValue()));
           /*setBackgroundColor(new Color(null,
                       (new Double(Math.random()   * 128)).intValue() + 128 ,
                       (new Double(Math.random()   * 128)).intValue() + 128 ,
                       (new Double(Math.random()   * 128)).intValue() + 128 ));*/
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
