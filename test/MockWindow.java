import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.IViewListener;
import view.ImageProcessingGUIView;

/**
 * A fake window that is used to "Fire" button commands to the controller to ensure the controller
 * is receiving commands correctly.
 */
public class MockWindow extends ImageProcessingGUIView {

  IViewListener listener;

  /**
   * Constructs the initial instance of the GUI.
   *
   * @param listener Listener for this GUI
   */
  public MockWindow(IViewListener listener) {
    super(listener);
    this.listener = listener;
  }

  @Override
  public void run() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  @Override
  public void renderMessage(String msg) throws IllegalArgumentException {
    JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void actionPerformed(ActionEvent a) {
    switch (a.getActionCommand()) {
      case "Save":
        emitSaveEvent();
        break;
      case "Blur":
        emitBlurLayerEvent();
        break;
      case "Sharpen":
        emitSharpenLayerEvent();
        break;
      case "Grayscale":
        emitGrayscaleLayerEvent();
        break;
      case "Sepia":
        emitSepiaLayerEvent();
        break;
      case "Red Component":
        emitRedComponentLayerEvent();
        break;
      case "Green Component":
        emitGreenComponentLayerEvent();
        break;
      case "Blue Component":
        emitBlueComponentLayerEvent();
        break;
      case "Value Component":
        emitValueComponentLayerEvent();
        break;
      case "Intensity Component":
        emitIntensityComponentLayerEvent();
        break;
      case "Darken":
        emitDarkenEvent(5);
        break;
      case "Brighten":
        emitBrightenEvent(3);
        break;
      case "Vertical Flip":
        emitVerticalFlipEvent();
        break;
      case "Horizontal Flip":
        emitHorizontalFlipEvent();
        break;
      case "Add Image":
        emitLoadImageEvent();
        break;
      case "Delete Image":
        emitDeleteLayerEvent();
        break;
      case "Show Image":
        emitShowLayerEvent();
        break;
      case "Hide Image":
        emitHideLayerEvent();
        break;
      case "Save All":
        emitSaveAllEvent();
        break;
      case "Load Multi":
        emitLoadAllEvent();
        break;
      default:
        break;
    }
  }

  /**
   * Emits a load all event and tells the listener to load in the multi layer image at the selected
   * file path.
   */
  private void emitLoadAllEvent() {
    listener.loadAllHandler("f.getAbsolutePath()");

  }

  /**
   * Tells the listener to save the top most visible layer image with the given file type at the
   * selected file path.
   */
  private void emitSaveEvent() {
    listener.saveLayerHandler("f.getAbsolutePath()",
        "optionsFileType[filetypeValue]");

  }

  /**
   * Tells the listener to save the whole image with the specified type from the user and the
   * selected path from the user.
   */
  private void emitSaveAllEvent() {
    listener.saveAllHandler("name", "type");
  }

  /**
   * Tells the listener to blur the current layer in the image.
   */
  private void emitBlurLayerEvent() {
    listener.blurHandler();
  }

  /**
   * Tells the listener to sharpen the current layer in the image.
   */
  private void emitSharpenLayerEvent() {
    listener.sharpenHandler();
  }

  /**
   * Tells the listener to grayscale the current layer in the image.
   */
  private void emitGrayscaleLayerEvent() {
    listener.grayscaleHandler();
  }

  /**
   * Tells the listener to sepia the current layer in the image.
   */
  private void emitSepiaLayerEvent() {
    listener.sepiaHandler();
  }

  /**
   * Tells the listener to apply the red component to the current image.
   */
  private void emitRedComponentLayerEvent() {
    listener.redComponentHandler();
  }

  /**
   * Tells the listener to apply the green component to the current layer in the image.
   */
  private void emitGreenComponentLayerEvent() {
    listener.greenComponentHandler();
  }

  /**
   * Tells the listener to apply the blue component to the current layer in the image.
   */
  private void emitBlueComponentLayerEvent() {
    listener.blueComponentHandler();
  }


  /**
   * Tells the listener to apply the value component to the current layer in the image.
   */
  private void emitValueComponentLayerEvent() {
    listener.valueHandler();
  }

  /**
   * Tells the listener to apply the intensity component to the current layer in the image.
   */
  private void emitIntensityComponentLayerEvent() {
    listener.intensityHandler();
  }

  /**
   * Tells the listener to apply a darken transformation with the given val to the current layer in
   * the image.
   */
  private void emitDarkenEvent(int val) {
    listener.darkenHandler(val);
  }

  /**
   * Tells the listener to apply a brighten transformation with the given val to the current layer
   * in the image.
   */
  private void emitBrightenEvent(int val) {
    listener.brightenHandler(val);
  }


  /**
   * Tells the listener to apply a vertical flip to the current layer in the image.
   */
  private void emitVerticalFlipEvent() {
    listener.handleFlipVertical();
  }

  /**
   * Tells the listener to apply a horizontal flip to the current layer in the image.
   */
  private void emitHorizontalFlipEvent() {
    listener.handleFlipHorizontal();
  }


  /**
   * Tells the listener to remove the current layer. It then updates the list of layers in the GUI
   * with the new list of layers.
   */
  private void emitDeleteLayerEvent() {
    listener.removeLayer();
  }

  /**
   * Tells the layer to set the layer with the given id from the user as the current layer. Also
   * sets the board of the layer in the list to red if it has been selected as the current.
   */
  private void emitSelectLayerEvent() {
    String layerName = JOptionPane.showInputDialog("Layer Name");
    listener.selectLayer(layerName);
  }

  /**
   * Tells the listener to set the current layer to visible.
   */
  private void emitShowLayerEvent() {
    listener.show();
  }

  /**
   * Tells the listener to make the current layer invisible.
   */
  private void emitHideLayerEvent() {
    listener.hide();
  }

  /**
   * Tells the listener to load in the image with the user selected file type and at the selected
   * file path as a layer in the program. The name of the layer is also taken from the user.
   */
  private void emitLoadImageEvent() {
    listener.loadLayerHandler("name", "type", "name");

  }


}
