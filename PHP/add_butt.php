<?php    
    $name = $_REQUEST["name"];
    //$name = "test";       
    $user = "root";    
    $password = "";    
    $host ="localhost";    
    $db_name ="test";    
    $con = mysqli_connect($host,$user,$password,$db_name);    
    $sql = "insert into butt values('".$name."');";    
    if(mysqli_query($con,$sql))    
    {    
        echo "Data inserted successfully....";    
    }    
    else     
    {    
        echo "some error occured";    
    }    
    mysqli_close($con);    
    ?>   