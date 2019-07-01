import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class DisplayMapMain extends PApplet {

//Setting Global Variables
PFont font; 
PImage mapImage;
Table populationTable;
float dataMin = MAX_FLOAT;
float dataMax = MIN_FLOAT;
float mapScreenWidth, mapScreenHeight; 

int rowCount;

float xLocation;
float yLocation;
float sizeValue;
String usState;
TableRow currentRow;

boolean twentyTenPopulation = false;
boolean twentyFourteenPopulation = false;
boolean twentyEighteenPopulation = false;

public void setup(){
  //Set the Size, Background and Title of the Window:
  
  background(255, 255, 255);
    
  mapScreenWidth  = width;
  mapScreenHeight = height;
  
  //Load the Tables and images working with.
  populationTable = loadTable("Data/StatePopulationTotals_tsv.tsv", "header"); //loads the Table inside, and 2nd argument tells the function that there is a header in the file.
  mapImage = loadImage("Images/US_Map.png");
  rowCount = populationTable.getRowCount();
   
}

public void draw() {
   
   background(255);
  
   //image(mapImage,0,0,mapScreenWidth,mapScreenHeight);  //Set the Map Image to size of Width and Height.
   image(mapImage, 0, 0);
   smooth(); 
   strokeWeight(0.5f);
   eventHandler();
   surface.setTitle("US Population Visualizations"); 
    
   setText();
}

//Method to handle all the events from the user.
public void eventHandler(){
  
  keyPressed();
  
}

public void keyPressed(){
  if(key =='q' ||key =='Q'){
    twentyTenPopulation = true;
     twentyFourteenPopulation = false;
    twentyEighteenPopulation = false;
    mapData("Population 2010", 192, 0, 0);
  }else if(key =='w'||key =='W'){
    twentyFourteenPopulation = true;
    twentyTenPopulation = false;
    twentyEighteenPopulation = false;
    mapData("Population 2014", 0,111, 255);  
  }else if(key =='e'||key =='E'){
    twentyTenPopulation = false;
    twentyFourteenPopulation = false;
    twentyEighteenPopulation = true;
    mapData("Population 2018", 140, 124, 12);  
  }else{
    twentyFourteenPopulation = false;
    twentyTenPopulation = false;
    twentyEighteenPopulation = false;
  }
}

//Method to visually draw the data on the map:
public void mapData(String populationYear, int R, int G, int B){
  //getMinMaxValues();
    
  for (int i =0; i < rowCount; i++){
    
    currentRow = populationTable.getRow(i);
    usState = currentRow.getString("State");
    float origSizeValue = currentRow.getFloat(populationYear);
    sizeValue = sqrt(origSizeValue / 50000); //Use this formula to convert the numbers to a much smaller ratio to fit the screen.
    //println(usState + " "+ sizeValue); 
    xLocation = currentRow.getFloat("XCoordinate"); 
    yLocation = currentRow.getFloat("Ycoordinate");
    strokeWeight(2.0f);
    ellipse(xLocation, yLocation, sizeValue, sizeValue); //draw circles to represent sizes
    fill(R,G,B); 
      if ( dist(mouseX, mouseY, xLocation, yLocation) < 6) {
        stroke(0xff3735da);
        strokeWeight(5);
        point(xLocation, yLocation);
        fill(0);
        textSize(14);
        text( nf((int)origSizeValue, 0, 2), xLocation, yLocation -10);
      }
  }
  
}
//Method to set the Text of the Window:
public void setText(){
  //Display Text Properties:
  String s = "Press 'q', 'w', 'e' for different yearly Visualizations (2010, 2014, 2019).";
  textSize(16);
  fill(140, 9, 12);
  text(s, 140, 20);  // Text wraps within text box
  
  
if(twentyTenPopulation ==true) { surface.setTitle("US Population Visualizations - 2010"); }
if(twentyFourteenPopulation == true) {surface.setTitle("US Population Visualizations - 2014"); }
if(twentyEighteenPopulation == true){surface.setTitle("US Population Visualizations - 2018"); }
}

public void getMinMaxValues(){
   //Find the minimum and maximum values.
   for (int row = 0; row < rowCount; row++) {
     float thisValue = populationTable.getFloat(row, 0);
     if (thisValue > dataMax) {
       dataMax = thisValue;
     }
   
     if (thisValue < dataMin) {
       dataMin = thisValue;
     } 
   } 
  
}
  public void settings() {  size(700,450); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "DisplayMapMain" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
