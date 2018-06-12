<?php
	/*define('hostname','localhost');
	define('user','root');
	define('password',''); 
	define('databaseName','askrat2');
	
	$connect = mysql_connect(hostname,user,password);
	mysql_select_db(databaseName);*/
	
	$host="localhost";
	$user="id1217672_anshv1401";
	$password="password";
	$db_name="id1217672_onmytip_db";
	$connect=mysqli_connect($host,$user,$password,$db_name) or die("Server not avaliable ");	
?>   
