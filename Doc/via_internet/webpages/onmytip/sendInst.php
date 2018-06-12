<?php

	if($_SERVER["REQUEST_METHOD"]=="GET"){
		include 'connection.php';
		sendInst();
	}
	
	function sendInst(){
		
		global $connect;
		
		
		$query1 = "select status from device_list";
		$result1= mysqli_query($connect,$query1);
		
		$no_of_rows=mysqli_num_rows($result1);
		
		$temp_array1=array();
		
		if($no_of_rows > 0){
			while($row = mysqli_fetch_assoc($result1)){
				$temp_array1[]= $row;
			}
		}
		
		$device_status=array();
		$a=0;
		
		foreach($temp_array1 as $x=>$x_value)
		{
			if($x_value["status"]=='on'){
				$device_status[$a]='on';
			}else{
				$device_status[$a]='off';
			}
			$a++;
		}
		$a=count($device_status);
		echo "*";
		for($t=0;$t<$a;$t++){
			echo $t;
			echo $device_status[$t];
		}
		echo $t;
		echo "\n";
		mysqli_close($connect);
	} 
?>