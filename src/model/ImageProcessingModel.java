package model;

import filters.BlurFilter;
import filters.Replicator;
import filters.SharpenFilter;
import filters.colortransformation.GreyscaleTransformation;
import filters.colortransformation.SepiaTransformation;
import filters.colortransformation.greyscale.BlueGreyscale;
import filters.colortransformation.greyscale.GreenGreyscale;
import filters.colortransformation.greyscale.LumaGreyscale;
import filters.colortransformation.greyscale.RedGreyscale;
import filters.colortransformation.IntensityChange;
import filters.colortransformation.ValueChange;
import filters.flippingtransformation.FlipHorizontal;
import filters.flippingtransformation.FlipVertical;
import filters.intensitytransformation.BrightenTransformation;
import filters.intensitytransformation.DarkenTransformation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.pixel.IPixel;
import util.ImageUtil;

/**
 * Class representing a model for an ImageProcessor.
 */
public class ImageProcessingModel implements IImageProcessingModel {

  private ImageOfPixel image;
  private ImageOfPixel maskedImage;

  /**
   * Constructor for an image processing model that uses a given image to set its image field.
   *
   * @param image An image which contains a 2d array of pixels.
   * @throws IllegalArgumentException if the image is null
   */
  public ImageProcessingModel(ImageOfPixel image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    this.image = image;
  }

  public ImageProcessingModel(ImageOfPixel image, ImageOfPixel maskedImage) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    this.image = image;
    this.maskedImage = maskedImage;
  }

  public ImageOfPixel returnMaskedImage() throws IllegalArgumentException {

    if (this.image == null) {
      throw new IllegalArgumentException("No Masked image");
    }
    return new Replicator().returnImage(this.image);
  }

  /**
   * Brightens a given image.
   *
   * @param val value that the image will be brightened by.
   * @return the brightened image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public ImageOfPixel brighten(int val) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new BrightenTransformation().applyTransformation(this.image, val);
  }

  @Override
  public ImageOfPixel brighten(int val, ImageOfPixel maskedImage) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    if (maskedImage == null) {
      throw new IllegalArgumentException("no Masked image");
    }
    return new BrightenTransformation().applyTransformation(this.image, val, maskedImage);
  }

  /**
   * Darkens a given image.
   *
   * @param val value that the image will be darkened by.
   * @return the darkened image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public ImageOfPixel darken(int val) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    if (maskedImage == null) {
      return new DarkenTransformation().applyTransformation(this.image, val);
    }
    return new DarkenTransformation().applyTransformation(this.image, val, maskedImage);
  }

  @Override
  public ImageOfPixel darken(int val, ImageOfPixel maskedImage) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    if (maskedImage == null) {
      throw new IllegalArgumentException("no Masked image");
    }
    return new DarkenTransformation().applyTransformation(this.image, val, maskedImage);
  }

  /**
   * Greyscale an image based on the red component.
   *
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public ImageOfPixel redComponent() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new RedGreyscale().applyColorTransformation(this.image);
  }

  @Override
  public ImageOfPixel redComponent(ImageOfPixel maskedImage) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    if (maskedImage == null) {
      throw new IllegalArgumentException("Mask cannot be null");
    }
    return new RedGreyscale().applyColorTransformation(this.image, maskedImage);
  }


  /**
   * Greyscale an image based on the green component.
   *
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public ImageOfPixel greenComponent() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new GreenGreyscale().applyColorTransformation(this.image);
  }

  @Override
  public ImageOfPixel greenComponent(ImageOfPixel maskedImage) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    if (maskedImage == null) {
      throw new IllegalArgumentException("Mask cannot be null");
    }
    return new GreenGreyscale().applyColorTransformation(this.image, maskedImage);
  }

  /**
   * Greyscale an image based on the blue component.
   *
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public ImageOfPixel blueComponent() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new BlueGreyscale().applyColorTransformation(this.image);
  }

  @Override
  public ImageOfPixel blueComponent(ImageOfPixel maskedImage) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    if (maskedImage == null) {
      throw new IllegalArgumentException("Mask cannot be null");
    }
    return new BlueGreyscale().applyColorTransformation(this.image, maskedImage);
  }

  /**
   * Alters an image's intensity.
   *
   * @return The intensified image.
   * @throws IllegalArgumentException if image is null
   */
  @Override
  public ImageOfPixel intensity() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new IntensityChange().applyColorTransformation(this.image);
  }

  @Override
  public ImageOfPixel intensity(ImageOfPixel maskedImage) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    if (maskedImage == null) {
      throw new IllegalArgumentException("Mask cannot be null");
    }
    return new IntensityChange().applyColorTransformation(this.image, maskedImage);
  }

  /**
   * Alters an image's pixels' value.
   *
   * @return The image with changed values.
   * @throws IllegalArgumentException if image is null
   */
  @Override
  public ImageOfPixel valueImage() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new ValueChange().applyColorTransformation(this.image);
  }

  @Override
  public ImageOfPixel valueImage(ImageOfPixel maskedImage) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    if (maskedImage == null) {
      throw new IllegalArgumentException("Mask cannot be null");
    }
    return new ValueChange().applyColorTransformation(this.image, maskedImage);
  }

  /**
   * Greyscale an image based on the luma of the components.
   *
   * @return the greyscale image.
   * @throws IllegalArgumentException if the image is null
   */
  @Override
  public ImageOfPixel luma() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new LumaGreyscale().applyColorTransformation(this.image);
  }

  @Override
  public ImageOfPixel luma(ImageOfPixel maskedImage) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    if (maskedImage == null) {
      throw new IllegalArgumentException("Mask cannot be null");
    }
    return new LumaGreyscale().applyColorTransformation(this.image, maskedImage);
  }


  /**
   * Flips the image horizontally.
   *
   * @return The horizontally flipped image.
   * @throws IllegalArgumentException if the image is null.
   */
  @Override
  public ImageOfPixel horizontalFlip() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }

    return new FlipHorizontal().flipTransform(this.image);
  }

  /**
   * Flips the image vertically.
   *
   * @return The vertically flipped image.
   * @throws IllegalArgumentException if the image is null.
   */
  @Override
  public ImageOfPixel verticalFlip() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }

    return new FlipVertical().flipTransform(this.image);
  }

  /**
   * Filters the image by blurring the image.
   *
   * @return The blurred image.
   */
  @Override
  public ImageOfPixel blur() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }

    return new BlurFilter().transform(this.image);

  }


  public ImageOfPixel blur(ImageOfPixel maskedImage) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    if (maskedImage == null) {
      throw new IllegalArgumentException("no Masked image");
    }
    return new BlurFilter().maskTransform(this.image, maskedImage);
  }

  /**
   * Filters the image by sharpening the image.
   *
   * @return The sharpened image.
   */
  @Override
  public ImageOfPixel sharpen() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new SharpenFilter().maskTransform(this.image, this.maskedImage);
  }

  @Override
  public ImageOfPixel sharpen(ImageOfPixel maskedImage) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    if (maskedImage == null) {
      throw new IllegalArgumentException("no Masked image");
    }
    return new SharpenFilter().maskTransform(this.image, maskedImage);
  }


  /**
   * Transforms the image into a sepia colored image.
   *
   * @return The transformed image.
   */
  @Override
  public ImageOfPixel sepia() throws IllegalArgumentException {
    return new SepiaTransformation().transform(this.image);
  }

  @Override
  public ImageOfPixel sepia(ImageOfPixel maskedImage) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    if(maskedImage == null) {
      throw new IllegalArgumentException("Mask cannot be null");
    }
    return new SepiaTransformation().transform(this.image, maskedImage);
  }

  /**
   * Greyscale an image by using a matrix for component conversion.
   *
   * @return the greyscale image.
   */
  @Override
  public ImageOfPixel greyscale() throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    return new GreyscaleTransformation().transform(this.image);
  }

  @Override
  public ImageOfPixel greyscale(ImageOfPixel maskedImage) throws IllegalArgumentException{
    if (image == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    if (maskedImage == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    return new GreyscaleTransformation().transform(this.image, maskedImage);
  }

  /**
   * Saves a file of the given filename as a PPM file.
   *
   * @param filename the file name of the image
   * @throws IOException if the filename is invalid
   */
  @Override
  public void saveImageAsPPM(String filename) throws IOException {
    this.image.saveImageAsPPM(filename);
  }

  /**
   * Saves a file of the given filename as the type specified in the outputName file.
   *
   * @param outputName the file name of the image
   * @throws IOException if the filename is invalid
   */
  public void saveImageAs(String outputName) throws IOException {
    this.image.saveImageAs(outputName);
  }


}
