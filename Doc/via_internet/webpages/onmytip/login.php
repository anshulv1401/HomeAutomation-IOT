<?php

	if($_SERVER["REQUEST_METHOD"]=="POST"){
		require 'connection.php';
		checkStudent();
	}
		
	function checkStudent(){
		global $connect;
	
		$email=$_POST["email"];
		$password=$_POST["password"];
		
		$q=mysqli_query($connect,"select * from registration_table where email='".$email."' and password='".$password."'");
		$n=mysqli_num_rows($q);
		
		
		
		if($n>0)
		{
			while($row = mysqli_fetch_assoc($q)){
				$temp_array[]= $row;
			}
			
			header("Content-Type : application/json");
			echo json_encode(array("userdetails"=>$temp_array));
				
			mysqli_close($connect);
			
		}else
		{
			$temp_array=array("result"=>"fail");
		
			header("Content-Type : application/json");
			echo json_encode(array("userdetails"=>$temp_array));
			
			mysqli_close($connect);
	
		
		}	
	}


?>
