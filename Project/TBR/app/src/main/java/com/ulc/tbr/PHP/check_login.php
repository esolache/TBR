<?php
    $user = "root";    
    $password = "";    
    $host ="localhost";    
    $db_name ="tutorbyrequest";
    $con = mysqli_connect($host,$user,$password,$db_name); 
    
    // if($_REQUEST["netid"]){
    //     $netid = $_REQUEST["netid"];
    // }
    // if($_REQUEST["password"]){
    //     $loginpass = $_REQUEST["password"];
    // }
    
    $netid = "student";
    $loginpass = "student";      
           
    $sql = "select* from users_table where net_id = ('".$netid."') and password = ('".$loginpass."');";
    //$sql = "select* from users_table where net_id = 'student' and password = 'student';";
    //$sql = "select* from users_table;";
    $result = mysqli_query($con,$sql);
    
    if(!$result) //mysqli_query($con,$sql)
    {
	echo "No users.<br>";
	echo $netid;
	echo $loginpass . "<br>";

    }
    else
    {
    $row = $result->fetch_assoc();
    $array = array(
        "student_id" => $row['student_id'],
        "net_id" => $row['net_id'],
        "name" => $row['name'],
        "email" => $row['email'],
        "tutor" => $row['tutor'],
        "tutee" => $row['tutee'],
    );
    // $myObj->student_id = $row['student_id'];
    // $myObj->name = $row['name'];
    // $myObj->net_id = $row['net_id'];

    $myJSON = json_encode($array);

    echo $myJSON;
	echo "User found." . "<br>";
	// Process all rows
	// while($row = $result->fetch_assoc()) {
    // 		//echo $row['column_name']; // Print a single column data
    // 		echo $row['name'] . "<br>";       // Print the entire row data
	// }
    } 
    mysqli_close($con);    
    ?>   