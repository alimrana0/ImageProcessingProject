package view.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import model.graph.Line;
import model.graph.Position2D;
import model.imaging.pixel.IPixel;

public class GraphPanel extends JPanel {

  private List<Line> lines;
  //the rectangle within which all lines lie
  private Position2D minD, maxD;


  public GraphPanel() {
    super();
    lines = new ArrayList<Line>();
    this.setBackground(Color.WHITE);
    minD = new Position2D(0, 0);
    maxD = new Position2D(0, 0);

  }




  public void setLines(List<Line> lines) {
    this.lines = new ArrayList<Line>(lines);

    List<Position2D> points = new ArrayList<Position2D>();
    for (Line l : this.lines) {
      points.add(new Position2D(l.start));
      points.add(new Position2D(l.end));
    }
    if (points.size() > 0) {

      minD = points.get(0);
      maxD = points.get(1);

      for (Position2D p : points) {
        if (p.getX() < minD.getX()) {
          minD = new Position2D(p.getX(), minD.getY());
        }
        if (p.getY() > minD.getY()) {
          minD = new Position2D(minD.getX(), p.getY());
        }
      }

      for (Position2D p : points) {
        if (p.getX() > maxD.getX()) {
          maxD = new Position2D(p.getX(), maxD.getY());
        }
        if (p.getY() > maxD.getY()) {
          maxD = new Position2D(maxD.getX(), p.getY());
        }
      }
    }
  }


  /**
   * Override the paintComponent method of the JPanel Do NOT override paint!
   *
   * @param g
   */
  @Override
  protected void paintComponent(Graphics g) {
    //never forget to call super.paintComponent!
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;


    /*
    the origin of the panel is top left. In order
    to make the origin bottom left, we must "flip" the
    y coordinates so that y = height - y

    We do that by using an affine transform. The flip
    can be specified as scaling y by -1 and then
    translating by height.
     */

    AffineTransform originalTransform = g2d.getTransform();

    //the order of transforms is bottom-to-top
    //so as a result of the two lines below,
    //each y will first be scaled, and then translated
    g2d.translate(0, this.getPreferredSize().getHeight());
    g2d.scale(1, -1);

    List colors = new ArrayList();
    colors.add(Color.RED);
    colors.add(Color.GREEN);
    colors.add(Color.BLUE);
    colors.add(Color.BLACK);
    int color = 0;
    for (Line l : lines) {
      switch (color) {
        case 0:
          g2d.setColor((Color) colors.get(color));
          color++;
          break;
        case 1:
          g2d.setColor((Color) colors.get(color));
          color++;
          break;
        case 2:
          g2d.setColor((Color) colors.get(color));
          color++;
          break;
        case 3:
          g2d.setColor((Color) colors.get(color));
          color = 0;
          break;
      }

      Position2D start = l.start;
      Position2D end = l.end;
      g2d.drawLine((int) start.getX(), (int) start.getY(),
          (int) end.getX(), (int) end.getY());
    }
  }
}
