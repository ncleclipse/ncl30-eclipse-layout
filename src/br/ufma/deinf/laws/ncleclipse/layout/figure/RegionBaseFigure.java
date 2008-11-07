package br.ufma.deinf.laws.ncleclipse.layout.figure;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
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
         setConstraint(labelName, new Rectangle(getBounds().height-3, getBounds().width-3, -1, -1));
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
         labelCapital.setText("Capital : "+capital);
    }
  }
