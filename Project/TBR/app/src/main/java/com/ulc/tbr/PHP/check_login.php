<?php
    $user = "root";    
    $password = "";    
    $host ="localhost";    
    $db_name ="tutorbyrequest";
    $con = mysqli_connect($host,$user,$password,$db_name); 
    
    //$netid = $_REQUEST["netid"];
    //$loginpass = $_REQUEST["password"];
    $netid = "student";
    $loginpass = "student";      
           
    //$sql = "select* from users_table where netid = ('".$netid."') and password = ('".$loginpass."');";
    //$sql = "select* from users_table where netid = 'student' and password = 'student';";
    $sql = "select* from users_table;";
    $result = mysqli_query($con,$sql);
    
    if(!$result) //mysqli_query($con,$sql)
    {
	echo "No users.<br>";
	echo $netid . "<br>";
	echo $loginpass . "<br>";

    }
    else
    {
	echo "User found." . "<br>";
	// Process all rows
	while($row = $result->fetch_assoc()) {
    		//echo $row['column_name']; // Print a single column data
    		echo $row['name'] . "<br>";       // Print the entire row data
	}
    } 
    mysqli_close($con);    
    ?>   