import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class Cropper {
    public static void main(String[] args) {
        List<String> values = getValues();
        cropImage(values);
    }

    public static List<String> getValues() {
        List<String> valuesList = new ArrayList<>();
        // user chooses directory
        System.out.println("Please choose a folder: ");
        Scanner input = new Scanner(System.in);
        String directory = input.nextLine();
        valuesList.add(directory);
        // user chooses x coordinate
        System.out.println("Choose x coordinate: ");
        input = new Scanner(System.in);
        String xCoordinate = (input.nextLine());
        valuesList.add(xCoordinate);
        // user chooses x coordinate
        System.out.println("Choose y coordinate: ");
        input = new Scanner(System.in);
        String yCoordinate = (input.nextLine());
        valuesList.add(yCoordinate);
        // user chooses width
        System.out.println("Choose final image width: ");
        input = new Scanner(System.in);
        String width = (input.nextLine());
        valuesList.add(width);
        // user chooses height
        System.out.println("Choose final image height: ");
        input = new Scanner(System.in);
        String height = (input.nextLine());
        valuesList.add(height);

        return valuesList;
    }

    public static void cropImage(List<String> values) {
        try {
            File dir = new File(values.get(0));
            File[] filesInFolder = dir.listFiles();
            Integer fileNum = 0;
            if (filesInFolder != null) {
                for (File file : filesInFolder) {
                    fileNum += 1;
                    BufferedImage originalImg = ImageIO.read(file);
                    System.out.println("Original Image Dimensions: " + originalImg.getWidth() + " x " + originalImg.getHeight());

                    // Creating a subimage of given dimensions
                    Integer xCoordinate = Integer.parseInt(values.get(1));
                    Integer yCoordinate = Integer.parseInt(values.get(2));
                    Integer width = Integer.parseInt(values.get(3));
                    Integer height = Integer.parseInt(values.get(4));
                    BufferedImage SubImg = originalImg.getSubimage(xCoordinate, yCoordinate, width, height);

                    // Creating new file for cropped image by
                    // creating an object of File class
                    StringBuilder sb = new StringBuilder();
                    sb.append(dir.getPath());
                    sb.append("\\ImageCropperOutput\\");
                    File outputDir = new File(sb.toString());
                    if (!outputDir.exists()) {
                        outputDir.mkdir();
                    }
                    sb.append("CroppedImage");
                    sb.append(fileNum);
                    sb.append(".png");
                    System.out.println(sb.toString());
                    File outputfile = new File(sb.toString());

                    ImageIO.write(SubImg, "png", outputfile);
                    System.out.println("Cropped Image created successfully");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
