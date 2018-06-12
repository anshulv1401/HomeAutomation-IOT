<?php
	/*define('hostname','localhost');
	define('user','root');
	define('password',''); 
	define('databaseName','askrat2');
	
	$connect = mysql_connect(hostname,user,password);
	mysql_select_db(databaseName);*/
	
	
	$host="localhost";
	$user="root";
	$password="";
	$db_name="onmytip_db";
	$connect=mysqli_connect($host,$user,$password,$db_name) or die("Server not avaliable");
	
?>   
