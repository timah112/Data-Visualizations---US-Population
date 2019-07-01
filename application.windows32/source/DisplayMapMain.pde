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

void setup(){
  //Set the Size, Background and Title of the Window:
  size(700,450);
  background(255, 255, 255);
    
  mapScreenWidth  = width;
  mapScreenHeight = height;
  
  //Load the Tables and images working with.
  populationTable = loadTable("Data/StatePopulationTotals_tsv.tsv", "header"); //loads the Table inside, and 2nd argument tells the function that there is a header in the file.
  mapImage = loadImage("Images/US_Map.png");
  rowCount = populationTable.getRowCount();
   
}

void draw() {
   
   background(255);
  
   //image(mapImage,0,0,mapScreenWidth,mapScreenHeight);  //Set the Map Image to size of Width and Height.
   image(mapImage, 0, 0);
   smooth(); 
   strokeWeight(0.5);
   eventHandler();
   surface.setTitle("US Population Visualizations"); 
    
   setText();
}

//Method to handle all the events from the user.
void eventHandler(){
  
  keyPressed();
  
}

void keyPressed(){
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
void mapData(String populationYear, int R, int G, int B){
  //getMinMaxValues();
    
  for (int i =0; i < rowCount; i++){
    
    currentRow = populationTable.getRow(i);
    usState = currentRow.getString("State");
    float origSizeValue = currentRow.getFloat(populationYear);
    sizeValue = sqrt(origSizeValue / 50000); //Use this formula to convert the numbers to a much smaller ratio to fit the screen.
    //println(usState + " "+ sizeValue); 
    xLocation = currentRow.getFloat("XCoordinate"); 
    yLocation = currentRow.getFloat("Ycoordinate");
    strokeWeight(2.0);
    ellipse(xLocation, yLocation, sizeValue, sizeValue); //draw circles to represent sizes
    fill(R,G,B); 
      if ( dist(mouseX, mouseY, xLocation, yLocation) < 6) {
        stroke(#3735da);
        strokeWeight(5);
        point(xLocation, yLocation);
        fill(0);
        textSize(14);
        text( nf((int)origSizeValue, 0, 2), xLocation, yLocation -10);
      }
  }
  
}
