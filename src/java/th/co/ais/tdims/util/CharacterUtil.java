/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.util;

public class CharacterUtil {

    public static String removeNull(Object data) {
       return (data == null ? "" : String.valueOf(data));
    }
}
