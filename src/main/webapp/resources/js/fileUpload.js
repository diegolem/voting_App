/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @ignore
 */
function contains(arr, obj) {
    var returnVal = false;
    if (arr !== null) {
        for (var i = 0; i < arr.length; i++)
            if (arr[i] === obj.id) {
                returnVal = true;
                break;
            }

    }
    return returnVal;
}