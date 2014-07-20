package Polygon.Difference;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created by ilaznyan on 7/11/2014.
 */
public class Manager {
    private ArrayList<PolygonWithName> catalog = new ArrayList<PolygonWithName>();
    private static String mFolderLoad;
    private static String mFolderSave;
    public Manager(final String folderLoad,final String folderSave){
        mFolderLoad = folderLoad;
        mFolderSave = folderSave;
        loadUniquePolygon();
    }
    static  private  String dellExtensionsFileName(String nameFile){
        int a = nameFile.length()-4;
        return nameFile.substring(0,a);
    }
    static  private  String addExtensionsFileName(String nameFile){
        return nameFile+".wkt";
    }
    static private void readFolder(ArrayList<String>catalog, String nameCatalog){
        File folder = new File(nameCatalog);
        for (final File file : folder.listFiles()) {
            catalog.add(dellExtensionsFileName(file.getName()));
        }
    }
    static private boolean greatPoligongon(PolygonWithName polygonWithName){
        try{
            polygonWithName.setPolygon((Polygon) new WKTReader().
                    read(mFolderLoad+addExtensionsFileName(polygonWithName.getmName())));
            return true;
        }
        catch (Exception e){
            // convert data error
            return false;
        }
    }
    private void loadFile(final File file){
        String readFiles = "";
        Scanner in = null;
        try{
            in = new Scanner(file);
            while (in.hasNext()){
                readFiles += in.nextLine();
            }
            PolygonWithName polygonWithName = new PolygonWithName(dellExtensionsFileName(file.getName()),null);
            if (greatPoligongon(polygonWithName) == true){
                catalog.add(polygonWithName);
            }
        }
        catch (Exception e){
            //error read in file
        }
        finally {
            if ( in!=null ){
                in.close();
            }
        }
    }
    public void loadFolder(){
        File folder = new File(mFolderLoad);
        for (final File file : folder.listFiles()) {
           loadFile(file);
        }
    }
    public Polygon differencePolygons(Polygon one, Polygon two){
        Geometry result = one.intersection(two);
        return (Polygon) result;
    }
    private void saveFiles(Polygon polygon, String fileName) {
        String strPolygon = new WKTWriter().write(polygon);
        String path = addExtensionsFileName(mFolderSave + fileName);
        File file = new File(path);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            writer.println(polygon);
            writer.close();
        }
        catch (IOException e){
            //error write data
        }
        finally {
            if (writer != null){
                writer.close();
            }
        }
    }
    private void loadUniquePolygon()
    {
        ArrayList<String>catalogRecycled = new ArrayList<String>();
        readFolder(catalogRecycled,mFolderSave);
        loadFolder();
        boolean check= false;
        for (int i = 0; i < catalog.size() ; i++) {
            for (int j = 0; j < catalogRecycled.size();j++){
                if (catalogRecycled.get(j).
                        regionMatches(0, catalog.get(i).getmName(), 0, catalog.get(i).getmName().length())) {
                    check = true;
                    break;
                }
            }
            if (check == true){
                check = false;
                for (int j = 0; j < catalog.size();j++){
                    Polygon polygon = differencePolygons(catalog.get(i).getPolygon(),catalog.get(j).getPolygon());
                    String fileName = addExtensionsFileName(catalog.get(j).getmName() + "" + catalog.get(i).getmName());
                    saveFiles(polygon,fileName);
                }
            }
        }
    }


}