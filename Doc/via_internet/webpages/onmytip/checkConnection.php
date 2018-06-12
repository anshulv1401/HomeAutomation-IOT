<?php

	if($_SERVER["REQUEST_METHOD"]=="POST"){
	
		checkConnection();
	}
		
	function checkConnection(){
		
	
		$test = "connected";
		$temp_array[]= $test;
		
		header("Content-Type : application/json");
		echo json_encode(array("connectionStatus"=>$temp_array));			
	}
	
?>

