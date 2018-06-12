<?php
	/*define('hostname','localhost');
	define('user','root');
	define('password',''); 
	define('databaseName','askrat2');
	
	$connect = mysql_connect(hostname,user,password);
	mysql_select_db(databaseName);*/
	
	$host="localhost";
	$user="id1707652_root";
	$password="password";
	$db_name="id1707652_onmytip_db";
	$connect=mysqli_connect($host,$user,$password,$db_name) or die("Server not avaliable");
?>   
