/*
  Web client

 This sketch connects to a website (http://www.google.com)
 using an Arduino Wiznet Ethernet shield.

 Circuit:
 * Ethernet shield attached to pins 10, 11, 12, 13

 created 18 Dec 2009
 by David A. Mellis
 modified 9 Apr 2012
 by Tom Igoe, based on work by Adrian McEwen

 */

#include <SPI.h>
#include <Ethernet.h>

// Enter a MAC address for your controller below.
// Newer Ethernet shields have a MAC address printed on a sticker on the shield
byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
// if you don't want to use DNS (and reduce your sketch size)
// use the numeric IP instead of the name for the server:
//IPAddress server(74,125,232,128);  // numeric IP for Google (no DNS)
char server[] = "192.168.0.100";    // name address for Google (using DNS)

// Set the static IP address to use if the DHCP fails to assign
IPAddress ip(192, 168, 0, 177);

String val1 = "----------";
String val2 = "----------";
String val3 = "----------";
String val4 = "----------";
String val5 = "----------";
String val6 = "----------";
String val7 = "----------";
String val8 = "----------";

String device_on="on";
String device_off="off";

int pin1=14;
int pin2=15;
int pin3=16;
int pin4=17;
int pin5=18;
int pin6=19;
int pin7=20;
int pin8=21;


// Initialize the Ethernet client library
// with the IP address and port of the server
// that you want to connect to (port 80 is default for HTTP):
EthernetClient client;

void setup() {
  // Open serial communications and wait for port to open:
  Serial.begin(9600);
  pinMode(pin1,OUTPUT);
  pinMode(pin2,OUTPUT);
  pinMode(pin3,OUTPUT);
  pinMode(pin4,OUTPUT);
  pinMode(pin5,OUTPUT);
  pinMode(pin6,OUTPUT);
  pinMode(pin7,OUTPUT);
  pinMode(pin8,OUTPUT);
  
  while (!Serial) {
     // wait for serial port to connect. Needed for native USB port only
  }

  // start the Ethernet connection:
  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    // try to congifure using IP address instead of DHCP:
    Ethernet.begin(mac, ip);
  }
  // give the Ethernet shield a second to initialize:
  delay(1000);
  Serial.println("connecting...");
  printIPAddress();
  // if you get a connection, report back via serial:
  
}

void loop() {
  // if there are incoming bytes available
  // from the server, read them and print them

  // if there's a successful connection:
  if (client.connect(server, 80)) {
    Serial.println("connected");
    // Make a HTTP request:
    client.println("GET /onmytip/sendInst.php HTTP/1.1");
    client.println("Host: 192.168.0.100");
    client.println("Connection: close");
    client.println();
    
  } else {
    // if you didn't get a connection to the server:
    Serial.println("connection failed");
  }
  //wait for response
  delay(500);
  while (client.available()) {
      char c = client.read();
      //Serial.print(c);
      if (c=='*') {
        String tempMsg = client.readStringUntil('\n');

       
        val1 = splitToVal(tempMsg, "0", "1");
        val2 = splitToVal(tempMsg, "1", "2");
        val3 = splitToVal(tempMsg, "2", "3");
        val4 = splitToVal(tempMsg, "3", "4");
        val5 = splitToVal(tempMsg, "4", "5");
        val6 = splitToVal(tempMsg, "5", "6");
        val7 = splitToVal(tempMsg, "6", "7");
        val8 = splitToVal(tempMsg, "7", "8");

        /*NOTE :- current project contains relay system with active low switching.
         * Means when the relays are set to the 'normally open' configuration, it will turn on and conduct when low value(0 voltage) is applied to it.
         * This made confusion for us, as the whole project was designed for a active high relay system.
         * Due to lack of time and energy slight changes made in arduino code.
         * As you will notice that first 3 if-else code are set to write HIGH for a device_off command, as these 3 are for the Active Low relay system.
        */
        if(val1==device_off){
          digitalWrite(pin1,HIGH);
        }else{
          digitalWrite(pin1,LOW);  
        }
        
        if(val2==device_off){
          digitalWrite(pin2,HIGH);
        }else{
          digitalWrite(pin2,LOW); 
        }
        
        if(val3==device_off){
          digitalWrite(pin3,HIGH);
        }else{
          digitalWrite(pin3,LOW);  
        }
        
        if(val4==device_on){
          digitalWrite(pin4,HIGH);
        }else{
          digitalWrite(pin4,LOW);  
        }

        if(val5==device_on){
          digitalWrite(pin5,HIGH);
        }else{
          digitalWrite(pin5,LOW);  
        }

        if(val6==device_on){
          digitalWrite(pin6,HIGH);
        }else{
          digitalWrite(pin6,LOW);  
        }

        if(val7==device_on){
          digitalWrite(pin7,HIGH);
        }else{
          digitalWrite(pin7,LOW);  
        }

        if(val8==device_on){
          digitalWrite(pin8,HIGH);
        }else{
          digitalWrite(pin8,LOW);  
        }
        Serial.println(val1);
        Serial.println(val2);
        Serial.println(val3);
        Serial.println(val4);
        Serial.println(val5);
        Serial.println(val6);
        Serial.println(val7);
        Serial.println(val8);
        
      }
    }
  

  // if the server's disconnected, stop the client:
  if (!client.connected()) {
    Serial.println();
    Serial.println("disconnecting.");
    client.stop();
    delay(100);
  }
}

void printIPAddress()
{
  Serial.print("My IP address: ");
  for (byte thisByte = 0; thisByte < 4; thisByte++) {
    // print the value of each byte of the IP address:
    Serial.print(Ethernet.localIP()[thisByte], DEC);
    Serial.print(".");
  }

  Serial.println();
}

String splitToVal(String inputString, String delimiter, String endChar) {
  String tempString = "";
  int from=-1;
  int to=-1;
  for (int i = 0; i < inputString.length(); i++) {
    if (inputString.substring(i, i + 1) == delimiter) {
      from = i + 1;
    }
    if (inputString.substring(i, i + 1) == endChar) {
      to = i;
    }     
   }
  if(to==-1){
    return device_off;
  }else{
    tempString = inputString.substring(from, to);
    return tempString;
  }
}
