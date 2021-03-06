# ImageProcessing
# Running the program
- The commands used to control the image processor are based off of the example given 
in the assignment with a slight variation on the get component command.
- First load the image and give it a name: load stars.ppm stars
  - Now you can manipulate the image stars, below example commands using stars are listed.
  - load (filename) (name of image to be used in program)
- get-component (component) (image name) (name of new saved image) 
  - get-component red stars stars-red
  - you can replace component in parentheses with any of the following
    - red
    - green
    - blue
    - luma
    - value
    - intensity
- brighten (integer) (image name) (name of new saved image)
  - brighten 10 stars stars-bright
- darken (integer) (image name) (name of new saved image)
  - darken 10 stars stars-dark
- vertical-flip (image name) (name of new saved image)
  - vertical-flip stars stars-vflipped
- horizontal-flip (image name) (name of new saved image)
  - horizontal-flip stars stars-hflipped
- save (file name) (image to be saved as new ppm file)
  - save stars-copy.ppm stars

- Or you can copy and paste the text below
  - Text to copy:
    load stars.ppm stars
    get-component red stars red-stars
    save redstars.ppm red-stars
    get-component green stars green-stars
    save greenstars.ppm green-stars
    get-component blue stars blue-stars
    save bluestars.ppm blue-stars
    get-component luma stars lumastars
    save lumastars.ppm lumastars
    get-component value stars valuestars
    save valuestars.ppm valuestars
    get-component intensity stars intensitystars
    save intensitystars.ppm intensitystars
    brighten 10 stars brightstars
    save brightstars.ppm brightstars
    darken 10 stars darkstars
    save darkstars.ppm darkstars
    vertical-flip stars verticalstars
    save verticalstars.ppm verticalstars
    horizontal-flip stars horizontalstars
    save horizontalstars.ppm horizontalstars
    q



#Citation
The image stars was free to use and taken from https://filesamples.com/formats/ppm
and converted into the correct ppm format using ImageMagick.


#Class Diagrams

![alt text](https://github.com/alimrana0/ImageProcessing/blob/main/diagram1.PNG)

![alt text](https://github.com/alimrana0/ImageProcessing/blob/main/diagram1.PNG)

![alt text](https://github.com/alimrana0/ImageProcessing/blob/main/diagram1.PNG)

#Design Overview
**-- ImageProcessor**
The main processor that executes the runtime of an ImageProcessor.
#model
**-- IImageProcessorModel**     
The IImageProcessingModel interface is the primary model interface for a single layered image. It contains all current image processing methods for our model, which are the transformations on an Image that the processor supports.   
**-- IImageProcessingSession**     
The IImageProcessingModel interface is our primary model interface for a multilayered image. It contains all current image processing methods for our model, which are the transformations on an Image that the processor supports. It also allows for the manipulation of the layers in the image, such as getting, adding, removing, and hiding the images.
**-- ImageProcessingModel**   
The ImageProcessingModelImpl is the primary model class, which functions as a caller on transformations on an image.
**-- ImageProcessingSession**
The ImageProcessingSession is a class that handles the saving/loading of files. It acts as a transient holder for images being processed in the controller.
**-- ImageProcessingSessionImpl**
The ImageProcessingSession is a class that handles the saving/loading of files of a multilayered image model and the transformations/filtering on these image layers. It acts as a transient holder for images being processed in the controller.
##graph
**-- Histogram**
The Histogram class is a class that represents the mapping of lines on a graph represented as red, green, blue, and intensity values.
**-- Line**
The Line class is a class that represents a connection of multiple Position2Ds in a specified color.
**-- Position2D**
The Position2D class is a class that represented a 2D positional coordinate.
##Imaging
**-- IColor**.  
The color interface contains methods to retrieve red,green, and blue color values from wherever they are stored.   
**-- Color**   
The Color class implements the Color interface. It represents an RGB color. It contains methods that override the equal and hashcode methods to find color equivalency.   
**-- Posn**
The Posn class Represents an 2D Cartesian coordinate.
**-- ImageOfPixel**.   
The ImageOfPixel interface is an interface that represents an Image contrived of pixels. The two methods in this interface either retrieve a 2D list of pixels representing the image or saves the image as a PPM file.
**-- Image**.  
The Image class implements the ImageOfPixel interface. It replicates the pixels in the image and saves them.  
###pixel
**-- IPixel**
The IPixel interface represents a pixel. It contains methods to get the color of the position of the pixel in an image.
**-- Pixel**
The Pixel class implements the Pixel interface. It overrides the equals and hashcode to find pixel equivalency.
#filters
**-- FilterClamp** 
The FilterClamp class clamps any RGB value to a minimum value of 0 or maximum value of 255.
**-- AbstractImageProcessing**
This is an abstract class that represents any filter on an image/pixel using kernels. Includes things like image blur or image sharpening.
**-- BlurFilter**
The BlurFilter class creates an instance that creates a kernel matrix that will allow for an image to be blurred.
**-- SharpenFilter**
The SharpenFilter class creates an instance that creates a kernel matrix that will allow for an image to be sharpened.
**-- IFilter**
The IFilter interface class holds one method that allows for the general filtering of an Image.
**-- IKernel**
The IKernel interface represents a kernel that holds a square matrix that can be used to manipulate an image through its matrix values.
**-- Kernel**
The Kernel class implements IKernel. It holds general methods to get the kernel's width, height, and values, as well as a method to retrieve a value of the matrix at a specific index.
##ColorTransformation
**-- AbstractColorTransformation**    
This is an abstract class that represents the many transformations we can do on the color of an image/pixel. Includes things like greyscaling an image, visualizing the red, green, and blue components of an image, or visualizing the intensity of an image.  
**-- IColorTransform**
The IColorTransform interface contains one method that applies a color transformation to an image.
**-- IntensityChange**    
A class that represents an intensity transformation, which just sets each pixel to the average of the three components for that pixel.
**-- ValueChange**   
This is a class that represents a value transformation, which sets each rgb value to the maximum value of the three components for each pixel.
**-- AbstractColorTransformationProcessor**   
This is an abstract class that represents any color transformation on an image/pixel using kernels. Includes things like image greyscale or image sepia.
**-- GreyscaleTransformation**   
This is a class that represents a greyscale transformation, which sets matrix values for RGB components for a pixel to be transformed into its greyscale version.
**-- SepiaTransformation**   
This is a class that represents a sepia transformation, which sets matrix values for RGB components for a pixel to be transformed into its sepia version.
###Greyscale
**-- BlueGreyscale**.    
The BlueGreyscale class represents a greyscale transformation on the blue component of an image. All RGB color values are set to the image's blue component value.   
**-- GreenGreyscale**.     
The GreenGreyscale class represents a greyscale transformation on the green component of an image. All RGB color values are set to the image's green component value. **--RedGreyscale**.    
**-- RedGreyscale**
The RedGreyscale class represents a greyscale transformation on the red component of an image. All RGB color values are set to the image's red component value.
**-- LumaGreyscale**   
The LumaGreyscale class represents a luma greyscale transformation, which uses the weighted sum of 0.2126r + 0.7152g + 0.0722b to set the RGB values to greyscale the image.
##IntensityTransformation
**-- AbstractIntensityTransformation**   
The AbstractIntensityTransformation applies some intensity transformation on a given image and returns the transformed image.  
**-- IIntensityTransform**
The IIntensityTransform interface contains one method that applies an intensity transformation to an image.
**-- BrightenTransform**   
The BrightenTransform class represents a transformation that adds certain value to the RGB components of an image which effectively brightens the image.
**-- DarkenTransform**
The DarkenTransform class represents a transformation that subtracts certain value to the RGB components of an image which effectively darkens the image.
##FlippingTransformation
**-- IFlipTransform**
The IFlipTransform interface contains one method that applies a translational transformation to an image.
**-- HorizontalFlip**   
The HorizontalFlip class represents an image being flipped over the vertical middle axis. 
**-- VerticalFlip**.
The VerticalFlip class represents an image being flipped over the horizontal middle axis.
#controller
**-- ImageController**
The ImageController interface is the primary controller interface for the Image Processor. It contains a single method called run(), which runs the Image Processor based off user input.  
**-- ImageControllerImpl**. 
The ImageControllerImpl class implements the ImageController interface. It is the primary controller for an Image Processor. This class takes in user input and delegates processing to the model depending on the given commands. It also allows for transformed images to be read and saved.
**-- ImageProcessingController**.
The ImageProcessingController class implements the ImageController interface. It is the primary controller for an Image Processor that deals with multilayered images. This class takes in user input and delegates processing to the model depending on the given commands. It also allows for transformed images to be read,saved, and hidden.
**-- ImageProcessingControllerGUI**.
The ImageProcessingController class implements the ImageController and IViewListener interface. It is the primary controller for an Image Processor that deals with multilayered images and is represented by a GUI. This class takes in user input through a GUI and delegates processing to the model depending on the given commands. It also allows for transformed images to be read,saved, and hidden.
##filereading
**-- ImageReadFile**
The ImageReadFile class is a class that reads in a general image and loads its contents
**-- IReadFile**
The IReadFile interface is an interface that contains one method that operates on an image to read its contents
**-- IReadMultiLayered**
The IReadMultiLayered interface is an interface that reads in a multilayered image and loads its contents
**-- PPMReadFile**
The PPMReadFile class is a class that contains methods to read in a PPM image and return its contents.
**-- ReadMultiLayered**
The ReadMultiLayered class is a class that contains methods to read in a multilayered image by using its contents and visibility to set the file paths and layer IDs.
##filereading
**-- AbstractImageWriteFile**
The AbstractImageWriteFile class is a class that creates an abstraction for an object that would write to a text file given an image's contents.
**-- IImageWriteFile**
The IImageWriteFile interface is an interface that contains one method that will be used to write to a file using an image's contents.
**-- IWriteMultiLayered**
The IWriteMultiLayered interface is an interface that contains one method that will be used to write to a file using a multilayered image's contents using the layer visibilities and IDs.
**-- PPMWriteFile**
The PPMWriteFile class is a class that contains methods to use an image's information to write to a PPM file representation of that image.
*-- PNGWriteFile**
The PNGWriteFile class is a class that contains methods to use an image's information to write to a PNG file representation of that image.
*-- JPEGWriteFile**
The JPEGWriteFile class is a class that contains methods to use an image's information to write to a JPEG file representation of that image.
**-- WriteMultiLayered**
The WriteMultiLayered class is a class that contains methods to use a multilayered image's information to write to a folder that contains the image file representing the image and a text file of its general contents.
#view
**-- IImageProcessingView**
The IImageProcessingView interface contains two methods to display. One renders a given message to the System, and the other shows the client options for commands.
**-- ImageProcessingView**
The ImageProcessingView class implements the IImageProcessingView interface. It performs transformations on the given image based on user inputs. The user may use current files or transform previously saved files.
**-- IImageProcessingGUIView**
The IImageProcessingGUIView interface contains methods to display a GUI that can add and set images.
**-- IViewListener**
The IViewListener interface contains most of the methods that would be required of a GUI representing a multilayered image.
**-- ImageProcessingGUIView**
The ImageProcessingGUIView class extends JFrame implements IImageProcessingGUIView, ActionListener and contains the main code for the GUI display of a multilayered image. It contains every possible action possible by the user, and allows the actions to be interactive by allowing user input to change the view.
##graph
**-- GraphPanel**
The GraphPanel class is a class that creates a JLayeredPanel that constructs a graph of given RGB and intensity values based on the image's pixel information.

#Downscale Breakdown
The way we implemented downscaled was largely detailed toward the assignment description's formulas that allow for an image to be scaled.
This was done by creating an interface that would be able to manipulate the size of an image (potential image upscaling). A class was used to
implement this interface that performed the task of breaking down an image's pixel locations, and comparing the ratio of the pixel's location
to the original width and height, and applying it to the desired changed image dimensions' ratio. Since this is a transformation, it was added to
ImageProcessingModel and ImageProcessingSession to perform on a given image. In order to allow for multiple layering of transforming, this downscale was also added
to the ImageProcessingSessionImpl. The controller had an added switch statement to perform this action,
and the ImageProcessingGUIView was changed in order to allow for these operations to be completed and visualized in the GUI.

#Mask Transformation Breakdown
Since this feature requires our program to interact with multiple images at the same time (image being transformed and the mask version of the image),
we had to make abstractions for our previously made filters. This is so that the transformation could be applied to any type of image transformation,
as well as for added extensibility for any potential changes in manipulating an image's pixels that doesn't have to pertain to its black pixels. The hope with this
was to allow for future masking that would be able to transform based on selected for pixels, other than just black. For example: Changing only the pixels in
the mask image (which doesn't have to be black and white so that the user can transform whatever image they want that isn't strictly black and white) that have an RGB
green value of 20. This will allow thresholds to be added later on. These abstracted classes (AbstractColorMaskTransform, AbstractIntensityMaskTransform, and AbstractImageProcessingMaskTransform).
had a method for the previous filters to implement, and the original abstract classes extended these mask abstracted classes. ColorMaskTransformProcessor was
not abstract because the filters that implement it only construct from a kernel, and don't need to call the method independently. The ImageProcessingModel and
ImageProcessingSession required additions for each feature that supports masking that takes in a masked image. This will allow, again, for future mask
transformations that don't have to pertain to just black pixels. The controller was changed in order to allow for these operations to occur, and since
this operations didn't need to be implemented in the GUI, there was no change to the View.