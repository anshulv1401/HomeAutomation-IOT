<?php
session_start();

	if($_SERVER["REQUEST_METHOD"]=="POST"){
		require 'connection.php';
		switchStatus();
	}
	
	function switchStatus(){
		
		global $connect;
		
		$status = $_POST["status"];
		$deviceId = $_POST["deviceId"];
		$query;
		if($status=="on"){
			$query="UPDATE device_list SET status = 'off' WHERE deviceId = '".$deviceId."';";	
			$status="off";
		}else{
			$query="UPDATE device_list SET status = 'on' WHERE deviceId = '".$deviceId."';";	
			$status="on";
		}
		mysqli_query($connect,$query) or die(mysqli_error($connect));
		mysqli_close($connect);
		$temp_array[]= "success";
		header("Content-Type : application/json");
		echo json_encode(array("status"=>$temp_array));
		
		$_SESSION['device_id']=$deviceId;
		$_SESSION['status']=$status;
		
		header("location: lowerLed.php");
		
		
		
	}
?>