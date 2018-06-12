<?php

	if($_SERVER["REQUEST_METHOD"]=="POST"){
		require 'connection.php';
		checkStudent();
	}
		
	function checkStudent(){
		global $connect;
	
		$email=$_POST["email"];
		
		$q=mysqli_query($connect,"select * from registrationtable where email='".$email."'");
		$n=mysqli_num_rows($q);
		
		
		
		if($n>0)
		{
			$temp_array=array("result"=>"true'".$q."'");
		
			header("Content-Type : application/json");
			echo json_encode(array("ratstudent"=>$temp_array));
			
			mysqli_close($connect);
			
		}else
		{
			$temp_array=array("result"=>"false");
		
			header("Content-Type : application/json");
			echo json_encode(array("ratstudent"=>$temp_array));
			
			mysqli_close($connect);
	
		
		}
	}


?>
