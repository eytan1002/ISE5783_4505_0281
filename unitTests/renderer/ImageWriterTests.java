package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTests {


    @Test
    void testWriteToImage() {
        //initialize the view plane resolustion for 800x500
        int nX = 800;
        int nY = 500;
        double width = 16;
        Color skyBlue = new Color(108, 171, 221);
        Color gold = new Color(212, 161, 42);
        ImageWriter imageWriter = new ImageWriter("testGoldGrid", nX, nY);
        double margin = imageWriter.getNx() / width;
        for (int i = 0; i < nX; i++)//scan the width
        {
            for (int j = 0; j < nY; j++) {
                if (j % margin == 0 || i % margin == 0) {
                    imageWriter.writePixel(i, j, gold);
                } else {
                    imageWriter.writePixel(i, j, skyBlue);
                }
            }
        }
        imageWriter.writeToImage();
    }

}