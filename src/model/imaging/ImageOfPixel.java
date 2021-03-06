package model.imaging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.graph.Line;
import model.imaging.pixel.IPixel;

/**
 * Interface representing an image made up of pixels.
 */
public interface ImageOfPixel {

  /**
   * Gets the 2D list of pixels of this pixel image.
   *
   * @return
   */
  List<List<IPixel>> getPixels();

  /**
   * Gets the 2D list of pixels of this pixel image as arraylsit.
   *
   * @return
   */
  List<ArrayList<IPixel>> getPixelsArraylist();

  /**
   * Saves an image as a PPM file given the name of the file to save it as.
   *
   * @param filename The name of the ppm file to create.
   * @throws IOException Thrown if the file output stream does not function correctly.
   */
  void saveImageAsPPM(String filename) throws IOException;

  /**
   * Saves an image as a common file type based on the extension of the output name. If the
   * extension is not a valid file type nothing happens.
   *
   * @param outputName The name of the file including its extension (example.jpg).
   * @throws IOException Thrown if the file output stream does not funciton correctly.
   */
  void saveImageAs(String outputName) throws IOException;

  /**
   * Returns a list of lines for the image.
   *
   * @return A list of lines used to draw on the GUI.
   */
  public List<Line> getLines();
}