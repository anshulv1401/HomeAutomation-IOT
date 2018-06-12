<?php

	if($_SERVER["REQUEST_METHOD"]=="POST"){
		require 'connection.php';
		$check=$_POST["check"];
		if($check=="true"){
	    	createStudent();
		}else{
			updateStudent();
		}
	}
	
	function createStudent(){
		
		global $connect;
		
		$ratid = $_POST["ratid"];
		$firstname = $_POST["firstname"];
		$lastname=$_POST["lastname"];
		$email=$_POST["email"];
		$contactno=$_POST["contact"];
		$dateofbirth=$_POST["dateofbirth"];
		$gender=$_POST["gender"];
		$password=$_POST["password"];
		$date=$_POST["dateinmillis"];
		
		$query = "INSERT into registrationtable(ratid,firstname,lastname,email,contact,dateofbirth,gender,password,dateinmillis) VALUES ('$ratid','$firstname','$lastname','$email','$contactno','$dateofbirth','$gender','$password','$date');";
		
		
		$result = mysqli_query($connect,$query);
		
		$temp_array=array();
		$temp_array[]= $result;		
		
		header("Content-Type : application/json");
		echo json_encode(array("registrationCheck"=>$temp_array));
		mysqli_close($connect);
		
		
	}
	
	function updateStudent(){
		
		global $connect;
	
		$ratid = $_POST["ratid"];
		$gender = $_POST["gender"];
		$dateofbirth = $_POST["dateofbirth"];
		
		$query="UPDATE registrationtable SET gender = '".$gender."' , dateofbirth = '".$dateofbirth."' WHERE ratid = '".$ratid."';";
		
		$result = mysqli_query($connect,$query);
		
		$temp_array=array();
		$temp_array[]= $result;		
		
		header("Content-Type : application/json");
		echo json_encode(array("registrationCheck"=>$temp_array));
		mysqli_close($connect);
	
	}
	
	
	
	
?>