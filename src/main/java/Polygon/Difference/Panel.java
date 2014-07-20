package Polygon.Difference;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
/**
 * Created by Goolos on 10.07.2014.
 */
public class Panel  {
    JLabel folderOriginal;
    JLabel folderRefined;
    JButton selectFolderOriginal;
    JButton selectFolderRefined;
    JButton job;
    JTextField pathFolderOriginal;
    JTextField pathFolderRefined;
    JFileChooser selectFolder;
    private String mFolderSave;
    private String mFolderLoad;
    public Panel(){
        JFrame frame = new JFrame("Working with poligons");
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setSize(400,160);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        folderOriginal = new JLabel("Original");
        folderOriginal.setPreferredSize(new Dimension(70,20));
        folderOriginal.setHorizontalAlignment(SwingConstants.RIGHT);
        folderRefined = new JLabel("Refined");
        folderRefined.setPreferredSize(new Dimension(70, 20));
        folderRefined.setHorizontalAlignment(SwingConstants.RIGHT);
        selectFolderOriginal = new JButton("Select");
        selectFolderRefined = new JButton("Select");
        pathFolderOriginal = new JTextField(20);
        pathFolderRefined = new JTextField(20);
        job = new JButton("JOB");
        selectFolder = new JFileChooser();
        selectFolder.setCurrentDirectory(new java.io.File("."));
        selectFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        selectFolder.setDialogTitle("Change folder");
        selectFolder.setAcceptAllFileFilterUsed(false);
        selectFolderOriginal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = selectFolder.showDialog(null, "Select");
                if (result == JFileChooser.APPROVE_OPTION) {

                    pathFolderOriginal.setText(selectFolder.getCurrentDirectory() +
                            selectFolder.getSelectedFile().getName());

                }
            return;
            }
        });
        selectFolderRefined.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = selectFolder.showDialog(null, "Select");
                if (result == JFileChooser.APPROVE_OPTION) {
                    pathFolderRefined.setText(selectFolder.getCurrentDirectory() +
                            selectFolder.getSelectedFile().getName());
                }
            return;
            }
        });
        job.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pathFolderOriginal.getText().length() == 0 && pathFolderRefined.getText().length() == 0) {
                        Manager manager = new Manager(pathFolderOriginal.getText(),pathFolderRefined.getText());
                        }
                        return;
                    }
        });
                //work
        frame.getContentPane().add(folderOriginal);
        frame.getContentPane().add(pathFolderOriginal);
        frame.getContentPane().add(selectFolderOriginal);
        frame.getContentPane().add(folderRefined);
        frame.getContentPane().add(pathFolderRefined);
        frame.getContentPane().add(selectFolderRefined);
        frame.getContentPane().add(job);
        frame.setVisible(true);

    }
}