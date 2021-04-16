<?php
    $user = "pistachi_user";    
    $password = "!=ioI~dW,ZIR";    
    $host ="localhost";    
    $db_name ="pistachi_TutorByRequest";
    $con = mysqli_connect($host,$user,$password,$db_name);
    
    // Create SQL query.
    $sql = "select distinct subject from courses_table;";
    $result = mysqli_query($con,$sql);
    

    while($row = $result->fetch_assoc()){
        $return['Subjects: '][] =
            $array = array(
                "subject" => $row['subject'],
            );
    }

    // Turn array into JSON.
    $myJSON = json_encode($return);

    echo $myJSON;

    mysqli_close($con);    
    ?>   