<?php

	if($_SERVER["REQUEST_METHOD"]=="POST"){
		include 'connection.php';
		getDevices();
	}
	
	function getDevices(){
		
		global $connect;
		
		$room_no=$_POST["roomNo"];
		$query = "select * from device_list;";
		
		if($room_no=="0"){
			$query = "select * from device_list;";
		}else{
			$query = "select * from device_list where roomNo='".$room_no."';";
		}
		
		
		$result= mysqli_query($connect,$query);
		
		$no_of_rows=mysqli_num_rows($result);
		
		$temp_array=array();
		
		if($no_of_rows > 0){
			while($row = mysqli_fetch_assoc($result)){
				$temp_array[]= $row;
			}
		}
		header("Content-Type : application/json");
		echo json_encode(array("devices"=>$temp_array));
		mysqli_close($connect);
	} 
?>