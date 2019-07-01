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
