/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picturelabfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author alexb
 */
public class PictureLabFX extends Application {

    @Override
    public void start(Stage stage) {
        Image myImage = new Image("ckBball.jpg");
        //Call methods to manipulate image here. Just uncomment each method for 
        //testing, and recomment the methods you don't want to test.

        //myImage = toGrayscale(myImage);
        //myImage = toInverse(myImage);
        //myImage = mirrorLtoR(myImage);
        //myImage = mirrorTtoB(myImage);
        //myImage = mirrorDiagonal(myImage);
        //myImage = flipX(myImage);
        //myImage = flipY(myImage);
        //Need to get a new picture for these
        //Image image2 = new Image("download.jpg");
        Image patch = getPartialImage(myImage, 65, 150, 50, 75);
        //ImageView picView = new ImageView(patch);
        patch = toInverse(patch);
        myImage = superimpose(65, 150, patch, myImage);

        ImageView picView = new ImageView(myImage);
        picView.setFitWidth(500);
        picView.setPreserveRatio(true);

        Group images = new Group();
        images.getChildren().addAll(picView);

        Scene scene = new Scene(images);
        stage.setHeight(500);
        stage.setWidth(500);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static Image toGrayscale(Image original) {
        Color[][] pixels = PictureUtility.imageTo2DArray(original);
        Color[][] result = new Color[pixels.length][pixels[0].length];
        for (int row = 0; row < pixels.length; row++) {
            for (int cell = 0; cell < pixels[row].length; cell++) {
                Color pixel = pixels[row][cell];
                double average = (pixel.getRed() + pixel.getBlue() + pixel.getGreen()) / 3;
                result[row][cell] = new Color(average, average, average, 0.5);
            }
        }
        return PictureUtility.array2DToImage(result);
    }

    public static Image toInverse(Image original) {
        Color[][] pixels = PictureUtility.imageTo2DArray(original);
        Color[][] result = new Color[pixels.length][pixels[0].length];
        for (int row = 0; row < pixels.length; row++) {
            for (int cell = 0; cell < pixels[row].length; cell++) {
                Color pixel = pixels[row][cell];
                double newR = (1 - pixel.getRed());
                double newB = (1 - pixel.getBlue());
                double newG = (1 - pixel.getGreen());
                result[row][cell] = new Color(newR, newG, newB, 0.5);
            }
        }
        return PictureUtility.array2DToImage(result);
    }

    public static Image mirrorLtoR(Image original) {
        Color[][] pixels = PictureUtility.imageTo2DArray(original);
        Color[][] result = new Color[pixels.length][pixels[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length / 2; col++) {
                result[row][col] = pixels[row][col];
            }
            for (int col = result[row].length / 2; col < result[row].length; col++) {
                result[row][col] = pixels[row][pixels[row].length - 1 - col];
            }

        }
        return PictureUtility.array2DToImage(result);
    }

    public static Image mirrorTtoB(Image original) {
        Color[][] pixels = PictureUtility.imageTo2DArray(original);
        Color[][] result = new Color[pixels.length][pixels[0].length];

        for (int row = 0; row < result.length / 2; row++) {
            result[row] = pixels[row];
        }
        for (int row = result.length / 2; row < result.length; row++) {
            result[row] = pixels[pixels.length - 1 - row];
        }
        return PictureUtility.array2DToImage(result);
    }

    public static Image mirrorDiagonal(Image original) {
        Color[][] pixels = PictureUtility.imageTo2DArray(original);
        Color[][] result = new Color[pixels.length][pixels[0].length];
        int i = pixels[0].length;
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < i; col++) {
                result[row][col] = pixels[row][col];
            }
            for (int col = i; col < pixels[row].length; col++) {
                result[row][col] = pixels[row - (col % i)][i - (col % i)];
            }
            i -= 1;
        }
        return PictureUtility.array2DToImage(result);
    }

    public static Image superimpose(double x, double y, Image patch, Image original) {
        Color[][] pixels = PictureUtility.imageTo2DArray(original);
        Color[][] newImg = PictureUtility.imageTo2DArray(patch);
        Color[][] result = new Color[pixels.length][pixels[0].length];
        
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[0].length; col++) {
                result[row][col] = pixels[row][col];
            }
        }
        
        for (int row = (int) y; row < newImg.length + (int) y; row++){
            for (int col = (int) x; col < newImg[0].length + (int) x; col++){
                result[row][col] = newImg[row - (int) y][col - (int) x];
            }
        }
        
        
        return PictureUtility.array2DToImage(result);
    }

    public static Image getPartialImage(Image source, double x, double y, double width, double height) {
        Color[][] fullImage = PictureUtility.imageTo2DArray(source);
        int adjWidth = ((int) width <= fullImage[0].length - (int) x) ? (int) width : fullImage[0].length - (int) x;
        int adjHeight = ((int) height <= fullImage.length - (int) y) ? (int) height : fullImage.length - (int) y;

        Color[][] patch = new Color[adjHeight][adjWidth];

        for (int row = (int) y; row < adjHeight + (int) y; row++) {
            for (int col = (int) x; col < adjWidth + (int) x; col++) {
                patch[row - (int) y][col - (int) x] = fullImage[row][col];
            }
        }
        return PictureUtility.array2DToImage(patch);
    }

    public static Image flipX(Image source) {
        Color[][] pixels = PictureUtility.imageTo2DArray(source);
        for (int j = 0; j < pixels.length; j++) {
            for (int i = 0; i < (pixels[0].length) / 2; i++) {
                Color temp = pixels[j][i];
                pixels[j][i] = pixels[j][pixels[0].length - i - 1];
                pixels[j][pixels[0].length - i - 1] = temp;
            }
        }
        return PictureUtility.array2DToImage(pixels);
    }

    public static Image flipY(Image source) {
        Color[][] pixels = PictureUtility.imageTo2DArray(source);
        for (int i = 0; i < (pixels.length) / 2; i++) {
            Color[] temp = pixels[i];
            pixels[i] = pixels[pixels.length - i - 1];
            pixels[pixels.length - i - 1] = temp;
        }
        return PictureUtility.array2DToImage(pixels);
    }
}
