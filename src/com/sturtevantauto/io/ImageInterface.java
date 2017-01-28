package com.sturtevantauto.io;

import java.io.File;

import com.sturtevantauto.gui.ImageSorter;

public class ImageInterface {
    static boolean foundstock;
    static boolean imagetime;
    static int count;
    static Car car = ImageSorter.getCar();

    /**
     * Lists all of the files in the given folder and stores them with
     * CarDefinitions.setImageNames();
     * 
     * @param file
     *            Filepath to search
     */
    public static void findFile(File file, boolean skipped) {
        count = 0;
        foundstock = false;
        imagetime = false;
        car.setStock(null);
        File[] list = file.listFiles();
        if (list != null)
            for (File filer : list) {
                if (skipped) {
                    if (imagetime) {
                        if (filer.getName().contains("__"))
                            imagetime = false;
                        else {
                            car.setImageNames(filer.getPath(), count);
                            count++;
                        }
                    }
                    if (filer.getName().contains("__") && !foundstock) {
                        car.setStockFile(filer);
                        car.setStock(filer.getName());
                        imagetime = true;
                        foundstock = true;
                    }
                } else {
                    if (imagetime) {
                        if (filer.getName().contains("F") || filer.getName().contains("G0") || filer.getName().contains("E") || filer.getName().contains("G1") 
                                || filer.getName().contains("H0") || filer.getName().contains("H1") || filer.getName().contains("J0") || filer.getName().contains("J1")
                                || filer.getName().contains("K0") || filer.getName().contains("K1") || filer.getName().contains("L0") || filer.getName().contains("L1")
                                || filer.getName().contains("M0") || filer.getName().contains("M1") || filer.getName().contains("N0") || filer.getName().contains("N1")
                                || filer.getName().contains("P0") || filer.getName().contains("P1") || filer.getName().contains("Q0") || filer.getName().contains("Q1")
                                || filer.getName().contains("R0") || filer.getName().contains("R1") || filer.getName().contains("S0") || filer.getName().contains("S1")
                                || filer.getName().contains("T0") || filer.getName().contains("T1") || filer.getName().contains("U0") || filer.getName().contains("U1")
                                || filer.getName().contains("V0") || filer.getName().contains("V1") || filer.getName().contains("W0") || filer.getName().contains("W1")
                                || filer.getName().contains("X0") || filer.getName().contains("X1") || filer.getName().contains("Y0") || filer.getName().contains("Y1")
                                || filer.getName().contains("Z0") || filer.getName().contains("Z1")) {
                            imagetime = false;
                        } else {
                            car.setImageNames(filer.getPath(), count);
                            count++;
                        }
                    }
                    if (!foundstock) {
                        if (filer.getName().contains("F") || filer.getName().contains("G0") || filer.getName().contains("E") || filer.getName().contains("G1") 
                                || filer.getName().contains("H0") || filer.getName().contains("H1") || filer.getName().contains("J0") || filer.getName().contains("J1")
                                || filer.getName().contains("K0") || filer.getName().contains("K1") || filer.getName().contains("L0") || filer.getName().contains("L1")
                                || filer.getName().contains("M0") || filer.getName().contains("M1") || filer.getName().contains("N0") || filer.getName().contains("N1")
                                || filer.getName().contains("P0") || filer.getName().contains("P1") || filer.getName().contains("Q0") || filer.getName().contains("Q1")
                                || filer.getName().contains("R0") || filer.getName().contains("R1") || filer.getName().contains("S0") || filer.getName().contains("S1")
                                || filer.getName().contains("T0") || filer.getName().contains("T1") || filer.getName().contains("U0") || filer.getName().contains("U1")
                                || filer.getName().contains("V0") || filer.getName().contains("V1") || filer.getName().contains("W0") || filer.getName().contains("W1")
                                || filer.getName().contains("X0") || filer.getName().contains("X1") || filer.getName().contains("Y0") || filer.getName().contains("Y1")
                                || filer.getName().contains("Z0") || filer.getName().contains("Z1")) {
                            car.setStock(filer.getName());
                            count = 0;
                            foundstock = true;
                            imagetime = true;
                            car.setStockFile(filer);
                        }
                    }
                }
            }
    }

    /**
     * Counts the amount of pictures in the folder
     * 
     * @param file
     * @return int picturecount
     */
    public static int countPictures(File file) {
        File[] list = file.listFiles();
        int pictures = 0;
        if (list != null)
            for (File filer : list) {
                if (filer.getName().contains("F") || filer.getName().contains("G0") || filer.getName().contains("E") || filer.getName().contains("G1") 
                        || filer.getName().contains("H0") || filer.getName().contains("H1") || filer.getName().contains("J0") || filer.getName().contains("J1")
                        || filer.getName().contains("K0") || filer.getName().contains("K1") || filer.getName().contains("L0") || filer.getName().contains("L1")
                        || filer.getName().contains("M0") || filer.getName().contains("M1") || filer.getName().contains("N0") || filer.getName().contains("N1")
                        || filer.getName().contains("P0") || filer.getName().contains("P1") || filer.getName().contains("Q0") || filer.getName().contains("Q1")
                        || filer.getName().contains("R0") || filer.getName().contains("R1") || filer.getName().contains("S0") || filer.getName().contains("S1")
                        || filer.getName().contains("T0") || filer.getName().contains("T1") || filer.getName().contains("U0") || filer.getName().contains("U1")
                        || filer.getName().contains("V0") || filer.getName().contains("V1") || filer.getName().contains("W0") || filer.getName().contains("W1")
                        || filer.getName().contains("X0") || filer.getName().contains("X1") || filer.getName().contains("Y0") || filer.getName().contains("Y1")
                        || filer.getName().contains("Z0") || filer.getName().contains("Z1")) {
                    pictures++;
                }
            }
        return pictures;
    }

    /**
     * Copies images from the sorting location to the sorted location when given
     * the stock, model, and make
     * 
     * @param stock
     * @param model
     * @param make
     */
    public static void CopyFiles(String stock, String model, String make) {
        File mainpath = car.getStorageLocation(false, make, model, stock);
        String carpath = make + "_" + model + "_" + stock + "_";
        mainpath.mkdirs();
        File[] images = new File[count];
        File[] imagesend = new File[count];
        int i = 0;
        while (i < count) {
            if (car.getImageNames()[i] == null || car.getImageNames()[i].contains("F") || car.getImageNames()[i].contains("G0") || car.getImageNames()[i].contains("E1") ||
                    car.getImageNames()[i].contains("H0") || car.getImageNames()[i].contains("H1") || car.getImageNames()[i].contains("G1")) {
                System.err.println("Image " + (i + 1) + " failed to move because it was a stock number picture somehow.");
            } else {
                images[i] = new File(car.getImageNames()[i]);
                if (car.getImageNames()[i].endsWith("_W.JPG")) {
                    mainpath = car.getStorageLocation(true, make, model, stock);
                    mainpath.mkdirs();
                }
                imagesend[i] = new File(mainpath + "/" + carpath + (i + 1) + ".jpg");
                if (!images[i].renameTo(imagesend[i])) {
                    System.out.println("Image " + (i + 1) + "failed to move. Missing?");
                }
            }
            i++;
        }
    }
    
    public static void CopyQuicksort(String stock) {
        File mainpath = new File("/Users/sturtevantauto/Pictures/Quicksort/" + stock);
        mainpath.mkdirs();
        File[] images = new File[count];
        File[] imagesend = new File[count];
        int i = 0;
        while (i < count) {
            images[i] = new File(car.getImageNames()[i]);
            imagesend[i] = new File(mainpath + "/" + stock + "_" + (i + 1) + ".jpg");
            if (!images[i].renameTo(imagesend[i])) {
                System.out.println("Image " + (i + 1) + "failed to move. Missing?");
            }
            i++;
        }
        
    }
}
