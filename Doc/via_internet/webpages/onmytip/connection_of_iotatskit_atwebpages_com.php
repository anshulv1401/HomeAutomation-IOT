<?php
	/*define('hostname','localhost');
	define('user','root');
	define('password',''); 
	define('databaseName','askrat2');
	
	$connect = mysql_connect(hostname,user,password);
	mysql_select_db(databaseName);*/
	
	$host="fdb16.awardspace.net";
	$user="2360129_onmytip";
	$password="onmytiP@123";
	$db_name="2360129_onmytip";
	$connect=mysqli_connect($host,$user,$password,$db_name) or die("Server not avaliable");
?>   
