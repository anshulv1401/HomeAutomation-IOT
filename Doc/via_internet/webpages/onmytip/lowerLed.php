<?php

session_start();
$usb_comPort = "COM3";
$device_id='4';
$status="not_on";
if(isset($_SESSION['device_id'])){
	$device_id=$_SESSION['device_id'];
}
if(isset($_SESSION['status'])){
	$status=$_SESSION['status'];
}
if($device_id=="1"){
	if($status=="on"){	
		exec("ECHO 1 > $usb_comPort"); // Turn On LED 1
	}else{
		exec("ECHO 2   > $usb_comPort "); // Turn Off LED 1
	}
}else if($device_id=="2"){
	if($status=="on"){	
		exec("ECHO 3 > $usb_comPort"); // Turn On LED 1
	}else{
		exec("ECHO 4   > $usb_comPort "); // Turn Off LED 1
	}
}else if($device_id=="3"){
	if($status=="on"){	
		exec("ECHO 5 > $usb_comPort"); // Turn On LED 1
	}else{
		exec("ECHO 6   > $usb_comPort "); // Turn Off LED 1
	}
}
?>