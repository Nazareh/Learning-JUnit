package util;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class SparseArrayTest {
    private SparseArray sparseArray;

    @Before
    public void before(){
        sparseArray = new SparseArray();
    }


    @Test
    public void handlesInsertionInDescendingOrder() {
        sparseArray.put(7, "seven");
        sparseArray.checkInvariants();
        sparseArray.put(6, "six");
        sparseArray.checkInvariants();
        assertThat(sparseArray.get(6), equalTo("six"));
        assertThat(sparseArray.get(7), equalTo("seven"));
    }
    @Test
    public void testBinarySearchValueFound (){
        int value = 40;
        int[] sortedArray = {10,20,30,40,50};
        int size = sortedArray.length;
        assertThat(sparseArray.binarySearch(value,sortedArray,size),equalTo(3));

    }    @Test
    public void testBinarySearchValueNotFound (){
        SparseArray sa = new SparseArray();
        int value = 25;
        int[] sortedArray = {10,20,30,40,50};
        int size = sortedArray.length;
        assertThat(sa.binarySearch(value,sortedArray,size),equalTo(-1));
    }
    @Test
    public void testBinarySearchEmptyArray (){
        SparseArray sa = new SparseArray();
        int value = 25;
        int[] sortedArray = {};
        int size = sortedArray.length;
        assertThat(sa.binarySearch(value,sortedArray,size),equalTo(-1));
    }

}