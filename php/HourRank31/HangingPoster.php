<?php

/*
 * Complete the 'solve' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER h
 *  2. INTEGER_ARRAY wallPoints
 *  3. INTEGER_ARRAY lengths
 */

function solve($h, $wallPoints, $lengths) {
    // Write your code here
    $wallPoints_Lengths = array_combine( $wallPoints, $lengths );
    $heightReaches = [];
    
    foreach( array_keys($wallPoints_Lengths) as $wallPoint ) {
        $length = $wallPoints_Lengths[$wallPoint];
        array_push( $heightReaches, $wallPoint - ($length * .25) );    
    }
    
    sort( $heightReaches );
    
    $ladderHeight = ceil( array_pop( $heightReaches ) - $h );

    return 
        ( $ladderHeight > 0 ) ?
            $ladderHeight :
            0;
}

$fptr = fopen(getenv("OUTPUT_PATH"), "w");

$first_multiple_input = explode(' ', rtrim(fgets(STDIN)));

$n = intval($first_multiple_input[0]);

$h = intval($first_multiple_input[1]);

$wallPoints_temp = rtrim(fgets(STDIN));

$wallPoints = array_map('intval', preg_split('/ /', $wallPoints_temp, -1, PREG_SPLIT_NO_EMPTY));

$lengths_temp = rtrim(fgets(STDIN));

$lengths = array_map('intval', preg_split('/ /', $lengths_temp, -1, PREG_SPLIT_NO_EMPTY));

$answer = solve($h, $wallPoints, $lengths);

fwrite($fptr, $answer . "\n");

fclose($fptr);
