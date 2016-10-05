package com.sturtevantauto.gui;

import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import com.sturtevantauto.io.Car;
import com.sturtevantauto.io.ImageInterface;
import com.sturtevantauto.io.Logger;
import com.sturtevantauto.io.MakeModelInterface;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JMenuBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;

public class ImageSorter {

    public JFrame frmImageSorter;
    private JTextField modelField;
    private JTextField makeField;
    private JTextField stockField;
    private JButton skipCarButton;
    private JLabel carPicture1;
    private JLabel carPicture2;
    private JLabel carPicture3;
    private JLabel carPicture4;
    private boolean buttonpushed = false;
    public static boolean quicksort;
    int increment = 100;
    static Car car = new Car();

    /**
     * Create the application.
     */
    public ImageSorter() {
        car.setMake(null);
        ImageInterface.findFile(car.getPictureLocation(), false);
        if (car.getStock() == null) {
            System.err.println("No cars found to sort!");
        } else if (car.getPictureLocation().toString().endsWith("/SORT_ME"))
            car.TrimStock(false);
        else if (car.getPictureLocation().toString().endsWith("/SKIPPED")) {
            ImageInterface.findFile(car.getPictureLocation(), true);
            car.TrimStock(true);
        }

        if (ImageInterface.countPictures(car.getPictureLocation()) != 0)
            increment = 100 / ImageInterface.countPictures(car.getPictureLocation());
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmImageSorter = new JFrame();
        frmImageSorter.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        frmImageSorter.setResizable(false);
        if (quicksort)
            frmImageSorter.setTitle("Image Quicksorter");
        if (!quicksort)
            frmImageSorter.setTitle("Image Sorter");
        frmImageSorter.setBounds(100, 100, 1000, 622);
        frmImageSorter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmImageSorter.getContentPane().setLayout(null);
        final JButton btnSort = new JButton("Sort");
        btnSort.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        if (!quicksort)
            btnSort.setEnabled(false);
        final JTextPane outputWindow = new JTextPane();
        outputWindow.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        outputWindow.setToolTipText("Displays output such as error or success messages");
        outputWindow.setEditable(false);
        if (quicksort)
            outputWindow.setBounds(15, 141, 979, 392);
        if (!quicksort)
            outputWindow.setBounds(15, 141, 295, 172);
        frmImageSorter.getContentPane().add(outputWindow);
        if (car.getStock() == null) {
            outputWindow.setText("No cars were found in the sorting directory.  Perhaps you didn't add the stock number to the end of any of the files, or forgot to import the pictures?");
        }
        if (!quicksort) {
            makeField = new JTextField();
            makeField.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
            makeField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    if (modelField.getText() != null) {
                        try {
                            car.setMake(makeField.getText());
                            MakeModelInterface.WriteMakeModelIndex(car.getModel(), car.getMake());
                            outputWindow.requestFocus();
                            outputWindow.setText("Make/Model association set!  Click the sort button to sort this car.");
                            btnSort.setEnabled(true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else
                        outputWindow.setText("Please enter the model first, and I'll look up the make for you.");
                }
            });
        }
        final JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(6, 545, 859, 27);
        progressBar.setVisible(false);
        frmImageSorter.getContentPane().add(progressBar);
        if (!quicksort) {
            JLabel lblStock = new JLabel("Stock #:");
            lblStock.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
            lblStock.setBounds(1, 11, 51, 16);
            frmImageSorter.getContentPane().add(lblStock);

            stockField = new JTextField();
            stockField.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
            stockField.setToolTipText("Stock # of the current scanned vehicle");
            stockField.setEditable(false);
            stockField.setBounds(55, 5, 255, 28);
            stockField.setColumns(10);
            frmImageSorter.getContentPane().add(stockField);

            modelField = new JTextField();
            modelField.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
            modelField.setToolTipText("Enter the model of the vehicle here");
            modelField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    car.setModel(modelField.getText());
                    MakeModelInterface.foundmake = false;
                    try {
                        MakeModelInterface.CheckMakeModelIndex(car.getModel());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (!MakeModelInterface.foundmake) {
                        outputWindow.requestFocus();
                        outputWindow.setText("Please enter the make as well.  I can't retrieve it.");
                        makeField.setText("");
                        makeField.setEditable(true);
                    } else {
                        outputWindow.requestFocus();
                        outputWindow.setText("Make found!  Click sort to sort this car!");
                        btnSort.setEnabled(true);
                        makeField.setText(car.getMake());
                        makeField.setEditable(false);
                    }
                }
            });

            JLabel lblmodel = new JLabel("\u0010Model:");
            lblmodel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
            lblmodel.setBounds(6, 45, 46, 16);
            frmImageSorter.getContentPane().add(lblmodel);

            modelField.setBounds(55, 39, 255, 28);
            modelField.setColumns(10);
            frmImageSorter.getContentPane().add(modelField);

            JLabel lblNewLabel = new JLabel("Make:");
            lblNewLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
            lblNewLabel.setBounds(15, 79, 37, 16);
            frmImageSorter.getContentPane().add(lblNewLabel);

            makeField.setToolTipText("Sometimes the make won't be found, and you'll have to enter it here");
            makeField.setBounds(55, 73, 255, 28);
            makeField.setEditable(false);
            makeField.setColumns(10);
            frmImageSorter.getContentPane().add(makeField);

            if (car.getStock() == null)
                modelField.setEditable(false);
        }
        JLabel lblOutputWindow = new JLabel("Output window:");
        lblOutputWindow.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        lblOutputWindow.setBounds(15, 113, 117, 16);
        frmImageSorter.getContentPane().add(lblOutputWindow);
        if (!quicksort) {
            carPicture1 = new JLabel();
            carPicture1.setToolTipText("Double click on this to open the displayed image");
            carPicture1.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
            loadMouseAdapter(carPicture1, 0);
            carPicture1.setBounds(322, 6, 330, 253);
            loadImage(carPicture1, 0);
            frmImageSorter.getContentPane().add(carPicture1);

            carPicture2 = new JLabel();
            carPicture2.setToolTipText("Double click on this to open the displayed image");
            carPicture2.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
            loadMouseAdapter(carPicture2, 1);
            carPicture2.setBounds(664, 6, 330, 253);
            loadImage(carPicture2, 1);
            frmImageSorter.getContentPane().add(carPicture2);

            carPicture3 = new JLabel();
            carPicture3.setToolTipText("Double click on this to open the displayed image");
            carPicture3.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
            loadMouseAdapter(carPicture3, 2);
            carPicture3.setBounds(322, 271, 330, 253);
            loadImage(carPicture3, 2);
            frmImageSorter.getContentPane().add(carPicture3);

            carPicture4 = new JLabel();
            carPicture4.setToolTipText("Double click on this to open the displayed image");
            carPicture4.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
            loadMouseAdapter(carPicture4, 3);
            carPicture4.setBounds(664, 271, 330, 253);
            loadImage(carPicture4, 3);
            frmImageSorter.getContentPane().add(carPicture4);

            /**
             * Handles when the "Sort" button is clicked
             */
            btnSort.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    progressBar.setVisible(true);
                    btnSort.setEnabled(false);
                    progressBar.setValue(progressBar.getValue() + increment);
                    outputWindow.setText("");
                    String[] options = new String[] { "Delete", "Continue" };
                    try {
                        if (Logger.CheckIfCarIndexed(car.getStock())) {
                            int choice = JOptionPane.showOptionDialog(null, "Would you like to delete the existing files for this car, or just continue anyways?", "Car already sorted!",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                            if (choice == 0) {
                                car.getStockFile().delete();
                                File[] images = new File[5];
                                int i = 0;
                                while (i < car.getImageNames().length) {
                                    if (car.getImageNames()[i] != null) {
                                        images[i] = new File(car.getImageNames()[i]);
                                        images[i].delete();
                                    }
                                    i++;
                                }
                                if (car.getStock() != null) {
                                    outputWindow.setText(car.getStock() + " successfully deleted!");
                                    car.setMake(null);
                                    ImageInterface.findFile(car.getPictureLocation(), false);
                                    loadImage(carPicture1, 0);
                                    loadImage(carPicture2, 1);
                                    loadImage(carPicture3, 2);
                                    loadImage(carPicture4, 3);
                                    car.TrimStock(false);
                                    stockField.setText(car.getStock());
                                } else
                                    stockField.setText("NO CARS");

                                makeField.setText("");
                                makeField.setEditable(false);
                                modelField.setText("");
                                return;
                            }
                            if (choice == 1) {
                                // This is where the continue option is handled
                            }
                        }
                        if (car.getStock() != null) {
                            ImageInterface.CopyFiles(car.getStock(), car.getModel(), car.getMake());
                            car.getStockFile().delete();
                            Logger.LogCar(car.getStock());
                            outputWindow.setText(car.getStock() + " " + car.getMake() + " " + car.getModel() + " successfully sorted!");
                            car.setMake(null);
                            ImageInterface.findFile(car.getPictureLocation(), false);
                            loadImage(carPicture1, 0);
                            loadImage(carPicture2, 1);
                            loadImage(carPicture3, 2);
                            loadImage(carPicture4, 3);
                            car.TrimStock(false);
                            stockField.setText(car.getStock());
                        } else
                            stockField.setText("NO CARS");

                        makeField.setText("");
                        makeField.setEditable(false);
                        modelField.setText("");

                    } catch (HeadlessException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            btnSort.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    if (!buttonpushed) {
                        progressBar.setVisible(true);
                        System.out.println("Found " + ImageInterface.countPictures(car.getPictureLocation()) + " cars in the sorting directory.  Click the button again to sort them all!");
                        buttonpushed = true;
                        outputWindow.setText("Found " + ImageInterface.countPictures(car.getPictureLocation()) + " cars in the sorting directory.  Click the button again to sort them all!");
                        progressBar.setValue(progressBar.getValue() + increment);
                        return;
                    }
                    if (buttonpushed) {
                        btnSort.setEnabled(false);
                        int i = 0;
                        int count = ImageInterface.countPictures(car.getPictureLocation());
                        outputWindow.setText("");
                        while (count > i) {
                            progressBar.setValue(progressBar.getValue() + increment);
                            ImageInterface.CopyQuicksort(car.getStock());
                            outputWindow.setText(outputWindow.getText() + car.getStock() + " successfully sorted to /Users/sturtevantauto/Pictures/Quicksort/" + car.getStock() + System.getProperty("line.separator"));
                            car.getStockFile().delete();
                            ImageInterface.findFile(car.getPictureLocation(), false);
                            i++;
                            if(count > i)
                                car.TrimStock(false);
                        }
                        outputWindow.setText(outputWindow.getText() + "Done sorting!  Sorted " + count + " cars.  You can now safely close the program.");
                    }
                }
            });
        }
        btnSort.setToolTipText("Clicking this button will sort the current car and proceed");
        btnSort.setBounds(877, 536, 117, 36);
        frmImageSorter.getContentPane().add(btnSort);
        if (!quicksort) {
            skipCarButton = new JButton("Skip this car (copies it to skipped car folder)");
            skipCarButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    progressBar.setValue(progressBar.getValue() + increment);
                    car.getStockFile().renameTo(new File("/Users/sturtevantauto/Pictures/Car_Pictures/SKIPPED/" + car.getStock() + "__.jpg"));
                    for (int i = 0; car.getImageNames().length > i; i++) {
                        if (car.getImageNames()[i] != null) {
                            File carfile = new File(car.getImageNames()[i]);
                            carfile.renameTo(new File("/Users/sturtevantauto/Pictures/Car_Pictures/SKIPPED/" + car.getStock() + "_" + i + ".jpg"));
                        }
                    }
                    outputWindow.setText(car.getStock() + " successfully moved out for later sorting!");
                    car.setMake(null);
                    ImageInterface.findFile(car.getPictureLocation(), false);
                    if (car.getStock() != null) {
                        car.TrimStock(false);
                        stockField.setText(car.getStock());
                    } else
                        stockField.setText("NO CARS");
                    makeField.setText("");
                    makeField.setEditable(false);
                    modelField.setText("");

                }
            });
            skipCarButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
            skipCarButton.setBounds(15, 323, 295, 36);
            frmImageSorter.getContentPane().add(skipCarButton);
        }
        JLabel label = new JLabel("");
        label.setBounds(436, 547, 61, 16);
        frmImageSorter.getContentPane().add(label);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        frmImageSorter.setJMenuBar(menuBar);

        JMenu menuFile = new JMenu("File");
        menuFile.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        menuBar.add(menuFile);

        JMenuItem menuItem1 = new JMenuItem("About");
        String[] opt = { "OK" };
        menuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ImageIcon imageIcon = null;
                try {
                    Image img = ImageIO.read(getClass().getResource("/img/jaytek.jpg"));
                    imageIcon = new ImageIcon(img);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                JOptionPane
                        .showOptionDialog(null,
                                "<html>Made by Jaytek (2016)<br>"
                                + "Version 1.1.3-SNAPSHOT<br>"
                                + "<url>http://jaytek.org/</url><br>"
                                + "Registered under LGPL-3.0<br>"
                                + "Authorized for use by anyone.  You just can't <br>"
                                + "modify the program in a way that would intentionally <br>"
                                + "break it, and you can't claim it's yours.</html>",
                                "About", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, imageIcon, opt, opt[0]);
            }
        });
        menuItem1.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        menuFile.add(menuItem1);
        if (!quicksort) {
            JMenuItem menuItem2 = new JMenuItem("Change storage location");
            menuItem2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int result = fileChooser.showOpenDialog(frmImageSorter);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        String[] options = new String[] { "Cancel", "Continue" };
                        int choice = JOptionPane.showOptionDialog(null,
                                "This will restart the program, resulting in loss of progress beyond the last time you clicked the sort button.  Would you like to proceed?", "Warning",
                                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                        if (choice == 1) {
                            File selectedFile = fileChooser.getSelectedFile();
                            car.setPictureLocation(selectedFile.toString() + "/");
                            restart();
                            if (selectedFile.toString().contains("SKIP"))
                                skipCarButton.setVisible(false);
                            else if (selectedFile.toString().contains("SORT"))
                                skipCarButton.setVisible(true);

                        }
                    }
                }
            });
            menuItem2.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
            menuFile.add(menuItem2);
        }
        JMenuItem menuItem3 = new JMenuItem("Restart");
        menuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restart();
            }
        });
        menuItem3.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        menuFile.add(menuItem3);

        JCheckBoxMenuItem menuItem4 = new JCheckBoxMenuItem("Check!");
        menuFile.add(menuItem4);
        if (!quicksort) {
            if (car.getStock() != null)
                stockField.setText(car.getStock());
            else {
                carPicture1.setText("No image found!");
                carPicture1.setHorizontalAlignment(SwingConstants.CENTER);
                carPicture1.setVerticalAlignment(SwingConstants.CENTER);
                carPicture1.setIcon(null);
                carPicture2.setText("No image found!");
                carPicture2.setHorizontalAlignment(SwingConstants.CENTER);
                carPicture2.setVerticalAlignment(SwingConstants.CENTER);
                carPicture2.setIcon(null);
                carPicture3.setText("No image found!");
                carPicture3.setHorizontalAlignment(SwingConstants.CENTER);
                carPicture3.setVerticalAlignment(SwingConstants.CENTER);
                carPicture3.setIcon(null);
                carPicture4.setText("No image found!");
                carPicture4.setHorizontalAlignment(SwingConstants.CENTER);
                carPicture4.setVerticalAlignment(SwingConstants.CENTER);
                carPicture4.setIcon(null);
                stockField.setText("NO CARS");
            }
        }
        frmImageSorter.setLocationRelativeTo(null);
    }
/**
 * Loads pictures into a given JLabel.  Needs to be passed the position in the array of image locations as well.
 * @param carPicture
 * @param j
 * @author Aevum Kairos
 */
    private void loadImage(JLabel carPicture, int j) {
        if (car.getImageNames()[j] != null) {
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(car.getImageNames()[j]));
            } catch (IOException e) {
                carPicture.setVisible(false);
            }
            Image dimg = img.getScaledInstance(carPicture.getWidth(), carPicture.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
            carPicture.setIcon(imageIcon);
        } else {
            carPicture.setHorizontalAlignment(SwingConstants.CENTER);
            carPicture.setVerticalAlignment(SwingConstants.CENTER);
            carPicture.setText("No image found!");
        }
    }
/**
 * Loads a mouse adapter on the passed JLabel and an integer for the array position
 * @param carPicture
 * @param j
 * @author Aevum Kairos
 */
    private void loadMouseAdapter(JLabel carPicture, final int j) {
        carPicture.addMouseListener(new MouseAdapter() {
            boolean OneClick;

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (OneClick) {
                    if (car.getImageNames()[j] != null)
                        try {
                            Desktop.getDesktop().open(new File(car.getImageNames()[j]));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    OneClick = false;
                } else {
                    OneClick = true;
                    Timer t = new Timer("clickTimer", false);
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            OneClick = false;
                        }
                    }, 500);
                }
            }
        });
    }
/**
 * Reloads the GUI.
 * 
 * @author Aevum Kairos
 */
    private void restart() {
        frmImageSorter.dispose();
        new ImageSorter();
        initialize();
        frmImageSorter.setVisible(true);
    }
/**
 * Returns the car object this class is using.
 * @return
 * @author Aevum Kairos
 */
    public static Car getCar() {
        return car;
    }
}
