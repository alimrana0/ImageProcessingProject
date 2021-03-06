package filters.colortransformation.greyscale;

import java.util.List;

import filters.colortransformation.AbstractColorTransformation;
import model.imaging.Color;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

/**
 * Class to represent a pixel being greyscale in terms of its red component.
 */
public class RedGreyscale extends AbstractColorTransformation {

  /**
   * Empty constructor for a RedGreyscale filter.
   */
  public RedGreyscale() {
    //Doesn't need any initializations.
  }

  /**
   * Applies the color transformation to the given pixel by updating its rgb values. Any out of
   * range rgb value is clamped to the minimum value of 0 or the maximum value of 255.
   *
   * @param pixel Pixel being transformed.
   * @return The transformed pixel.
   */
  protected IPixel colorTransform(IPixel pixel) {


    int red = pixel.getColor().getRed();

    int changedGreen = red;
    int changedBlue = red;

    return new Pixel(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()), new Color(red,
            changedGreen, changedBlue));

  }

  /**
   * Applies the color transformation to the given pixel by updating its rgb values selected by
   * the mask image. Any out of range rgb value is clamped to the minimum value of 0 or the maximum
   * value of 255.
   *
   * @param pixel Pixel being transformed.
   * @param maskedPixelPosns the list of positions of the black pixels in the mask
   * @return the pixel, transformed only if it is in the position of the mask's black pixel
   */
  protected IPixel colorTransform(IPixel pixel, List<Posn> maskedPixelPosns) {
    if (maskedPixelPosns.contains(pixel.getPosn())) {
      return colorTransform(pixel);
    }
    return pixel;
  }
}
