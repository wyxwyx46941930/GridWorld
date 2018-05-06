//import imagereader.*;
//import imagereader.IImageProcessor;

import imagereader.IImageProcessor;

import java.awt.Toolkit;
import java.awt.Image;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class MyImageProcessor implements IImageProcessor {
    // RED_FILTER for showChanelR
    private static final int RED_FILTER = 0xffff0000;
    // GREEN_FILTER for showChanelG
    private static final int GREEN_FILTER = 0xff00ff00;
    // BLUE_FILTER for showChanelB
    private static final int BLUE_FILTER = 0xff0000ff;
    
    // The flag for RGBFilter to use the gray mode
    private static final int GRAY_MODE= 0;
    // The red filter that remove the alaph
    private static final int RED_GETTER = 0x00ff0000;
    // The green filter that remove the alaph
    private static final int GREEN_GETTER = 0x0000ff00;
    // The blue filter that remove the alaph
    private static final int BLUE_GETTER = 0x000000ff;

    private static final double RED_FACTOR = 0.299;
    private static final double GREEN_FACTOR = 0.587;
    private static final double BLUE_FACTOR = 0.114;

    class RGBFilter extends RGBImageFilter {
        
        private static final int SIXTEEN = 16;
        private static final int EIGHT = 8;

        private int model;
        // constructor
        public RGBFilter(int model) {
            this.model = model;
            canFilterIndexColorModel = true;
        }
        public int filterRGB(int x, int y, int rgb) {
            // gray mode
            if (model == GRAY_MODE) {
                int red = (rgb & RED_GETTER) >> SIXTEEN;
                int green = (rgb & GREEN_GETTER) >> EIGHT;
                int blue = (rgb & BLUE_GETTER);
                int gray = (int) (red * RED_FACTOR + green * GREEN_FACTOR + blue * BLUE_FACTOR);
                return (rgb & 0xff000000) | (gray << SIXTEEN) | (gray << EIGHT) | gray;
            }
            return rgb & model;
        }
    }
    public Image showChanelR(Image sourceImage) {
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(
                        sourceImage.getSource(),
                        new RGBFilter(RED_FILTER)));
    }
    public Image showChanelG(Image sourceImage) {
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(
                        sourceImage.getSource(),
                        new RGBFilter(GREEN_FILTER)));
    }
    public Image showChanelB(Image sourceImage) {
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(
                        sourceImage.getSource(),
                        new RGBFilter(BLUE_FILTER)));
    }
    public Image showGray(Image sourceImage) {
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(
                        sourceImage.getSource(),
                        new RGBFilter(GRAY_MODE)));
    }
}
