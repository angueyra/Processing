import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import dawesometoolkit.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class LogToFile extends PApplet {

/*
Example of using the log to file feature of the Debug class. 
*/



DawesomeToolkit dawesome;
Debug debug;
ArrayList<PVector> sphere;



public void setup(){
  
  
  dawesome = new DawesomeToolkit(this);
  debug = new Debug(this,120,100);
  sphere = dawesome.fibonacciSphereLayout(100,150);
  noStroke();
}



public void draw(){
  
  background(20);
  debug.update("fps",frameRate);
  debug.update("frameCount",frameCount);
  debug.draw();
  lights();

  // log entry (append) to a file called log.txt every two seconds. Will auto add a timestamp with each entry

  if (frameCount%120==0){
    debug.log(frameCount);
  }

  translate(width/2,height/2);

  float xRot = radians(270 -  millis()*.02f);
  float yRot = radians(270 -  millis()*.03f);
  rotateX( xRot ); 
  rotateY( yRot );
  debug.update("xRot",xRot);
  debug.update("yRot",yRot);
  int counter = 0;
  for (PVector p : sphere) {
     pushMatrix();
       translate(p.x,p.y,p.z);
       PVector polar = dawesome.cartesianToPolar(p);
       rotateY(polar.y);
       rotateZ(polar.z);
       pushMatrix();
         fill(dawesome.BITTERSWEET);
         rotateY(radians(90));
         text(counter,0,0);
       popMatrix();
       fill(dawesome.BITTERSWEET);
       box(5,5,5);
     popMatrix();
     counter++;
  }
 
  
}
  public void settings() {  size(600,600,OPENGL);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--hide-stop", "LogToFile" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
