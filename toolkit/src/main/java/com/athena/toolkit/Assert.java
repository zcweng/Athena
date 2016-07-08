package com.athena.toolkit;

import android.text.TextUtils;

/**
 * Created by Pierce on 2016/7/9.
 */
public class Assert {

    public static void notNull(Object o){
        if(o == null){
            throw new IllegalArgumentException();
        }
    }

    public static void notEmpty(String str){
        if(TextUtils.isEmpty(str)){
            throw new IllegalArgumentException();
        }
    }

    public static void notEmpty(CharSequence str){
        if(TextUtils.isEmpty(str)){
            throw new IllegalArgumentException();
        }
    }

    public static void isTrue(boolean arg){
        if(!arg){
            throw new IllegalArgumentException();
        }
    }

    public static void isPositive(int arg){
        if(arg <= 0){
            throw new IllegalArgumentException();
        }
    }

    public static void isNegative(int arg){
        if(arg >= 0){
            throw new IllegalArgumentException();
        }
    }

    /**
     * 不小于0
     */
    public static long notLessThanZero(long number) {
        if (number < 0) {
            throw new IllegalArgumentException("Can not be < 0.");
        }
        return number;
    }


    public static void checkOffsetAndCount(int arrayLength, int offset, int count) {
        if ((offset | count) < 0 || offset > arrayLength || arrayLength - offset < count) {
            throw new ArrayIndexOutOfBoundsException("length=" + arrayLength
                    + "; regionStart=" + offset
                    + "; regionLength=" + count);
        }
    }
    public static void checkOffsetAndCount(int offset, int count) {
        if ((offset | count) < 0) {
            throw new ArrayIndexOutOfBoundsException("regionStart=" + offset
                    + "; regionLength=" + count);
        }
    }
    public static void checkBytes(byte[] array, int len) {
        if (len < 0) {
            throw new ArrayIndexOutOfBoundsException("len=" + len);
        }
        if (array == null || array.length != len) {
            throw new ArrayIndexOutOfBoundsException("array=" + len+"; len="+len);
        }
    }

}
