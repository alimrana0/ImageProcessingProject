package filters;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import model.imaging.Color;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

public class AbstractImageProcessingMaskTransform {

  protected final IKernel kernel;

  /**
   * Constructs an AbstractImageProcessingMaskTransform using a given kernel.
   *
   * @param kernel kernel being applied to the image.
   * @throws IllegalArgumentException If the kernel is null or not 3x3.
   */
  protected AbstractImageProcessingMaskTransform(IKernel kernel) throws IllegalArgumentException {
    if (kernel == null) {
      throw new IllegalArgumentException("Argument can't be null.");
    }
    if (kernel.getHeight() % 2 == 0 || kernel.getWidth() % 2 == 0) {
      throw new IllegalArgumentException("Kernel dimensions must be odd");
    }
    this.kernel = kernel;
  }

  /**
   * Applies some transformation on the color of the given image based on the pixels in a masked
   * image.
   *
   * @param image Image being transformed.
   * @param maskedImage black and white version of the iop
   * @return The transformed image.
   * @throws IllegalArgumentException If an image is null.
   */
  public ImageOfPixel transform(ImageOfPixel image, ImageOfPixel maskedImage) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image can't be null.");
    }
    List<List<IPixel>> imagePixels = image.getPixels();
    List<Posn> maskedPixelsPosns = storeBlack(maskedImage.getPixels());

    return new Image(filtered(imagePixels, image, maskedPixelsPosns));
  }

  /**
   * Stores the black pixels of the mask image in a list.
   * @param maskedPixels the 2D list of pixels of the mask image
   * @return a list of Posns for the black pixels in the mask
   */
  protected List<Posn> storeBlack(List<List<IPixel>> maskedPixels)  {
    List<Posn> blackPixels = new ArrayList();
    for (int i = 0; i < maskedPixels.size(); i++) {
      for (int j = 0; j < maskedPixels.get(0).size(); j++) {
        if (maskedPixels.get(i).get(j).getColor().getRed() == 0
                && maskedPixels.get(i).get(j).getColor().getBlue() == 0
                && maskedPixels.get(i).get(j).getColor().getGreen() == 0) {
          blackPixels.add(maskedPixels.get(i).get(j).getPosn());
        } // Decided not to error handle a non-black-and-white image so that the black pixels of
        //any image can be transferred onto an image
//        else if ((maskedPixels.get(i).get(j).getColor().getRed() != 255)
//                || (maskedPixels.get(i).get(j).getColor().getGreen() != 255)
//        || (maskedPixels.get(i).get(j).getColor().getBlue() != 255)){
//          throw new IllegalArgumentException("Image is not black and white");
//        }
      }
    }
    return blackPixels;
  }

  /**
   * Returns the filtered 2D array of pixels of the image.
   *
   * @param pixels Pixels of the image being filtered.
   * @param image  Image being filtered.
   * @return The filtered 2D pixel array.
   */
  protected List<ArrayList<IPixel>> filtered(List<List<IPixel>> pixels,
                                             ImageOfPixel image, List<Posn> maskedPixelsPosns) {
    List<ArrayList<IPixel>> newPixels = new ArrayList<>();
    for (int i = 0; i < pixels.size(); i++) {
      ArrayList<IPixel> r = new ArrayList<>();
      for (int j = 0; j < pixels.get(0).size(); j++) {
          r.add(filter(pixels.get(i).get(j), image, maskedPixelsPosns));
      }
      newPixels.add(r);
    }

    return newPixels;

  }

  /**
   * Method to transform a pixel based on a kernel, only if the positions contained in the list of
   * masked pixel positions has a position corresponding to the given pixel
   * @param pixel the pixel being transformed
   * @param image the image of the pixel being transformed
   * @param maskedPixelPosns the list of pixel positions of the black pixels of the mask image
   * @return the transformed pixel.
   */
  protected IPixel filter(IPixel pixel, ImageOfPixel image, List<Posn> maskedPixelPosns) {
    if (maskedPixelPosns.contains(pixel.getPosn())) {
      List<List<IPixel>> imagePixels = image.getPixels();

      int peripherals = kernel.getHeight() / 2;

      int r = 0;
      int g = 0;
      int b = 0;

      for (int i = peripherals * -1; i <= peripherals; i++) {
        for (int j = peripherals * -1; j <= peripherals; j++) {
          try {
            double kVal = (double) Array
                    .get(Array.get(kernel.getValues(), i + peripherals), j + peripherals);

            int red = imagePixels.get(pixel.getPosn().getY() + i)
                    .get(pixel.getPosn().getX() + j).getColor().getRed();
            int green = imagePixels.get(pixel.getPosn().getY() + i)
                    .get(pixel.getPosn().getX() + j).getColor().getGreen();
            int blue = imagePixels.get(pixel.getPosn().getY() + i)
                    .get(pixel.getPosn().getX() + j).getColor().getBlue();

            red *= kVal;
            green *= kVal;
            blue *= kVal;

            r += red;
            g += green;
            b += blue;

          } catch (IndexOutOfBoundsException ignore) {
            r += 0;
            g += 0;
            b += 0;
          }
        }
      }

      r = FilterClamp.clamp(r);
      g = FilterClamp.clamp(g);
      b = FilterClamp.clamp(b);

      return new Pixel(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()), new Color(r, g, b));


    }
    return pixel;
  }



}
