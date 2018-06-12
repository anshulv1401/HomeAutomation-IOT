<?php
	include 'connection.php';
	global $connect;
	$query = "select deviceId,status from device_list;";
	$result= mysqli_query($connect,$query);
	$no_of_rows=mysqli_num_rows($result);
	$temp_array=array();	
	if($no_of_rows > 0){
		while($row = mysqli_fetch_assoc($result)){
			$temp_array[]= $row;
		}
	}
	header("Content-Type : application/json");
	echo json_encode(array("device_status"=>$temp_array));
	mysqli_close($connect);
?>